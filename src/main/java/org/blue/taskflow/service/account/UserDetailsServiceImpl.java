package org.blue.taskflow.service.account;

import com.google.common.collect.Sets;
import org.blue.taskflow.domain.entity.account.Authority;
import org.blue.taskflow.domain.entity.account.Role;
import org.blue.taskflow.domain.entity.account.User;
import org.blue.taskflow.domain.manager.AccountManager;
import org.blue.taskflow.rest.security.OperatorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 *
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-8-27
 * Time: 11:08:02
 */
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountManager accountManager;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException, DataAccessException {
        User user = accountManager.getUserByLoginName(loginName);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + loginName + " 不存在");
		}

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

		//-- 无以下属性, 暂时全部设为true. --//
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		OperatorDetails userDetails = new OperatorDetails(user.getLoginName(), user
				.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
        //加入登录时间信息、职员和角色
        userDetails.setLoginTime(new Date());
        userDetails.setEmployee(user.getEmployee());
		userDetails.setRoleList(user.getRoleList());
		return userDetails;    
    }

    /**
	 * 获得用户所有角色的权限集合.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		for (Role role : user.getRoleList()) {
			for (Authority authority : role.getAuthorityList()) {
				authSet.add(new GrantedAuthorityImpl(authority.getPrefixedName()));
			}
		}
		return authSet;
	}
}

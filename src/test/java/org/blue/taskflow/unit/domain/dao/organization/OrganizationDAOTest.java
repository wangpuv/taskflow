package org.blue.taskflow.unit.domain.dao.organization;

import static org.junit.Assert.*;

import org.blue.taskflow.data.OrganizationData;
import org.blue.taskflow.domain.dao.organization.OrganizationDAO;
import org.blue.taskflow.domain.entity.organization.Organization;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTxTestCase;
import org.springside.modules.test.utils.DbUnitUtils;

import javax.sql.DataSource;

/**
 * OrganizationDAO的测试用例, 测试ORM映射及特殊的DAO操作.
 *
 * 默认在每个测试函数后进行回滚.
 *
 * @author blue
 */
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class OrganizationDAOTest extends SpringTxTestCase {
    private static DataSource dataSourceHolder = null;

    @Autowired
    private OrganizationDAO entityDAO;

    @Before
	public void loadDefaultData() throws Exception {
		if (dataSourceHolder == null) {
			DbUnitUtils.loadData(dataSource, "/data/default-data.xml");
			dataSourceHolder = dataSource;
		}
	}

	@AfterClass
	public static void cleanDefaultData() throws Exception {
		DbUnitUtils.removeData(dataSourceHolder, "/data/default-data.xml");
	}

    @Test
	//如果你需要真正插入数据库,将Rollback设为false
	//@Rollback(false)
	public void crudEntityWithEmployee() {
		//新建并保存带职员的机构
		Organization organization = OrganizationData.getRandomOrganizationWithEmployee();
		entityDAO.save(organization);
		//强制执行sql语句
		entityDAO.flush();

		//获取职员
		organization = entityDAO.findUniqueBy("id", organization.getId());
		assertEquals(1, organization.getEmployees().size());

		//删除机构的职员
		organization.getEmployees().clear();
		entityDAO.flush();
		organization = entityDAO.findUniqueBy("id", organization.getId());
		assertEquals(0, organization.getEmployees().size());

		//删除机构
		entityDAO.delete(organization.getId());
		entityDAO.flush();
		organization = entityDAO.findUniqueBy("id", organization.getId());
		assertNull(organization);
	}
}

package org.blue.taskflow.data;

import org.blue.taskflow.domain.entity.organization.Employee;
import org.blue.taskflow.domain.entity.organization.Organization;
import org.springside.modules.test.utils.DataUtils;

import java.util.Date;

/**
 * Organization相关实体测试数据生成.
 *
 * @author blue
 */
public class OrganizationData {

    public static Organization getRandomOrganization() {
        String organizationName = DataUtils.randomName("Organization");

        Organization organization = new Organization();
        organization.setName(organizationName);
        organization.setShortname(organizationName);
        organization.setCreateTime(new Date());
        return organization;
    }

    public static Organization getRandomOrganizationWithEmployee() {
		Organization organization = getRandomOrganization();
        Employee employee = getRandomEmployee();
        employee.setOrganization(organization);
		organization.getEmployees().add(employee);

		return organization;
	}

    public static Employee getRandomEmployee() {
        String employeeName = DataUtils.randomName("Employee");

        Employee employee = new Employee();
        employee.setName(employeeName);
        employee.setBirthday("1982-10-21");
        employee.setSex("男");
        employee.setEmail(employeeName + "@blue.org");
        employee.setCreateTime(new Date());
        return employee;
    }
}

package org.blue.taskflow.domain.manager;

import java.util.Date;
import java.util.List;

import org.blue.taskflow.domain.dao.organization.EmployeeDAO;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.blue.taskflow.domain.dao.organization.OrganizationDAO;
import org.blue.taskflow.domain.entity.organization.Employee;
import org.blue.taskflow.domain.entity.organization.Organization;

@Component
public class OrganizationManager {
    @Autowired
    private OrganizationDAO organizationDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    public List<Organization> getAllOrganization() {
        List<Organization> organList = organizationDAO.getAll("id", true);
        organizationDAO.initAllProperties(organList);
        return organList;
    }

    public Organization getOrganization(Long id) {
        Organization organization = organizationDAO.get(id);
        organizationDAO.initAllProperties(organization);
        return organization;
    }

    public void createAndStoreOrganization(Organization organization) {
        if (organization.getId() == null) {
            organization.setCreateTime(new Date());
        }
        organizationDAO.save(organization);
    }

    public void deleteOrganization(Long id) {
        organizationDAO.delete(id);
    }

    public List<Organization> getAllRootOrganization() {
        Criterion criterion = Restrictions.isNull("parent");
        List<Organization> organList = organizationDAO.find(criterion);
        organizationDAO.initAllProperties(organList);
        return organList;
    }

    public void createAndStoreEmployee(Employee employee) {
        if (employee.getId() == null) {
            employee.setCreateTime(new Date());
        }
        employeeDAO.save(employee);
    }

    public Employee getEmployee(Long id) {
        Employee employee = employeeDAO.get(id);
        employeeDAO.initAllProperties(employee);
        return employee;
    }

    public void deleteEmployee(Long id) {
        employeeDAO.delete(id);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = employeeDAO.getAll("id", true);
        employeeDAO.initAllProperties(employeeList);
        return employeeList;
    }

    public List<Employee> getEmployeesByOrganizationId(Long organizationId) {
        List<Employee> employees = employeeDAO.findBy("organization.id", organizationId);
        employeeDAO.initAllProperties(employees);
        return employees;
    }

    public List<Employee> getEmployeesByEmployeeId(Long employeeId) {
        Employee employee = employeeDAO.get(employeeId);
        employeeDAO.initAllProperties(employee);
        List<Employee> employees = employeeDAO.findBy("organization.id", employee.getOrganization().getId());
        employeeDAO.initAllProperties(employees);
        return employees;
    }
}

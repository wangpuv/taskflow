package org.blue.taskflow.service.organization;

import org.blue.taskflow.domain.entity.organization.Employee;
import org.blue.taskflow.domain.entity.organization.Organization;
import org.blue.taskflow.domain.manager.OrganizationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-8-31
 * Time: 9:21:29
 */
@Service("organizationService")
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationManager organizationManager;

    @Autowired
    public void setOrganizationManager(OrganizationManager organizationManager) {
        this.organizationManager = organizationManager;
    }

    @Override
    public void createAndStoreOrganization(Organization organization) {
        organizationManager.createAndStoreOrganization(organization);
    }

    @Override
    public void deleteOrganization(Long id) {
        organizationManager.deleteOrganization(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> getAllOrganization() {
        return organizationManager.getAllOrganization();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> getAllRootOrganization() {
        return organizationManager.getAllRootOrganization();
    }

    @Override
    @Transactional(readOnly = true)
    public Organization getOrganization(Long id) {
        return organizationManager.getOrganization(id);
    }

    @Override
    public void createAndStoreEmployee(Employee employee) {
        organizationManager.createAndStoreEmployee(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployee(Long id) {
        return organizationManager.getEmployee(id);
    }

    @Override
    public void deleteEmployee(Long id) {
        organizationManager.deleteEmployee(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return organizationManager.getAllEmployees();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByOrganizationId(Long organizationId) {
        return organizationManager.getEmployeesByOrganizationId(organizationId);
    }
}

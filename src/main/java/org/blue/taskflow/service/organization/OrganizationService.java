package org.blue.taskflow.service.organization;

import org.blue.taskflow.domain.entity.organization.Employee;
import org.blue.taskflow.domain.entity.organization.Organization;

import java.util.List;

/**
 * 机构管理服务接口
 * <p/>
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-8-31
 * Time: 8:50:56
 */
public interface OrganizationService {

    /**
     * 创建或保存机构
     *
     * @param organization 机构
     */
    void createAndStoreOrganization(Organization organization);

    /**
     * 删除机构
     *
     * @param id 机构ID
     */
    void deleteOrganization(Long id);

    /**
     * 获取所有机构的集合
     *
     * @return 所有机构的集合
     */
    List<Organization> getAllOrganization();

    /**
     * 获取所有根机构的集合
     *
     * @return 所有根机构的集合
     */
    List<Organization> getAllRootOrganization();

    /**
     * 获取机构通过机构ID
     *
     * @param id 机构ID
     * @return 机构
     */
    Organization getOrganization(Long id);

    /**
     * 创建或保存职员
     *
     * @param employee 职员
     */
    void createAndStoreEmployee(Employee employee);

    /**
     * 获取职员通过职员ID
     *
     * @param id 职员ID
     * @return 职员
     */
    Employee getEmployee(Long id);

    /**
     * 删除职员
     *
     * @param id 职员ID
     */
    void deleteEmployee(Long id);

    /**
     * 获取所有职员的集合
     *
     * @return 所有职员的集合
     */
    List<Employee> getAllEmployees();

    /**
     * 获取机构下的所有职员的集合通过机构ID
     *
     * @param organizationId 机构ID
     * @return 机构下的所有职员的集合
     */
    List<Employee> getEmployeesByOrganizationId(Long organizationId);
}
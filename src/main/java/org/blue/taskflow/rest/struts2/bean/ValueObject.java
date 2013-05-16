package org.blue.taskflow.rest.struts2.bean;

import org.blue.taskflow.domain.entity.organization.Employee;
import org.blue.taskflow.domain.entity.organization.Organization;
import org.blue.taskflow.rest.codetable.CodeTableData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-15
 * Time: 8:47:22
 */
public class ValueObject {
    private List<CodeTableData> codeTableList = new ArrayList<CodeTableData>();

    private List<Employee> employeeList = new ArrayList<Employee>();

    private List<Organization> organizationList = new ArrayList<Organization>();

    public List<CodeTableData> getCodeTableList() {
        return codeTableList;
    }

    public void setCodeTableList(List<CodeTableData> codeTableList) {
        this.codeTableList = codeTableList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }
}

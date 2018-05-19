package com.company.humanResources;

public class OrdinaryEmployeeFactory extends EmployeeFactory {
    @Override
    public EmployeeGroup createDepartment() {
        return null;
    }

    @Override
    public EmployeeGroup createDepartment(String name) {
        return null;
    }

    @Override
    public EmployeeGroup createDepartment(String name, int size) {
        return null;
    }

    @Override
    public EmployeeGroup createDepartment(String name, Employee[] employees) {
        return null;
    }

    @Override
    public EmployeeGroup createProject() {
        return null;
    }

    @Override
    public EmployeeGroup createProject(String name) {
        return null;
    }

    @Override
    public EmployeeGroup createProject(String name, Employee[] employees) {
        return null;
    }

    @Override
    public GroupsManager createDepartmentManager() {
        return null;
    }

    @Override
    public GroupsManager createDepartmentManager(String name) {
        return null;
    }

    @Override
    public GroupsManager createDepartmentManager(String name, int size) {
        return null;
    }

    @Override
    public GroupsManager createDepartmentManager(String name, Department[] groups) {
        return null;
    }

    @Override
    public GroupsManager createProjectManager() {
        return null;
    }

    @Override
    public GroupsManager createProjectManager(EmployeeGroup[] employeeGroups) {
        return null;
    }
}

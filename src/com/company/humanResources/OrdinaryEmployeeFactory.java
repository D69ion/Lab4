package com.company.humanResources;

public class OrdinaryEmployeeFactory extends EmployeeFactory {
    @Override
    public EmployeeGroup createDepartment() {
        return new Department();
    }

    @Override
    public EmployeeGroup createDepartment(String name) {
        return new Department(name);
    }

    @Override
    public EmployeeGroup createDepartment(String name, int size) {
        return new Department(name, size);
    }

    @Override
    public EmployeeGroup createDepartment(String name, Employee[] employees) {
        return new Department(name, employees);
    }

    @Override
    public EmployeeGroup createProject() {
        return new Project();
    }

    @Override
    public EmployeeGroup createProject(String name) {
        return new Project(name);
    }

    @Override
    public EmployeeGroup createProject(String name, Employee[] employees) {
        return new Project(name, employees);
    }

    @Override
    public GroupsManager createDepartmentManager() {
        return new DepartmentManager();
    }

    @Override
    public GroupsManager createDepartmentManager(String name) {
        return new DepartmentManager(name);
    }

    @Override
    public GroupsManager createDepartmentManager(String name, int size) {
        return new DepartmentManager(name, size);
    }

    @Override
    public GroupsManager createDepartmentManager(String name, Department[] groups) {
        return new DepartmentManager(name, groups);
    }

    @Override
    public GroupsManager createProjectManager() {
        return new ProjectManager();
    }

    @Override
    public GroupsManager createProjectManager(EmployeeGroup[] employeeGroups) {
        return new ProjectManager(employeeGroups);
    }
}

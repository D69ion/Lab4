package com.company.io;

import com.company.humanResources.*;

public abstract class EmployeeFactory {

    static public EmployeeFactory getEmployeeFactory(OrdersFactoryTypesEnumeration type){

    }

    public EmployeeGroup createDepartment(){
        return new Department();
    }

    public EmployeeGroup createDepartment(String name){
        return new Department(name);
    }

    public EmployeeGroup createDepartment(String name, int size){
        return new Department(name, size);
    }

    public EmployeeGroup createDepartment(String name, Employee[] employees){
        return new Department(name, employees);
    }

    public EmployeeGroup createProject(){
        return new Project();
    }

    public EmployeeGroup createProject(String name){
        return new Project(name);
    }

    public EmployeeGroup createProject(String name, Employee[] employees){
        return new Project(name, employees);
    }

    public GroupsManager createDepartmentManager(){
        return new DepartmentManager();
    }

    public GroupsManager createDepartmentManager(String name){
        return new DepartmentManager(name);
    }

    public GroupsManager createDepartmentManager(String name, int size){
        return new DepartmentManager(name, size);
    }

    public GroupsManager createDepartmentManager(String name, Department[] groups){
        return new DepartmentManager(name, groups);
    }

    public GroupsManager createProjectManager(){
        return new ProjectManager();
    }

    public GroupsManager createProjectManager(EmployeeGroup[] employeeGroups){
        return new ProjectManager(employeeGroups);
    }

}

package com.company.humanResources;

import com.company.io.*;

public class BinaryFileBasedEmployeeFactory extends EmployeeFactory {
    private GroupsManagerBinaryFileSource binaryFileSource;
    private EmployeeFactory factory;

    public BinaryFileBasedEmployeeFactory(String path, EmployeeFactory factory){
        binaryFileSource = new GroupsManagerBinaryFileSource(path);
        this.factory = factory;
    }

    public String getPath(){
        return binaryFileSource.getPath();
    }

    public void setPath(String path){
        binaryFileSource.setPath(path);
    }

    @Override
    public EmployeeGroup createDepartment() {
        ControlledDepartment controlledDepartment = new ControlledDepartment();
        binaryFileSource.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createDepartment(String name) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(name);
        binaryFileSource.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createDepartment(String name, int size) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(name, size);
        binaryFileSource.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createDepartment(String name, Employee[] employees) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(name, employees);
        binaryFileSource.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createProject() {
        ControlledProject controlledProject = new ControlledProject();
        binaryFileSource.create(controlledProject);
        return controlledProject;
    }

    @Override
    public EmployeeGroup createProject(String name) {
        ControlledProject controlledProject = new ControlledProject(name);
        binaryFileSource.create(controlledProject);
        return controlledProject;
    }

    @Override
    public EmployeeGroup createProject(String name, Employee[] employees) {
        ControlledProject controlledProject = new ControlledProject(name, employees);
        binaryFileSource.create(controlledProject);
        return controlledProject;
    }

    @Override
    public GroupsManager createDepartmentManager() {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(factory);
        for(int i = 0; i < controlledDepartmentManager.size(); i++){
            binaryFileSource.create(controlledDepartmentManager.get(i));
        }
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createDepartmentManager(String name) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, factory);
        for(int i = 0; i < controlledDepartmentManager.size(); i++){
            binaryFileSource.create(controlledDepartmentManager.get(i));
        }
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createDepartmentManager(String name, int size) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, size, factory);
        for(int i = 0; i < controlledDepartmentManager.size(); i++){
            binaryFileSource.create(controlledDepartmentManager.get(i));
        }
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createDepartmentManager(String name, Department[] groups) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, groups, factory);
        for(int i = 0; i < controlledDepartmentManager.size(); i++){
            binaryFileSource.create(controlledDepartmentManager.get(i));
        }
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createProjectManager() {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager(factory);
        for(int i = 0; i < controlledProjectManager.size(); i++){
            binaryFileSource.create(controlledProjectManager.get(i));
        }
        return controlledProjectManager;
    }

    @Override
    public GroupsManager createProjectManager(EmployeeGroup[] employeeGroups) {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager(employeeGroups, factory);
        for(int i = 0; i < controlledProjectManager.size(); i++){
            binaryFileSource.create(controlledProjectManager.get(i));
        }
        return controlledProjectManager;
    }
}

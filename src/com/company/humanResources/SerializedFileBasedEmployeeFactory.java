package com.company.humanResources;

import com.company.io.*;

public class SerializedFileBasedEmployeeFactory extends EmployeeFactory {
    private GroupsManagerSerializedFileSource serializedFileSource;
    private EmployeeFactory factory;

    public SerializedFileBasedEmployeeFactory(String path, EmployeeFactory factory){
        serializedFileSource = new GroupsManagerSerializedFileSource(path);
        this.factory = factory;
    }

    public String getPath(){
        return serializedFileSource.getPath();
    }

    public void setPath(String path){
        serializedFileSource.setPath(path);
    }

    @Override
    public EmployeeGroup createDepartment() {
        ControlledDepartment controlledDepartment = new ControlledDepartment();
        serializedFileSource.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createDepartment(String name) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(name);
        serializedFileSource.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createDepartment(String name, int size) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(name, size);
        serializedFileSource.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createDepartment(String name, Employee[] employees) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(name, employees);
        serializedFileSource.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createProject() {
        ControlledProject controlledProject = new ControlledProject();
        serializedFileSource.create(controlledProject);
        return controlledProject;
    }

    @Override
    public EmployeeGroup createProject(String name) {
        ControlledProject controlledProject = new ControlledProject(name);
        serializedFileSource.create(controlledProject);
        return controlledProject;
    }

    @Override
    public EmployeeGroup createProject(String name, Employee[] employees) {
        ControlledProject controlledProject = new ControlledProject(name, employees);
        serializedFileSource.create(controlledProject);
        return controlledProject;
    }

    @Override
    public GroupsManager createDepartmentManager() {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(factory);
        for(int i = 0; i < controlledDepartmentManager.size(); i++){
            serializedFileSource.create(controlledDepartmentManager.get(i));
        }
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createDepartmentManager(String name) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, factory);
        for(int i = 0; i < controlledDepartmentManager.size(); i++){
            serializedFileSource.create(controlledDepartmentManager.get(i));
        }
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createDepartmentManager(String name, int size) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, size, factory);
        for(int i = 0; i < controlledDepartmentManager.size(); i++){
            serializedFileSource.create(controlledDepartmentManager.get(i));
        }
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createDepartmentManager(String name, Department[] groups) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, groups, factory);
        for(int i = 0; i < controlledDepartmentManager.size(); i++){
            serializedFileSource.create(controlledDepartmentManager.get(i));
        }
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createProjectManager() {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager(factory);
        for(int i = 0; i < controlledProjectManager.size(); i++){
            serializedFileSource.create(controlledProjectManager.get(i));
        }
        return controlledProjectManager;
    }

    @Override
    public GroupsManager createProjectManager(EmployeeGroup[] employeeGroups) {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager(employeeGroups, factory);
        for(int i = 0; i < controlledProjectManager.size(); i++){
            serializedFileSource.create(controlledProjectManager.get(i));
        }
        return controlledProjectManager;
    }
}

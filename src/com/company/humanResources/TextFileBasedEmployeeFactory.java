package com.company.humanResources;

import com.company.io.*;

public class TextFileBasedEmployeeFactory extends EmployeeFactory {
    private GroupsManagerTextFileSource textFileSource;

    public TextFileBasedEmployeeFactory(String path){
        textFileSource = new GroupsManagerTextFileSource(path);
    }

    public String getPath(){
        return textFileSource.getPath();
    }

    public void setPath(String path){
        textFileSource.setPath(path);
    }

    @Override
    public EmployeeGroup createDepartment() {
        ControlledDepartment controlledDepartment = new ControlledDepartment();
        textFileSource.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createDepartment(String name) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(name);
        textFileSource.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createDepartment(String name, int size) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(name, size);
        textFileSource.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createDepartment(String name, Employee[] employees) {
        ControlledDepartment controlledDepartment = new ControlledDepartment(name, employees);
        textFileSource.create(controlledDepartment);
        return controlledDepartment;
    }

    @Override
    public EmployeeGroup createProject() {
        ControlledProject controlledProject = new ControlledProject();
        textFileSource.create(controlledProject);
        return controlledProject;
    }

    @Override
    public EmployeeGroup createProject(String name) {
        ControlledProject controlledProject = new ControlledProject(name);
        textFileSource.create(controlledProject);
        return controlledProject;
    }

    @Override
    public EmployeeGroup createProject(String name, Employee[] employees) {
        ControlledProject controlledProject = new ControlledProject(name, employees);
        textFileSource.create(controlledProject);
        return controlledProject;
    }

    @Override
    public GroupsManager createDepartmentManager() {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager();
        for(int i = 0; i < controlledDepartmentManager.size(); i++){
            textFileSource.create(controlledDepartmentManager.get(i));
        }
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createDepartmentManager(String name) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name);
        for(int i = 0; i < controlledDepartmentManager.size(); i++){
            textFileSource.create(controlledDepartmentManager.get(i));
        }
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createDepartmentManager(String name, int size) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, size);
        for(int i = 0; i < controlledDepartmentManager.size(); i++){
            textFileSource.create(controlledDepartmentManager.get(i));
        }
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createDepartmentManager(String name, Department[] groups) {
        ControlledDepartmentManager controlledDepartmentManager = new ControlledDepartmentManager(name, groups);
        for(int i = 0; i < controlledDepartmentManager.size(); i++){
            textFileSource.create(controlledDepartmentManager.get(i));
        }
        return controlledDepartmentManager;
    }

    @Override
    public GroupsManager createProjectManager() {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager();
        for(int i = 0; i < controlledProjectManager.size(); i++){
            textFileSource.create(controlledProjectManager.get(i));
        }
        return controlledProjectManager;
    }

    @Override
    public GroupsManager createProjectManager(EmployeeGroup[] employeeGroups) {
        ControlledProjectManager controlledProjectManager = new ControlledProjectManager(employeeGroups);
        for(int i = 0; i < controlledProjectManager.size(); i++){
            textFileSource.create(controlledProjectManager.get(i));
        }
        return controlledProjectManager;
    }
}

package com.company.io;

import com.company.humanResources.*;

import java.util.Collection;

public class ControlledProjectManager extends ProjectManager {
    protected Source<EmployeeGroup> employeeGroupSource;

    private static Source<EmployeeGroup> DEFAULT_EMPLOYEE_GROUP_SOURCE = null;

    public ControlledProjectManager(){
        super();
    }

    public ControlledProjectManager(EmployeeGroup[] employeeGroups){
        super(employeeGroups);
    }

    public Source<EmployeeGroup> getEmployeeGroupSource() {
        return employeeGroupSource;
    }

    public void setEmployeeGroupSource(Source<EmployeeGroup> employeeGroupSource) {
        this.employeeGroupSource = employeeGroupSource;
    }
    @Override
    public boolean add(EmployeeGroup employeeGroup) {
        return employeeGroupSource.create(new ControlledProject(employeeGroup.getName(), employeeGroup.getEmployees())) &&
                super.add(employeeGroup);
    }

    @Override
    public void addGroup(EmployeeGroup employeeGroup) throws AlreadyAddedException {
        employeeGroupSource.create(new ControlledProject(employeeGroup.getName(), employeeGroup.getEmployees()));
        super.addGroup(employeeGroup);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        employeeGroupSource.create(new ControlledProject(element.getName(), element.getEmployees()));
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) c.toArray();
        for(int i = 0; i < employeeGroups.length; i++){
            if(!add(employeeGroups[i]))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) c.toArray();
        for (int i = 0; i < employeeGroups.length; i++) {
            add(index, employeeGroups[i]);
        }
        return true;
    }

    @Override
    public boolean removeGroup(String groupName) {
        return employeeGroupSource.delete(super.getEmployeeGroup(groupName)) && super.removeGroup(groupName);
    }

    @Override
    public EmployeeGroup remove(int index) {
        if(employeeGroupSource.delete(get(index)))
            return super.remove(index);
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return employeeGroupSource.delete((EmployeeGroup) o) && super.remove(o);
    }

    @Override
    public int removeGroup(EmployeeGroup project) {
        if(employeeGroupSource.delete(project))
            return super.removeGroup(project);
        return 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) c.toArray();
        for(int i = 0; i < employeeGroups.length; i++){
            if(!remove(employeeGroups[i]))
                return false;
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        EmployeeGroup[] employeeGroup = (EmployeeGroup[]) c.toArray();
        for(int i = 0 ; i < employeeGroup.length; i++){
            if(!contains(employeeGroup[i])){
                removeGroup(employeeGroup[i]);
            }
        }
        return true;
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        employeeGroupSource.delete(get(index));
        employeeGroupSource.create(new ControlledDepartment(element.getName(), element.getEmployees()));
        return super.set(index, element);
    }

    public void store(){
        EmployeeGroup[] employeeGroups = super.getGroups();
        ControlledDepartment controlledDepartment;
        for(int i = 0; i < super.size(); i++){
            if(employeeGroups[i] instanceof ControlledDepartment){
                controlledDepartment = (ControlledDepartment) employeeGroups[i];
                if(controlledDepartment.isChanged){
                    employeeGroupSource.store(employeeGroups[i]);
                }
            }
        }
    }

    public EmployeeGroup[] load(){
        EmployeeGroup[] employeeGroups = super.getGroups();
        for(int i = 0; i < super.size(); i++){
            employeeGroupSource.load(employeeGroups[i]);
        }
        return employeeGroups;
    }
}

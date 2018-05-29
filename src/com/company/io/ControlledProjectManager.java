package com.company.io;

import com.company.humanResources.*;

import java.util.Collection;

//todo вместо вызоыва конструктора в методах, используй фабрику, ссылку на которую будет принимать конструктор класса и запоминать ее в поле
public class ControlledProjectManager extends ProjectManager {
    protected Source<EmployeeGroup> employeeGroupSource;
    private EmployeeFactory factory;

    private static Source<EmployeeGroup> DEFAULT_EMPLOYEE_GROUP_SOURCE = null;

    public ControlledProjectManager(EmployeeFactory factory){
        super();
        this.factory = factory;
    }

    public ControlledProjectManager(EmployeeGroup[] employeeGroups, EmployeeFactory factory){
        super(employeeGroups);
        this.factory = factory;
    }

    public Source<EmployeeGroup> getEmployeeGroupSource() {
        return employeeGroupSource;
    }

    public void setEmployeeGroupSource(Source<EmployeeGroup> employeeGroupSource) {
        this.employeeGroupSource = employeeGroupSource;
    }
    @Override
    public boolean add(EmployeeGroup employeeGroup) {
        return employeeGroupSource.create(factory.createProject(employeeGroup.getName(), (Employee[]) employeeGroup.toArray())) &&
                super.add(employeeGroup);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        employeeGroupSource.create(factory.createProject(element.getName(), (Employee[]) element.toArray()));
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
                remove(employeeGroup[i]);
            }
        }
        return true;
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        employeeGroupSource.delete(get(index));
        employeeGroupSource.create(factory.createProject(element.getName(), (Employee[]) element.toArray()));
        return super.set(index, element);
    }

    public void store(){
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) super.toArray();
        ControlledProject controlledProject;
        for(int i = 0; i < super.size(); i++){
            if(employeeGroups[i] instanceof ControlledProject){
                controlledProject = (ControlledProject) employeeGroups[i];
                if(controlledProject.isChanged){
                    employeeGroupSource.store(employeeGroups[i]);
                }
            }
        }
    }

    public void load(){
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) super.toArray();
        for(int i = 0; i < super.size(); i++){
            employeeGroupSource.load(employeeGroups[i]);
        }
    }
}

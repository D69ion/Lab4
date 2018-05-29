package com.company.io;

import com.company.humanResources.*;

import java.util.Collection;

//todo вместо вызоыва конструктора в методах, используй фабрику, ссылку на которую будет принимать конструктор класса и запоминать ее в поле
public class ControlledDepartmentManager extends DepartmentManager {
    protected Source<EmployeeGroup> employeeGroupSource;
    private EmployeeFactory factory;

    private static Source<EmployeeGroup> DEFAULT_EMPLOYEE_GROUP_SOURCE = null;

    public ControlledDepartmentManager(EmployeeFactory factory){
        super();
        employeeGroupSource = DEFAULT_EMPLOYEE_GROUP_SOURCE;
        this.factory = factory;
    }

    public ControlledDepartmentManager(String name, EmployeeFactory factory){
        super(name);
        employeeGroupSource = DEFAULT_EMPLOYEE_GROUP_SOURCE;
        this.factory = factory;
    }

    public ControlledDepartmentManager(String name, int size, EmployeeFactory factory){
        super(name, size);
        employeeGroupSource = DEFAULT_EMPLOYEE_GROUP_SOURCE;
        this.factory = factory;
    }

    public ControlledDepartmentManager(String name, Department[] groups, EmployeeFactory factory){
        super(name, groups);
        employeeGroupSource = DEFAULT_EMPLOYEE_GROUP_SOURCE;
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
        return employeeGroupSource.create(factory.createDepartment(employeeGroup.getName(), (Employee[]) employeeGroup.toArray())) &&
                super.add(employeeGroup);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        employeeGroupSource.create(factory.createDepartment(element.getName(), (Employee[]) element.toArray()));
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
        return employeeGroupSource.delete(getEmployeeGroup(groupName)) && super.removeGroup(groupName);
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
        employeeGroupSource.create(factory.createDepartment(element.getName(), (Employee[]) element.toArray()));
        return super.set(index, element);
    }

    public void store(){
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) super.toArray();
        ControlledDepartment controlledDepartment;
        for(int i = 0; i < super.getSize(); i++){
            if(employeeGroups[i] instanceof ControlledDepartment){
                controlledDepartment = (ControlledDepartment) employeeGroups[i];
                if(controlledDepartment.isChanged){
                    employeeGroupSource.store(employeeGroups[i]);
                }
            }
        }
    }

    public EmployeeGroup[] load(){
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) super.toArray();
        for(int i = 0; i < getSize(); i++){
            employeeGroupSource.load(employeeGroups[i]);
        }
        return employeeGroups;
    }
}

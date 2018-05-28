package com.company.io;

import com.company.humanResources.*;

import java.util.Collection;

public class ControlledDepartment extends Department{
    protected boolean isChanged;

    public ControlledDepartment(){
        super();
    }

    public ControlledDepartment(String name){
        super(name);
    }

    public ControlledDepartment(String name, int size){
        super(name, size);
    }

    public ControlledDepartment(String name, Employee[] employees){
        super(name, employees);
    }

    protected boolean isChanged() {
        return isChanged;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        isChanged = true;
    }

    @Override
    public void setEmployees(Employee[] employees) {
        super.setEmployees(employees);
        isChanged = true;
    }

    @Override
    public void setSize(int size) {
        super.setSize(size);
        isChanged = true;
    }

    @Override
    public boolean removeEmployee(String name, String surname) {
        if(super.removeEmployee(name, surname)){
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEmployees(JobTitleEnum jobTitle) {
        if(super.removeEmployees(jobTitle)){
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean add(Employee employee) {
        if(super.add(employee)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        //todo сначала проверь результат remove, а затем от него пляши
        if(super.remove(o)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        if(super.addAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        if(super.addAll(index, c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if(super.removeAll(c)){
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if(super.retainAll(c)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        isChanged = true;
        super.clear();
    }

    @Override
    public Employee set(int index, Employee element) {
        isChanged = true;
        return super.set(index, element);
    }

    @Override
    public void add(int index, Employee element) {
        isChanged = true;
        super.add(index, element);
    }

    @Override
    public Employee remove(int index) {
        isChanged = true;
        return super.remove(index);
    }
}

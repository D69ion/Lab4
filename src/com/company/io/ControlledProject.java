package com.company.io;

import com.company.humanResources.*;

import java.util.Collection;

public class ControlledProject extends Project {
    protected boolean isChanged;

    public ControlledProject() {
        super();
    }

    public ControlledProject(String name){
        super(name);
    }

    public ControlledProject(String name, Employee[] employees){
        super(name, employees);
    }

    public boolean isChanged() {
        return isChanged;
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
    public boolean add(Employee employee) {
        if(super.add(employee)) {
            isChanged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
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

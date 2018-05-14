package com.company.io;

import com.company.humanResources.*;

import java.util.Collection;

public class ControlledProject extends Project {
    protected boolean isChanged;

    private static boolean DEFAULT_IS_CHANGED = false;

    public ControlledProject(String name){
        super(name);
        isChanged = DEFAULT_IS_CHANGED;
    }

    public ControlledProject(String name, Employee[] employees){
        super(name, employees);
        isChanged = DEFAULT_IS_CHANGED;
    }

    public boolean isChanged() {
        return isChanged;
    }

    @Override
    public void addEmployee(Employee newEmployee) throws AlreadyAddedException {
        super.addEmployee(newEmployee);
        isChanged = true;
    }

    @Override
    public boolean removeEmployee(String name, String surname) {
        isChanged = true;
        return super.removeEmployee(name, surname);
    }

    @Override
    public boolean removeEmployee(Employee employee) {
        isChanged = true;
        return super.removeEmployee(employee);
    }

    @Override
    public boolean add(Employee employee) {
        isChanged = true;
        return super.add(employee);
    }

    @Override
    public boolean remove(Object o) {
        isChanged = true;
        return super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        isChanged = true;
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        isChanged = true;
        return super.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        isChanged = true;
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        isChanged = true;
        return super.retainAll(c);
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

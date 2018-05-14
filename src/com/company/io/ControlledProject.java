package com.company.io;

import com.company.humanResources.*;

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
}

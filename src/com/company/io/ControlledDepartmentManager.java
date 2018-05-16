package com.company.io;

import com.company.humanResources.*;

public class ControlledDepartmentManager extends DepartmentManager {
    protected Source<EmployeeGroup> employeeGroupSource;

    private static Source<EmployeeGroup> DEFAULT_EMPLOYEE_GROUP_SOURCE = null;

    public ControlledDepartmentManager(){
        super();
    }

    public ControlledDepartmentManager(String name){
        super(name);
    }

    public ControlledDepartmentManager(String name, int size){
        super(name, size);
    }

    public ControlledDepartmentManager(String name, Department[] groups){
        super(name, groups);
    }

    public Source<EmployeeGroup> getEmployeeGroupSource() {
        return employeeGroupSource;
    }

    public void setEmployeeGroupSource(Source<EmployeeGroup> employeeGroupSource) {
        this.employeeGroupSource = employeeGroupSource;
    }

}

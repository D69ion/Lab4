package com.company.humanResources;

public interface GroupsManager {
    void addGroup(EmployeeGroup employeeGroup);
    int getGroupQuantity();
    int removeGroup(EmployeeGroup employeeGroup);
    boolean removeGroup(String groupName);
    EmployeeGroup getEmployeeGroup(String groupName);
    EmployeeGroup[] getGroups();
    int getEmployeesQuantity();
    int getEmployees(JobTitleEnum jobTitle);
    Employee employeeWithMaxSalary();
    EmployeeGroup getEmployeesGroup(String name, String surname);
}

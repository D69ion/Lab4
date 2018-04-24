package com.company.humanResources;

import java.time.LocalDate;

public interface GroupsManager {
    void addGroup(EmployeeGroup employeeGroup) throws AlreadyAddedException;
    int getGroupQuantity();
    int removeGroup(EmployeeGroup employeeGroup);
    boolean removeGroup(String groupName);
    EmployeeGroup getEmployeeGroup(String groupName);
    EmployeeGroup[] getGroups();
    int getEmployeesQuantity();
    int getEmployees(JobTitleEnum jobTitle);
    Employee employeeWithMaxSalary();
    EmployeeGroup getEmployeesGroup(String name, String surname);
    int getPartTimeEmployeeQuantity();
    int getStaffEmployeeQuantity();
    int getCurrentTravellersQuantity();
    Employee[] getCurrentTravellers(LocalDate startDate, LocalDate endDate);
}

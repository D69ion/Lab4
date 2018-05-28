package com.company.humanResources;

import java.time.LocalDate;
import java.util.List;

//todo убери дубли методов
public interface GroupsManager extends List<EmployeeGroup>{
    boolean removeGroup(String groupName);
    EmployeeGroup getEmployeeGroup(String groupName);
    int getEmployeesQuantity();
    int getEmployees(JobTitleEnum jobTitle);
    Employee employeeWithMaxSalary();
    EmployeeGroup getEmployeesGroup(String name, String surname);
    int getPartTimeEmployeeQuantity();
    int getStaffEmployeeQuantity();
    int getCurrentTravellersQuantity();
    Employee[] getCurrentTravellers(LocalDate startDate, LocalDate endDate);
}

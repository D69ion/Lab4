package com.company.humanResources;

import java.time.LocalDate;

public interface EmployeeGroup{
    String getName();
    void setName(String groupName);
    void addEmployee(Employee employee) throws AlreadyAddedException;
    Employee getEmployee(String name, String surname);
    boolean removeEmployee(String name, String surname);
    boolean removeEmployee(Employee employee);
    Employee getEmployeeWithMaxSalary();
    int getSize();
    Employee[] getEmployees();
    Employee[] getSortedEmployees();
    int getPartTimeEmployeeQuantity();
    int getStaffEmployeeQuantity();
    int getCurrentTravellersQuantity();
    Employee[] getCurrentTravellers(LocalDate startDate, LocalDate endDate);
    String toString();
    boolean equals(Object obj);
    int hashCode();
}

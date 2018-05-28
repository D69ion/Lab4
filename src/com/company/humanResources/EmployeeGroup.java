package com.company.humanResources;

import java.time.LocalDate;
import java.util.List;

//todo убери дубли методов
public interface EmployeeGroup extends List<Employee>{
    String getName();
    void setName(String groupName);
    Employee getEmployee(String name, String surname);
    boolean removeEmployee(String name, String surname);
    Employee getEmployeeWithMaxSalary();
    Employee[] getSortedEmployees();
    int getPartTimeEmployeeQuantity();
    int getStaffEmployeeQuantity();
    int getCurrentTravellersQuantity();
    Employee[] getCurrentTravellers(LocalDate startDate, LocalDate endDate);
    String toString();
    boolean equals(Object obj);
    int hashCode();
}

package com.company.humanResources;

import java.time.LocalDate;
import java.util.List;

//todo убери дубли методов
public interface EmployeeGroup extends List<Employee>{
    String getName();
    void setName(String groupName);
    boolean add(Employee employee); //дубль
    Employee getEmployee(String name, String surname);
    boolean removeEmployee(String name, String surname);
    boolean removeEmployee(Employee employee); //дубль
    Employee getEmployeeWithMaxSalary();
    int getSize(); //дубль
    Employee[] getEmployees(); //дубль toArray()
    Employee[] getSortedEmployees();
    int getPartTimeEmployeeQuantity();
    int getStaffEmployeeQuantity();
    int getCurrentTravellersQuantity();
    Employee[] getCurrentTravellers(LocalDate startDate, LocalDate endDate);
    String toString();
    boolean equals(Object obj);
    int hashCode();
}

package com.company.humanResources;

public interface EmployeeGroup{
    String getName();
    void setName(String groupName);
    void addEmployee(Employee employee);
    Employee getEmployee(String name, String surname);
    boolean removeEmployee(String name, String surname);
    boolean removeEmployee(Employee employee);
    Employee getEmployeeWithMaxSalary();
    int getSize();
    Employee[] getEmployees();
    Employee[] getSortedEmployees();
    String toString();
    boolean equals(Object obj);
    int hashCode();
}

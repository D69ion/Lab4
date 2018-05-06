package com.company.humanResources;

import java.time.LocalDate;
import java.util.*;

public class Department implements EmployeeGroup{
    private String name;
    private Employee[] employees;
    private int size;
	
	private static final String DEFAULT_NAME = "";
	private static final int DEFAULT_SIZE = 8;

    public Department(String name){
        this (name, DEFAULT_SIZE);
	}

    public Department(String name, int size){
        if(size < 0)
            throw new NegativeSizeException("Передана отрицательная длина массива");
        this.name = name;
        this.size = size;
        this.employees = new Employee[this.size];
    }

    public Department(String name, Employee[] employees){
        this.size = employees.length;
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Employee[] getEmployees() {
        Employee[] resEmployees = removeNullElements(employees);
        return resEmployees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Employee getEmployee(String name, String surname){
        for (Employee e: employees
             ) {
            if(e.getName().equals(name) && e.getSurname().equals(surname)){
                return e;
            }
        }
        return null;
    }

    public void addEmployee(Employee newEmployee)throws AlreadyAddedException{
        if(newEmployee == null)
            return;
        for(int i = 0; i < this.size; i++){
            if(newEmployee.equals(this.employees[i])){
                throw new AlreadyAddedException("Сотрудник уже есть в массиве");
            }
        }
        if (size >= employees.length) {
            Employee[] resEmployees = new Employee[employees.length * 2];
            System.arraycopy(employees, 0, resEmployees, 0, employees.length);
            resEmployees[employees.length] = newEmployee;
            this.employees = resEmployees;
            this.size++;
        } else {
            for (int i = size - 1; i < employees.length; i++) {
                if (employees[i] == null) {
                    employees[i] = newEmployee;
                    this.size++;
                    break;
                }
            }
        }
    }

    public boolean removeEmployee(Employee employee){
        int temp = -1;
        for (int i = 0; i < size; i++){
            if (employees[i].equals(employee)) {
                temp = i;
                break;
            }
        }
        if(temp == -1)
            return false;
        System.arraycopy(employees, temp + 1, employees, temp, size - temp - 1);
        for (int i = size - 1; i > -1; i--){
            if(employees[i] == null)
                continue;
            else {
                employees[i] = null;
                size--;
                break;
            }
        }
        return true;
    }

    public boolean removeEmployee(String name, String surname) {
        int temp = -1;
        for (int i = 0; i < size; i++){
            if (employees[i].getName().equals(name) && employees[i].getSurname().equals(surname)) {
                temp = i;
                break;
            }
        }
        if(temp == -1)
            return false;
        System.arraycopy(employees, temp + 1, employees, temp, size - temp - 1);
        for (int i = size - 1; i > -1; i--){
            if(employees[i] == null)
                continue;
            else {
                employees[i] = null;
                size--;
                break;
            }
        }
        return true;
    }

    public Employee[] getEmployees(JobTitleEnum jobTitle){
        Employee[] resEmployees = new Employee[size];
        for(int i = 0; i < size; i++){
            if(employees[i] != null && employees[i].getJobTitle().equals(jobTitle)){
                resEmployees[i] = employees[i];
            }
        }
        resEmployees = removeNullElements(resEmployees);
        return resEmployees;
    }

    public boolean removeEmployees(JobTitleEnum jobTitle){
        Employee[] resEmployees = new Employee[size];
        int temp = 0;
        for (Employee e: employees
                ) {
            if (e == null)
                continue;
            if (e.getJobTitle().equals(jobTitle)){
                e = null;
                temp++;
            }
        }
        size -= temp;
        resEmployees = removeNullElements(resEmployees);
        this.employees = resEmployees;
        return true;
    }

    public Employee getEmployeeWithMaxSalary(){
        int max = 0;
        Employee employee = null;
            for (Employee e: employees
                    ) {
                if(e.getSalary() > max) {
                    max = e.getSalary();
                    employee = e;
                }
            }
        return employee;
    }

    public Employee[] getSortedEmployees(){
        Employee[] resEmployees = getEmployees();
        Arrays.sort(resEmployees);
        return resEmployees;
    }

    @Override
    public int getPartTimeEmployeeQuantity() {
        int res = 0;
        for(int i = 0; i < this.size; i++){
            if(this.employees[i] instanceof PartTimeEmployee){
                res++;
            }
        }
        return res;
    }

    @Override
    public int getStaffEmployeeQuantity() {
        int res = 0;
        for(int i = 0; i < this.size; i++){
            if(this.employees[i] instanceof StaffEmployee){
                res++;
            }
        }
        return res;
    }

    @Override
    public int getCurrentTravellersQuantity() {
        int res = 0;
        for(int i = 0; i < this.size; i++){
            if(this.employees[i] instanceof PartTimeEmployee){
                if(((StaffEmployee)(this.employees[i])).isTravelNow()){
                    res++;
                }
            }
        }
        return res;
    }

    @Override
    public Employee[] getCurrentTravellers(LocalDate startDate, LocalDate endDate) {
        Employee[] res = new Employee[getStaffEmployeeQuantity()];
        int count = -1;
        for(int i = 0; i < this.size; i++){
            if(this.employees[i] instanceof PartTimeEmployee){
                if(((StaffEmployee)(this.employees[i])).getTravelDaysQuantityFromTimeLapse(startDate, endDate) > 0){
                    res[++count] = this.employees[i];
                }
            }
        }
        return res;
    }

    public JobTitleEnum[] getJobTitles(){
        JobTitleEnum[] jobTitleEnums = new JobTitleEnum[13];
        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < 13; j++){
                if(employees[i].getJobTitle() != jobTitleEnums[j]){
                    jobTitleEnums[j] = employees[i].getJobTitle();
                }
            }
        }
        return jobTitleEnums;
    }

    public Employee[] businessTravellers(){
        Employee[] res = new Employee[this.size];
        for(int i = 0; i < this.size; i++){
            if(((StaffEmployee)this.employees[i]).getTravels().length != 0){
                res[i] = this.employees[i];
            }
        }
        return removeNullElements(res);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Department ").append(name).append(": ").append(size).append("\r\n");
        for(int i = 0; i< size; i++)
            res.append(employees[i].toString()).append("\r\n");
        return res.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(!(obj instanceof Department)) return false;
        Department department = (Department)obj;
        if(!(this.name.equals(department.getName()))) return false;
        if(!(this.size == department.getSize())) return false;
        int temp = 0;
        Employee[] departmentEmployees = department.getEmployees();
        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                if(this.employees[i].equals(departmentEmployees[j]))
                    temp++;
            }
        }
        if(temp == this.size) return true;
        else return false;
    }

    @Override
    public int hashCode(){
        int hash = name.hashCode() ^ size;
        for(int i = 0; i < size; i++)
            hash ^= employees[i].hashCode();
        return hash;
    }

    private Employee[] removeNullElements(Employee[] employees){
        int count = 0;
        for (Employee e: employees
             ) {
            if (e == null)
                count++;
        }
        Employee[] resEmployees = new Employee[employees.length - count];
        count = 0;
        for (Employee e: employees
             ) {
            if(e != null){
                resEmployees[count] = e;
                count++;
            }
        }
        return resEmployees;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if(this.size == 0)
            return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        Employee employee = (Employee) o;
        for(int i = 0; i < this.size; i++){
            if(!this.employees[i].equals(employee))
                return false;
        }
        return true;
    }

    @Override
    public Iterator<Employee> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return this.employees;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) this.employees;
    }

    @Override
    public boolean add(Employee employee) {
        try {
            this.addEmployee(employee);
        }
        catch (AlreadyAddedException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return removeEmployee((Employee) o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Employee get(int index) {
        return null;
    }

    @Override
    public Employee set(int index, Employee element) {
        return null;
    }

    @Override
    public void add(int index, Employee element) {

    }

    @Override
    public Employee remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Employee> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Employee> listIterator(int index) {
        return null;
    }

    @Override
    public List<Employee> subList(int fromIndex, int toIndex) {
        return null;
    }
}

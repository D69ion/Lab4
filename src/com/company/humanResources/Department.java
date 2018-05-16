package com.company.humanResources;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Department implements EmployeeGroup, Serializable{
    private String name;
    private Employee[] employees;
    private int size;
	
	private static final String DEFAULT_NAME = "";
	private static final int DEFAULT_SIZE = 8;

	public Department(){
	    this (DEFAULT_NAME, DEFAULT_SIZE);
    }

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
        for(int i = 0; i < this.size; i++){
            if(employees[i].getName().equals(name) && employees[i].getSurname().equals(surname))
                return employees[i];
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
        for(int i = 0; i < this.size; i++){
            if(employees[i].getSalary() > max) {
                max = employees[i].getSalary();
                employee = employees[i];
            }
        }
        return employee;
    }

    public Employee[] getSortedEmployees(){
        Employee[] resEmployees = getEmployees();
        Arrays.sort(resEmployees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.compareTo(o2);
            }
        }.reversed());
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
        for(int i = 0; i < this.size; i++){
            if (employees[i] == null)
                count++;
        }
        Employee[] resEmployees = new Employee[employees.length - count];
        count = 0;
        for(int i = 0; i < this.size; i++){
            if(employees[i] != null){
                resEmployees[count] = employees[i];
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
        boolean b = false;
        for(int i = 0; i < this.size; i++){
            if(this.employees[i].equals(employee))
                b = true;
        }
        return b;
    }

    @Override
    public Iterator<Employee> iterator() {
        return new Iterator<Employee>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if(index < size)
                    return true;
                return false;
            }

            @Override
            public Employee next() {
                if(this.hasNext()){
                    return employees[index++];
                }
                return null;
            }
        };
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
        Employee[] employees = (Employee[]) c.toArray();
        boolean b = false;
        for(int i = 0; i < employees.length; i++){
            for(int j = 0; j < this.size; j++){
                if(employees[i].equals(this.employees[j]))
                    b = true;
            }
            if(!b)
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        Employee[] employees = (Employee[]) c.toArray();
        try {
            for (int i = 0; i < employees.length; i++) {
                this.addEmployee(employees[i]);
            }
        }
        catch (AlreadyAddedException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        Employee[] newEmployees = (Employee[]) c.toArray();
        if(this.size == this.employees.length || this.size + newEmployees.length < this.employees.length){
            Employee[] employees = new Employee[this.size * 2];
            System.arraycopy(this.employees, 0, employees, 0, index);
            for(int i = 0; i < newEmployees.length; i++){
                this.employees[index] = newEmployees[i];
                index++;
            }
            System.arraycopy(this.employees, index, employees, index, this.size - (index - newEmployees.length));
            this.employees = employees;
            return true;
        }
        if(this.size + newEmployees.length >= this.employees.length){
            System.arraycopy(this.employees, 0, this.employees, 0, index);
            for(int i = 0; i < newEmployees.length; i++){
                this.employees[index] = newEmployees[i];
                index++;
            }
            System.arraycopy(this.employees, index, this.employees, index, this.size - (index - newEmployees.length));
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Employee[] employees = (Employee[]) c.toArray();
        for(int i = 0; i < employees.length; i++){
            if(!this.remove(employees[i]))
                return false;
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Employee[] resEmployees = new Employee[this.size];
        Employee[] employees = (Employee[]) c.toArray();
        int count = 0;
        for(int i = 0; i < employees.length; i++){
            for(int j = 0; j < this.size; j++){
                if(employees[i].equals(this.employees[j])){
                    resEmployees[count] = employees[i];
                    count++;
                }
            }
        }
        if(count == 0)
            return false;
        this.employees = resEmployees;
        return true;
    }

    @Override
    public void clear() {
        this.employees = null;
    }

    @Override
    public Employee get(int index) {
        return this.employees[index];
    }

    @Override
    public Employee set(int index, Employee element) {
        Employee employee = this.employees[index] ;
        this.employees[index] = element;
        return employee;
    }

    @Override
    public void add(int index, Employee element) {
        if(this.employees[index] == null){
            this.employees[index] = element;
            return;
        }
        if(this.size == this.employees.length){
            Employee[] employees = new Employee[this.size * 2];
            System.arraycopy(this.employees, 0, employees, 0, index);
            employees[index] = element;
            System.arraycopy(this.employees, index, employees, index + 1, this.size - index);
            return;
        }
        System.arraycopy(this.employees, index, this.employees, index + 1, this.size - index);
        this.employees[index] = element;
    }

    @Override
    public Employee remove(int index) {
        Employee employee = this.employees[index];
        this.remove(employee);
        return employee;
    }

    @Override
    public int indexOf(Object o) {
        Employee employee = (Employee) o;
        for(int i = 0; i < this.size; i++){
            if(employee.equals(this.employees[i]))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Employee employee = (Employee) o;
        for(int i = this.size - 1; i > 0; i--){
            if(employee.equals(this.employees[i]))
                return i;
        }
        return -1;
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

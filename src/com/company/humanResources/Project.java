package com.company.humanResources;

import java.time.LocalDate;
import java.util.*;

public class Project implements EmployeeGroup{
    private String name;
    private ArrayList<Employee> employeesList;
    private int size;

    private static final String DEFAULT_NAME = "";
    private static final ArrayList DEFAULT_EMPLOYEES_LIST = null;
    private static final int DEFAULT_SIZE = 0;

    public Project(String name) {
        this.name = name;
        this.employeesList = DEFAULT_EMPLOYEES_LIST;
        this.size = DEFAULT_SIZE;
    }

    public Project(String name, Employee[] employees) {
        this.name = name;
        this.size = employees.length;
        for (int i = 0; i < employees.length; i++) {
            this.employeesList.add(employees[i]);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public Employee[] getEmployees(){
        return (Employee[]) this.employeesList.toArray();
    }

    public Employee[] getSortedEmployees(){
        Employee[] employees = getEmployees();
        Arrays.sort(employees);
        return employees;
    }

    @Override
    public int getPartTimeEmployeeQuantity() {
        Employee[] employees = getEmployees();
        int res = 0;
        for(int i = 0; i < this.size; i++){
            if(employees[i] instanceof PartTimeEmployee){
                res++;
            }
        }
        return res;
    }

    @Override
    public int getStaffEmployeeQuantity() {
        Employee[] employees = getEmployees();
        int res = 0;
        for(int i = 0; i < this.size; i++){
            if(employees[i] instanceof StaffEmployee){
                res++;
            }
        }
        return res;
    }

    @Override
    public int getCurrentTravellersQuantity() {
        Employee[] employees = getEmployees();
        int res = 0;
        for(int i = 0; i < this.size; i++){
            if(employees[i] instanceof StaffEmployee){
                if(((StaffEmployee)(employees[i])).isTravelNow()){
                    res++;
                }
            }
        }
        return res;
    }

    @Override
    public Employee[] getCurrentTravellers(LocalDate startDate, LocalDate endDate) {
        Employee[] employees = getEmployees();
        Employee[] res = new Employee[getStaffEmployeeQuantity()];
        int count = -1;
        for(int i = 0; i < this.size; i++){
            if(employees[i] instanceof PartTimeEmployee){
                if(((StaffEmployee)(employees[i])).getTravelDaysQuantityFromTimeLapse(startDate,endDate) > 0){
                    res[++count] = employees[i];
                }
            }
        }
        return res;
    }

    public void addEmployee(Employee employee) throws AlreadyAddedException {
        for (Employee e: this.employeesList
             ) {
            if(employee.equals(e))
                throw new AlreadyAddedException("Добавляемый сотрудник уже есть в массиве");
        }
        this.employeesList.add(employee);
    }

    public Employee getEmployee(String name, String surname) {
        Employee[] employees = getEmployees();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getName().equals(name) && employees[i].getSurname().equals(surname))
                return employees[i];
        }
        return null;
    }

    public boolean removeEmployee(String name, String surname) {
        for (Employee e: this.employeesList
             ) {
            if(e.getName().equals(name) && e.getSurname().equals(surname)){
                this.employeesList.remove(e);
                return true;
            }
        }
        return false;
    }

    public boolean removeEmployee(Employee employee){
        this.employeesList.remove(employee);
        return true;
    }

    public Employee getEmployeeWithMaxSalary(){
        Employee resEmployee = null;
        int max = 0;
        for (Employee e: this.employeesList
             ) {
            if(e.getSalary() > max)
                resEmployee = e;
        }
        return resEmployee;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Project ").append(getName()).append(": ").append(getSize()).append("\r\n");
        for (Employee e: this.employeesList
             ) {
            res.append(e.toString()).append("\r\n");
        }
        return res.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(!(obj instanceof Project)) return false;
        Project project = (Project)obj;
        if(!(this.name.equals(project.getName()))) return false;
        if(!(this.size == project.getSize())) return false;
        int temp = 0;
        for (Employee e: this.employeesList
             ) {
            for (Employee j: project.employeesList
                 ) {
                if(e.equals(j))
                    temp++;
            }
        }
        if(temp == this.size) return true;
        else return false;
    }

    @Override
    public int hashCode(){
        int hash = this.size ^ this.name.hashCode();
        for (Employee e: this.employeesList
             ) {
            hash ^= e.hashCode();
        }
        return hash;
    }

    @Override
    public int size() {
        return this.employeesList.size();
    }

    @Override
    public boolean isEmpty() {
        if(this.employeesList == null)
            return false;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        return this.employeesList.contains(o);
    }

    @Override
    public Iterator<Employee> iterator() {
        return this.employeesList.iterator();
    }

    @Override
    public Object[] toArray() {
        return getEmployees();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) getEmployees();
    }

    @Override
    public boolean add(Employee employee) {
        return this.employeesList.add(employee);
    }

    @Override
    public boolean remove(Object o) {
        return this.employeesList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.employeesList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        return this.employeesList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        return this.employeesList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.employeesList.retainAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.employeesList.retainAll(c);
    }

    @Override
    public void clear() {
        this.employeesList.clear();
    }

    @Override
    public Employee get(int index) {
        return this.employeesList.get(index);
    }

    @Override
    public Employee set(int index, Employee element) {
        return this.employeesList.set(index, element);
    }

    @Override
    public void add(int index, Employee element) {
        this.employeesList.add(index, element);
    }

    @Override
    public Employee remove(int index) {
        return this.employeesList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.employeesList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.employeesList.lastIndexOf(o);
    }

    @Override
    public ListIterator<Employee> listIterator() {
        return this.employeesList.listIterator();
    }

    @Override
    public ListIterator<Employee> listIterator(int index) {
        return this.employeesList.listIterator(index);
    }

    @Override
    public List<Employee> subList(int fromIndex, int toIndex) {
        return this.employeesList.subList(fromIndex, toIndex);
    }
}

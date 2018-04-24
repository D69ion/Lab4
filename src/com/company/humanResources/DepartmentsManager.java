package com.company.humanResources;

import java.time.LocalDate;

public class DepartmentsManager implements GroupsManager{
    private String name;
    private EmployeeGroup[] groups;
    private int size;

    private static final String DEFAULT_GROUP_NAME = "";
    private static final int DEFAULT_SIZE = 0;

    public DepartmentsManager(String name){
        this(name, new Department[DEFAULT_SIZE]);
    }

    public DepartmentsManager(String name, int size){
        if(size < 0)
            throw new NegativeSizeException("Передана отрицательная длина массива");
        this.name = name;
        this.groups = new EmployeeGroup[size];
        this.size = size;
    }

    public DepartmentsManager(String name, Department[] groups){
        this.name = name;
        this.groups = groups;
        this.size = groups.length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroups(Department[] groups) {
        this.groups = groups;
    }

    public int getSize(){
        return this.size;
    }

    public void setSize(int size){
        this.size = size;
    }

    @Override
    public int getGroupQuantity() {
        return getSize();
    }

    @Override
    public int getEmployeesQuantity() {
        return getEmployeesCount();
    }

    @Override
    public EmployeeGroup[] getGroups() {
        return this.groups;
    }

    public int getEmployeesCount(){
        int res = 0;
        for (EmployeeGroup d: groups
             ) {
            res += d.getSize();
        }
        return res;
    }

    @Override
    public int getEmployees(JobTitleEnum jobTitle){
        int res = 0;
        for (EmployeeGroup d: groups
                ) {
            for (Employee e: d.getEmployees()
                 ) {
                if(e.getJobTitle() == jobTitle)
                    res++;
            }
        }
        return res;
    }

    @Override
    public void addGroup(EmployeeGroup employeeGroup) throws AlreadyAddedException {
        for(int i = 0; i < this.size; i++){
            if(employeeGroup.equals(this.groups[i]))
                throw new AlreadyAddedException("Добавляемая группа уже есть в массиве");
        }
        if (size >= groups.length){
            EmployeeGroup[] resDepartments = new Department[groups.length * 2];
            System.arraycopy(groups, 0, resDepartments, 0, groups.length);
            resDepartments[groups.length] = employeeGroup;
            groups = resDepartments;
            this.size++;
        }
        else{
            for (int i = 0; i < groups.length; i++){
                if (groups[i] == null){
                    groups[i] = employeeGroup;
                    this.size++;
                    break;
                }
            }
        }
    }

    @Override
    public boolean removeGroup(String groupName){
        int temp = -1;
        for (int i = 0; i < this.size; i++){
            if (this.groups[i].getName().equals(groupName))
                temp = i;
        }
        if(temp == -1)
            return false;
        System.arraycopy(this.groups, temp + 1, this.groups, temp, this.size - temp - 1);
        for (int i = this.size - 1; i > -1; i--){
            if(this.groups[i] == null)
                continue;
            else {
                this.groups[i] = null;
                this.size--;
                break;
            }
        }
        return true;
    }

    @Override
    public int removeGroup(EmployeeGroup department){
       for(int i = 0; i < this.size; i++){
           if(this.groups[i].equals(department)){
               System.arraycopy(groups, i + 1, groups, i, size - i - 1);
               for (int j = size - 1; j > -1; j--){
                   if(groups[j] == null)
                       continue;
                   else {
                       groups[j] = null;
                       size--;
                       break;
                   }
               }
               this.size--;
               return this.size;
           }
       }
        return this.size;
    }

    public EmployeeGroup getEmployeeGroup(String groupName){
        for (EmployeeGroup d: groups
                ) {
            if(d.getName().equals(groupName))
                return d;
        }
        return null;
    }

    @Override
    public Employee employeeWithMaxSalary(){
        int max = 0;
        Employee employee = null;
        for (EmployeeGroup d: groups
             ) {
            if (d == null)
                continue;
            for (Employee e: d.getEmployees()
                 ) {
                if(e.getSalary() > max) {
                    max = e.getSalary();
                    employee = e;
                }
            }
        }
        return employee;
    }

    @Override
    public Department getEmployeesGroup(String name, String surname){
        for (EmployeeGroup d: groups
                ) {
            for (Employee e: d.getEmployees()
                    ) {
                if (e.getName().equals(name))
                    if (e.getSurname().equals(surname))
                        return (Department) d;
            }
        }
        return null;
    }

    @Override
    public int getPartTimeEmployeeQuantity() {
        Employee[] employees;
        int res = 0;
        for(int i = 0; i < this.size; i++) {
            employees = this.groups[i].getEmployees();
            for (int j = 0; j < this.size; j++) {
                if (employees[j] instanceof PartTimeEmployee) {
                    res++;
                }
            }
        }
        return res;
    }

    @Override
    public int getStaffEmployeeQuantity() {
        Employee[] employees;
        int res = 0;
        for(int i = 0; i < this.size; i++) {
            employees = this.groups[i].getEmployees();
            for (int j = 0; j < this.size; j++) {
                if (employees[j] instanceof StaffEmployee) {
                    res++;
                }
            }
        }
        return res;
    }

    @Override
    public int getCurrentTravellersQuantity() {
        Employee[] employees;
        int res = 0;
        for(int i = 0; i < this.size; i++) {
            employees = this.groups[i].getEmployees();
            for (int j = 0; j < this.size; j++) {
                if (employees[j] instanceof PartTimeEmployee) {
                    if (((StaffEmployee) (employees[j])).isTravelNow()) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    @Override
    public Employee[] getCurrentTravellers(LocalDate startDate, LocalDate endDate) {
        Employee[] res = new Employee[getStaffEmployeeQuantity()];
        Employee[] employees;
        int count = -1;
        for(int i = 0; i < this.size; i++) {
            employees = this.groups[i].getEmployees();
            for (int j = 0; j < this.size; j++) {
                if (employees[j] instanceof PartTimeEmployee) {
                    if (((StaffEmployee) (employees[j])).getTravelDaysQuantityFromTimeLapse(startDate, endDate) > 0) {
                        res[++count] = employees[j];
                    }
                }
            }
        }
        return res;
    }
}

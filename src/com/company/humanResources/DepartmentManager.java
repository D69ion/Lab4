package com.company.humanResources;

import java.time.LocalDate;
import java.util.*;

public class DepartmentManager implements GroupsManager{
    private String name;
    private EmployeeGroup[] groups;
    private int size;

    private static final String DEFAULT_GROUP_NAME = "";
    private static final int DEFAULT_SIZE = 0;

    public DepartmentManager(){
        this(DEFAULT_GROUP_NAME, DEFAULT_SIZE);
    }

    public DepartmentManager(String name){
        this(name, new Department[DEFAULT_SIZE]);
    }

    public DepartmentManager(String name, int size){
        if(size < 0)
            throw new NegativeSizeException("Передана отрицательная длина массива");
        this.name = name;
        this.groups = new EmployeeGroup[size];
        this.size = size;
    }

    public DepartmentManager(String name, Department[] groups){
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
        EmployeeGroup employeeGroup = (EmployeeGroup) o;
        boolean b = false;
        for(int i = 0; i < this.size; i++){
            if(this.groups[i].equals(employeeGroup))
                b = true;
        }
        return b;
    }

    @Override
    public Iterator<EmployeeGroup> iterator() {
        return new Iterator<EmployeeGroup>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if(index < size)
                    return true;
                return false;
            }

            @Override
            public EmployeeGroup next() {
                if(this.hasNext()){
                    return groups[index++];
                }
                return null;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return this.groups;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) this.groups;
    }

    @Override
    public boolean add(EmployeeGroup employee) {
        try {
            this.addGroup(employee);
        }
        catch (AlreadyAddedException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(removeGroup((EmployeeGroup) o) > 0)
            return true;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) c.toArray();
        boolean b = false;
        for(int i = 0; i < employeeGroups.length; i++){
            for(int j = 0; j < this.size; j++){
                if(employeeGroups[i].equals(this.groups[j]))
                    b = true;
            }
            if(!b)
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        EmployeeGroup[] employeeGroups = (EmployeeGroup[]) c.toArray();
        try {
            for (int i = 0; i < employeeGroups.length; i++) {
                this.addGroup(employeeGroups[i]);
            }
        }
        catch (AlreadyAddedException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        EmployeeGroup[] newEmployeeGroups = (EmployeeGroup[]) c.toArray();
        if(this.size == this.groups.length || this.size + newEmployeeGroups.length < this.groups.length){
            EmployeeGroup[] employeeGroups = new EmployeeGroup[this.size * 2];
            System.arraycopy(this.groups, 0, employeeGroups, 0, index);
            for(int i = 0; i < newEmployeeGroups.length; i++){
                this.groups[index] = newEmployeeGroups[i];
                index++;
            }
            System.arraycopy(this.groups, index, employeeGroups, index, this.size - (index - newEmployeeGroups.length));
            this.groups = employeeGroups;
            return true;
        }
        if(this.size + newEmployeeGroups.length >= this.groups.length){
            System.arraycopy(this.groups, 0, this.groups, 0, index);
            for(int i = 0; i < newEmployeeGroups.length; i++){
                this.groups[index] = newEmployeeGroups[i];
                index++;
            }
            System.arraycopy(this.groups, index, this.groups, index, this.size - (index - newEmployeeGroups.length));
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
        EmployeeGroup[] resEmployees = new EmployeeGroup[this.size];
        EmployeeGroup[] employees = (EmployeeGroup[]) c.toArray();
        int count = 0;
        for(int i = 0; i < employees.length; i++){
            for(int j = 0; j < this.size; j++){
                if(employees[i].equals(this.groups[j])){
                    resEmployees[count] = employees[i];
                    count++;
                }
            }
        }
        if(count == 0)
            return false;
        this.groups = resEmployees;
        return true;
    }

    @Override
    public void clear() {
        this.groups = null;
    }

    @Override
    public EmployeeGroup get(int index) {
        return this.groups[index];
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        EmployeeGroup employeeGroup = this.groups[index] ;
        this.groups[index] = element;
        return employeeGroup;
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        if(this.groups[index] == null){
            this.groups[index] = element;
            return;
        }
        if(this.size == this.groups.length){
            EmployeeGroup[] employeeGroups = new EmployeeGroup[this.size * 2];
            System.arraycopy(this.groups, 0, employeeGroups, 0, index);
            employeeGroups[index] = element;
            System.arraycopy(this.groups, index, employeeGroups, index + 1, this.size - index);
            return;
        }
        System.arraycopy(this.groups, index, this.groups, index + 1, this.size - index);
        this.groups[index] = element;
    }

    @Override
    public EmployeeGroup remove(int index) {
        EmployeeGroup employeeGroup = this.groups[index];
        this.remove(employeeGroup);
        return employeeGroup;
    }

    @Override
    public int indexOf(Object o) {
        EmployeeGroup employeeGroup = (EmployeeGroup) o;
        for(int i = 0; i < this.size; i++){
            if(employeeGroup.equals(this.groups[i]))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        EmployeeGroup employeeGroup = (EmployeeGroup) o;
        for(int i = this.size - 1; i > 0; i--){
            if(employeeGroup.equals(this.groups[i]))
                return i;
        }
        return -1;
    }

    //todo а чем тебе эти методы не угодили?
    @Override
    public ListIterator<EmployeeGroup> listIterator() {
        return null;
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator(int index) {
        return null;
    }

    @Override
    public List<EmployeeGroup> subList(int fromIndex, int toIndex) {
        return null;
    }
}

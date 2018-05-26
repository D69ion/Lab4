package com.company.humanResources;

import java.time.LocalDate;
import java.util.*;

//todo свой список надо было юзать
public class ProjectManager implements GroupsManager{
    private ArrayList<EmployeeGroup> groups;
    private int size;

    private static final int DEFAULT_SIZE = 0;

    public ProjectManager(){
        this.groups = null;
        this.size = DEFAULT_SIZE;
    }

    public ProjectManager(EmployeeGroup[] groups){
        this.size = groups.length;
        for (EmployeeGroup employeeGroup: groups
             ) {
            this.groups.add(employeeGroup);
        }
    }

    @Override
    public void addGroup(EmployeeGroup employeeGroup) throws AlreadyAddedException {
        for (EmployeeGroup employeeGroup1: this.groups
             ) {
            if(employeeGroup.equals(employeeGroup1))
                throw new AlreadyAddedException("Добавляемая группа уже есть в списке");
        }
        this.size++;
        this.groups.add(employeeGroup);
    }

    @Override
    public int getGroupQuantity() {
        return this.size;
    }

    @Override
    public int removeGroup(EmployeeGroup employeeGroup) {
        this.groups.remove(employeeGroup);
        this.size--;
        return this.size;
    }

    @Override
    public boolean removeGroup(String groupName) {//??????
        int temp = -1;
        EmployeeGroup[] employeeGroups = getGroups();
        for (int i = 0; i < size; i++){
            if (employeeGroups[i].getName().equals(groupName))
                temp = i;
        }
        if(temp == -1)
            return false;
        System.arraycopy(groups, temp + 1, groups, temp, size - temp - 1);
        for (int i = size - 1; i > -1; i--){
            if(employeeGroups[i] == null)
                continue;
            else {
                employeeGroups[i] = null;
                size--;
                break;
            }
        }
        return true;
    }

    @Override
    public EmployeeGroup getEmployeeGroup(String groupName) {
        for (EmployeeGroup employeeGroup: this.groups
             ) {
            if(employeeGroup.getName().equals(groupName))
                return employeeGroup;
        }
        return null;
    }

    @Override
    public EmployeeGroup[] getGroups() {
        return (EmployeeGroup[]) this.groups.toArray();
    }

    @Override
    public int getEmployeesQuantity() {
        int res = 0;
        for (EmployeeGroup employeeGroup: this.groups
             ) {
            res += employeeGroup.getSize();
        }
        return res;
    }

    @Override
    public int getEmployees(JobTitleEnum jobTitle) {
        int res = 0;
        Employee[] employees;
        for (EmployeeGroup employeeGroup: this.groups
             ) {
            employees = employeeGroup.getEmployees();
            for(int j = 0; j < employees.length; j++){
                if(employees[j].getJobTitle() == jobTitle)
                    res++;
            }
        }
        return res;
    }

    @Override
    public Employee employeeWithMaxSalary() {
        Employee employee = null;
        Employee temp;
        int max = 0;
        for (EmployeeGroup employeeGroup: this.groups
                ) {
            temp = employeeGroup.getEmployeeWithMaxSalary();
            if(max < temp.getSalary()) {
                max = temp.getSalary();
                employee = temp;
            }
        }
        if(employee != null)
            return employee;
        else
            return null;
    }

    @Override
    public EmployeeGroup getEmployeesGroup(String name, String surname) {
        for (EmployeeGroup employeeGroup: this.groups
             ) {
            if(employeeGroup.getEmployee(name, surname) != null)
                return employeeGroup;
        }
        return null;
    }

    @Override
    public int getPartTimeEmployeeQuantity() {
        Employee[] employees;
        int res = 0;
        for (EmployeeGroup employeeGroup : this.groups
                ) {
            employees = employeeGroup.getEmployees();
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
        for (EmployeeGroup employeeGroup: this.groups
             ) {
            employees = employeeGroup.getEmployees();
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
        for (EmployeeGroup employeeGroup: this.groups
             ) {
            employees = employeeGroup.getEmployees();
            for (int j = 0; j < this.size; j++) {
                if (employees[j] instanceof StaffEmployee) {
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
        for (EmployeeGroup employeeGroup: this.groups
             ) {
            employees = employeeGroup.getEmployees();
            for (int j = 0; j < this.size; j++) {
                if (employees[j] instanceof StaffEmployee) {
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
        if(this.groups == null)
            return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return this.groups.contains(o);
    }

    @Override
    public Iterator<EmployeeGroup> iterator() {
        return this.groups.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.groups.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.groups.toArray(a);
    }

    @Override
    public boolean add(EmployeeGroup employeeGroup) {
        return this.groups.add(employeeGroup);
    }

    @Override
    public boolean remove(Object o) {
        return this.groups.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.groups.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        return this.groups.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        return this.groups.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.groups.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.groups.retainAll(c);
    }

    @Override
    public void clear() {
        this.groups.clear();
    }

    @Override
    public EmployeeGroup get(int index) {
        return this.groups.get(index);
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        return this.groups.set(index, element);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        this.groups.add(index, element);
    }

    @Override
    public EmployeeGroup remove(int index) {
        return this.groups.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.groups.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.groups.lastIndexOf(o);
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator() {
        return this.groups.listIterator();
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator(int index) {
        return this.groups.listIterator(index);
    }

    @Override
    public List<EmployeeGroup> subList(int fromIndex, int toIndex) {
        return this.groups.subList(fromIndex, toIndex);
    }
}

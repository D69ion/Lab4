package com.company.humanResources;

import java.time.LocalDate;

public class Project implements EmployeeGroup{
    private String name;
    private List employeesList;
    private int size;

    private static final String DEFAULT_NAME = "";
    private static final List DEFAULT_EMPLOYEES_LIST = null;
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
            this.employeesList.addLast(employees[i]);
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
        return this.employeesList.toArray();
    }

    public Employee[] getSortedEmployees(){
        Employee[] employees = getEmployees();
        QSort(employees, 0, employees.length - 1);
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
            if(employees[i] instanceof PartTimeEmployee){
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
        Employee[] employees = getEmployees();
        for (int i = 0; i < this.size; i++){
            if(employee.equals(employees[i]))
                throw new AlreadyAddedException("Добавляемый сотрудник уже есть в массиве");
        }
        this.employeesList.addLast(employee);
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
        Employee[] employees = getEmployees();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getName().equals(name) && employees[i].getSurname().equals(surname)) {
                employeesList.deleteElement(employees[i]);
                return true;
            }
        }
        return false;
    }

    public boolean removeEmployee(Employee employee){
        this.employeesList.deleteElement(employee);
        return true;
    }

    public Employee getEmployeeWithMaxSalary(){
        Employee resEmployee = null;
        Employee[] employees = getEmployees();
        int max = 0;
        for(int i = 0; i < employees.length; i++){
            if(employees[i].getSalary() > max)
                resEmployee = employees[i];
        }
        return resEmployee;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Project ").append(getName()).append(": ").append(getSize()).append("\r\n");
        Employee[] employees = getEmployees();
        for(int i = 0; i < employees.length; i++){
            res.append(employees[i].toString()).append("\r\n");
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
        Employee[] projectEmployees = project.getEmployees();
        Employee[] employees = this.employeesList.toArray();
        int temp = 0;
        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                if(employees[i].equals(projectEmployees[j]))
                    temp++;
            }
        }
        if(temp == this.size) return true;
        else return false;
    }

    @Override
    public int hashCode(){
        int hash = this.size ^ this.name.hashCode();
        Employee[] employees = getEmployees();
        for(int i = 0; i < employees.length; i++){
            hash ^= employees[i].hashCode();
        }
        return hash;
    }

    private void QSort(Employee[] employees, int low, int high){
        int i = low, j = high; //low = 0; high = array.Length-1
        int pivot = employees[low + (high - low) / 2].getSalary();
        while (i <= j)
        {
            while (employees[i].getSalary() > pivot)
                i++;
            while (employees[j].getSalary() < pivot)
                j--;
            if (i <= j)
            {
                Swap(employees, i, j);
                i++;
                j--;
            }
        }
        if (low < j)
            QSort(employees, low, j);
        if (i < high)
            QSort(employees, i, high);
    }

    private static void Swap(Employee[] array, int i, int j)
    {
        Employee temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private class ListElement {
        ListElement next;
        Employee data;
    }

    private class List {
        private ListElement head;
        private ListElement tail;
        private int length = 0;

        public void addFirst(Employee data) {
            ListElement a = new ListElement();
            a.data = data;
            if (head == null) {
                head = a;
                tail = a;
            } else {
                tail.next = a;
                tail = a;
            }
            length++;
        }

        public void addLast(Employee data) {
            ListElement a = new ListElement();
            a.data = data;
            if (tail == null) {
                head = a;
                tail = a;
            } else {
                tail.next = a;
                tail = a;
            }
            length++;
        }

        public void deleteElement(Employee data) {
            if (head == null)
                return;
            if (head.equals(tail)) {
                head = null;
                tail = null;
                length--;
                return;
            }
            if (head.data.equals(data)) {
                head = head.next;
                length--;
                return;
            }
            ListElement t = head;
            while (t.next != null) {
                if (t.next.data.equals(data)) {
                    if (tail.equals(t.next))
                        tail = t;
                    t.next = t.next.next;
                    length--;
                    return;
                }
                t = t.next;
            }
        }

        public Employee[] toArray() {
            Employee[] res = new Employee[length];
            int count = 0;
            ListElement t = head;
            while (t != null) {
                res[count] = t.data;
                t = t.next;
                count++;
            }
            return res;
        }
    }
}

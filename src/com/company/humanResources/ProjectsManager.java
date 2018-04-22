package com.company.humanResources;

public class ProjectsManager implements GroupsManager{
    private List groups;
    private int size;

    private static final int DEFAULT_SIZE = 0;

    public ProjectsManager(){
        this.groups = null;
        this.size = DEFAULT_SIZE;
    }

    public ProjectsManager(EmployeeGroup[] groups){
        this.size = groups.length;
        for(int i = 0; i < this.size; i++){
            this.groups.addLast(groups[i]);
        }
    }

    @Override
    public void addGroup(EmployeeGroup employeeGroup) {
        this.size++;
        this.groups.addLast(employeeGroup);
    }

    @Override
    public int getGroupQuantity() {
        return this.size;
    }

    @Override
    public int removeGroup(EmployeeGroup employeeGroup) {
        this.groups.deleteElement(employeeGroup);
        this.size--;
        return this.size;
    }

    @Override
    public boolean removeGroup(String groupName) {
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
        EmployeeGroup[] employeeGroups = getGroups();
        for(int i = 0; i < this.size; i++){
            if(employeeGroups[i].getName().equals(groupName))
                return employeeGroups[i];
        }
        return null;
    }

    @Override
    public EmployeeGroup[] getGroups() {
        return this.groups.getGroups();
    }

    @Override
    public int getEmployeesQuantity() {
        int res = 0;
        EmployeeGroup[] employeeGroups = getGroups();
        for(int i = 0; i < this.size; i++){
            res += employeeGroups[i].getSize();
        }
        return res;
    }

    @Override
    public int getEmployees(JobTitleEnum jobTitle) {
        int res = 0;
        EmployeeGroup[] employeeGroups = getGroups();
        Employee[] employees = null;
        for(int i = 0; i < this.size; i++){
           employees = employeeGroups[i].getEmployees();
           for(int j = 0; j < employees.length; j++){
               if(employees[j].getJobTitle() == jobTitle)
                   res++;
           }
        }
        return res;
    }

    @Override
    public Employee employeeWithMaxSalary() {
        EmployeeGroup[] employeeGroups = getGroups();
        Employee employee = employeeGroups[0].getEmployeeWithMaxSalary();
        Employee temp = null;
        for(int i = 1; i < this.size; i++){
            temp = employeeGroups[i].getEmployeeWithMaxSalary();
            if(temp.getSalary() > employee.getSalary())
                employee = temp;
        }
        return employee;
    }

    @Override
    public EmployeeGroup getEmployeesGroup(String name, String surname) {
        EmployeeGroup[] employeeGroups = getGroups();
        for(int i = 0; i < this.size; i++){
            if(employeeGroups[i].getEmployee(name, surname) != null)
                return employeeGroups[i];
        }
        return null;
    }

    private class ListElement {
        ListElement next;
        EmployeeGroup data;
    }

    private class List {
        private ListElement head;
        private ListElement tail;
        private int length = 0;

        public void addFirst(EmployeeGroup data) {
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

        public void addLast(EmployeeGroup data) {
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

        public void deleteElement(EmployeeGroup data) {
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

        public EmployeeGroup[] getGroups(){
            EmployeeGroup[] res = new EmployeeGroup[length];
            int count = 0;
            ListElement t = head;
            while(t != null){
                res[count] = t.data;
                t = t.next;
                count++;
            }
            return res;
        }
    }
}

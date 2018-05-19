package com.company.io;

import com.company.humanResources.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class GroupsManagerTextFileSource extends GroupsManagerFileSource {
    public GroupsManagerTextFileSource() {
        super();
    }

    public GroupsManagerTextFileSource(String path) {
        super(path);
    }

    @Override
    public void load(EmployeeGroup employeeGroup) {
        File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
        try(Scanner scanner = new Scanner(file)){
            Employee[] employees;
            StaffEmployee staffEmployee;
            PartTimeEmployee partTimeEmployee;
            BusinessTravel businessTravel;
            String _class = scanner.nextLine();
            if(_class.equals("Department")){
                Department department = (Department) employeeGroup;
                int size = scanner.nextInt();
                employees = new Employee[size];
                for(int i = 0; i < size; i++){
                    _class = scanner.nextLine();
                    employees[i].setName(scanner.nextLine());
                    employees[i].setSurname(scanner.nextLine());
                    employees[i].setJobTitle(JobTitleEnum.valueOf(scanner.nextLine()));
                    employees[i].setSalary(scanner.nextInt());
                    if(_class.equals("StaffEmployee")){
                        staffEmployee = (StaffEmployee) employees[i];
                        staffEmployee.setBonus(scanner.nextInt());
                        for(int j = 0; j < scanner.nextInt(); j++){
                            int compensation = scanner.nextInt();
                            String destination = scanner.nextLine();
                            LocalDate begin = LocalDate.parse(scanner.nextLine());
                            LocalDate end = LocalDate.parse(scanner.nextLine());
                            String description = scanner.nextLine();
                            businessTravel = new BusinessTravel(compensation, begin, end,
                                    description, destination);
                            staffEmployee.addTravel(businessTravel);
                        }
                        employees[i] = staffEmployee;
                    }
                    else{
                        partTimeEmployee = (PartTimeEmployee) employees[i];
                        partTimeEmployee.setBonus(scanner.nextInt());
                        employees[i] = partTimeEmployee;
                    }
                }
                department.setEmployees(employees);
                department.setSize(employees.length);
                employeeGroup = department;
            }
            else{
                Project project = (Project) employeeGroup;
                int size = scanner.nextInt();
                Employee employee = new Employee() {
                    @Override
                    public int getBonus() {
                        return 0;
                    }

                    @Override
                    public void setBonus(int bonus) {

                    }
                };
                for(int i = 0; i < size; i++){
                    _class = scanner.nextLine();
                    employee.setName(scanner.nextLine());
                    employee.setSurname(scanner.nextLine());
                    employee.setJobTitle(JobTitleEnum.valueOf(scanner.nextLine()));
                    employee.setSalary(scanner.nextInt());
                    if(_class.equals("StaffEmployee")){
                        staffEmployee = (StaffEmployee) employee;
                        staffEmployee.setBonus(scanner.nextInt());
                        for(int j = 0; j < scanner.nextInt(); j++){
                            int compensation = scanner.nextInt();
                            String destination = scanner.nextLine();
                            LocalDate begin = LocalDate.parse(scanner.nextLine());
                            LocalDate end = LocalDate.parse(scanner.nextLine());
                            String description = scanner.nextLine();
                            businessTravel = new BusinessTravel(compensation, begin, end,
                                    description, destination);
                            staffEmployee.addTravel(businessTravel);
                        }
                        project.add(staffEmployee);
                    }
                    else{
                        partTimeEmployee = (PartTimeEmployee) employee;
                        partTimeEmployee.setBonus(scanner.nextInt());
                        project.add(partTimeEmployee);
                    }
                }
                employeeGroup = project;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (IllegalDatesException i){
            i.printStackTrace();
        }
    }

    @Override
    public void store(EmployeeGroup employeeGroup) {
        try {
            File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(employeeGroup.getClass().getSimpleName());
            printWriter.println(employeeGroup.size());
            Employee[] employees = employeeGroup.getEmployees();
            StaffEmployee staffEmployee;
            PartTimeEmployee partTimeEmployee;
            BusinessTravel[] businessTravels;
            for(int i = 0; i < employeeGroup.size(); i++){
                printWriter.println(employees[i].getClass().getSimpleName());
                printWriter.println(employees[i].getName());
                printWriter.println(employees[i].getSurname());
                printWriter.println(employees[i].getJobTitle());
                printWriter.print(employees[i].getSalary());
                if(employees[i] instanceof StaffEmployee){
                    staffEmployee = (StaffEmployee) employees[i];
                    printWriter.print(staffEmployee.getBonus());
                    businessTravels = staffEmployee.getTravels();
                    printWriter.println(businessTravels.length);
                    for(int j = 0; j < businessTravels.length; j++){
                        printWriter.print(businessTravels[j].getCompensation());
                        printWriter.println(businessTravels[j].getDestination());
                        printWriter.println(businessTravels[j].getBeginTravel().toString());
                        printWriter.println(businessTravels[j].getEndTravel().toString());
                        printWriter.println(businessTravels[j].getDescription());
                    }
                }
                else{
                    partTimeEmployee = (PartTimeEmployee) employees[i];
                    printWriter.print(partTimeEmployee.getBonus());
                }
            }
            printWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(EmployeeGroup employeeGroup) {
        File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
        return file.delete();
    }

    @Override
    public boolean create(EmployeeGroup employeeGroup) {
        try {
            File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
            if(file.createNewFile()){
                store(employeeGroup);
                return true;
            }
            else
                return false;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}

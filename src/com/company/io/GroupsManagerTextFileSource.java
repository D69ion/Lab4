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
        try{
            File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
            if(!file.canRead())
                throw new IOException();
            Scanner scanner = new Scanner(file);
            Employee[] employees;
            StaffEmployee staffEmployee;
            PartTimeEmployee partTimeEmployee;
            BusinessTravel[] businessTravels;
            String _class = scanner.nextLine();
            if(_class.equals("Department")){
                Department department = (Department) employeeGroup;
                int size = scanner.nextInt();
                employees = new Employee[size];
                for(int i = 0; i < size; i++){
                    _class = scanner.nextLine();
                    employees[i].setName(scanner.nextLine());
                    employees[i].setSurname(scanner.nextLine());
                    employees[i].setJobTitle(scanner.nextLine());
                    employees[i].setSalary(scanner.nextInt());
                    if(_class.equals("StaffEmployee")){
                        staffEmployee = (StaffEmployee) employees[i];
                        staffEmployee.setBonus(scanner.nextInt());
                        int travelsSize = scanner.nextInt();
                        businessTravels = new BusinessTravel[travelsSize];
                        for(int j = 0; j < travelsSize; j++){
                            int compensation = scanner.nextInt();
                            String destination = scanner.nextLine();
                            LocalDate begin = LocalDate.parse(scanner.nextLine());
                            LocalDate end = LocalDate.parse(scanner.nextLine());
                            String description = scanner.nextLine();
                            businessTravels[j] = new BusinessTravel(compensation, begin, end,
                                    description, destination);
                            staffEmployee.addTravel(businessTravels[j]);
                        }
                        employees[i] = staffEmployee;
                    }
                    else{
                        partTimeEmployee = (PartTimeEmployee) employees[i];
                        partTimeEmployee.setBonus(scanner.nextInt());
                        int temp = scanner.nextInt();
                        employees[i] = partTimeEmployee;
                    }
                }
                department.setEmployees(employees);
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
                    employee.setJobTitle(scanner.nextLine());
                    employee.setSalary(scanner.nextInt());
                    if(_class.equals("StaffEmployee")){
                        staffEmployee = (StaffEmployee) employee;
                        staffEmployee.setBonus(scanner.nextInt());
                        int travelsSize = scanner.nextInt();
                        businessTravels = new BusinessTravel[travelsSize];
                        for(int j = 0; j < travelsSize; j++){
                            int compensation = scanner.nextInt();
                            String destination = scanner.nextLine();
                            LocalDate begin = LocalDate.parse(scanner.nextLine());
                            LocalDate end = LocalDate.parse(scanner.nextLine());
                            String description = scanner.nextLine();
                            businessTravels[j] = new BusinessTravel(compensation, begin, end,
                                    description, destination);
                            staffEmployee.addTravel(businessTravels[j]);
                        }
                        project.add(staffEmployee);
                    }
                    else{
                        partTimeEmployee = (PartTimeEmployee) employee;
                        partTimeEmployee.setBonus(scanner.nextInt());
                        int temp = scanner.nextInt();
                        project.add(partTimeEmployee);
                    }
                }
                employeeGroup = project;
            }
            scanner.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void store(EmployeeGroup employeeGroup) {

    }

    @Override
    public void delete(EmployeeGroup employeeGroup) {
        File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
        file.delete();
    }

    @Override
    public void create(EmployeeGroup employeeGroup) {
        try {
            File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
            file.createNewFile();
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(employeeGroup.getClass().toString());
            printWriter.println(employeeGroup.size());
            Employee[] employees = employeeGroup.getEmployees();
            StaffEmployee staffEmployee;
            PartTimeEmployee partTimeEmployee;
            BusinessTravel[] businessTravels;
            for(int i = 0; i < employeeGroup.size(); i++){
                printWriter.println(employees[i].getClass().toString());
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
                    printWriter.println(0);
                }
            }
            printWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

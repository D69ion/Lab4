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
        //todo агалогично BinaryFS
        File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
        try (Scanner scanner = new Scanner(file)) {
            employeeGroup.clear();
            StaffEmployee staffEmployee;
            PartTimeEmployee partTimeEmployee;
            BusinessTravel businessTravel;
            if (scanner.nextLine().equals("Department")) {
                int size = scanner.nextInt();
                for (int i = 0; i < size; i++) {
                    if (scanner.nextLine().equals("StaffEmployee")) {
                        staffEmployee = new StaffEmployee(scanner.nextLine(), scanner.nextLine(),
                                JobTitleEnum.valueOf(scanner.nextLine()), scanner.nextInt());
                        staffEmployee.setBonus(scanner.nextInt());
                        for (int j = 0; j < scanner.nextInt(); j++) {
                            int compensation = scanner.nextInt();
                            String destination = scanner.nextLine();
                            LocalDate begin = LocalDate.parse(scanner.nextLine());
                            LocalDate end = LocalDate.parse(scanner.nextLine());
                            String description = scanner.nextLine();
                            businessTravel = new BusinessTravel(compensation, begin, end,
                                    description, destination);
                            staffEmployee.add(businessTravel);
                        }
                        employeeGroup.add(staffEmployee);
                    } else {
                        partTimeEmployee = new PartTimeEmployee(scanner.nextLine(), scanner.nextLine(),
                                JobTitleEnum.valueOf(scanner.nextLine()), scanner.nextInt());
                        employeeGroup.add(partTimeEmployee);
                    }
                }
            } else {
                int size = scanner.nextInt();
                for (int i = 0; i < size; i++) {
                    if (scanner.nextLine().equals("StaffEmployee")) {
                        staffEmployee = new StaffEmployee(scanner.nextLine(), scanner.nextLine(),
                                JobTitleEnum.valueOf(scanner.nextLine()), scanner.nextInt());
                        staffEmployee.setBonus(scanner.nextInt());
                        for (int j = 0; j < scanner.nextInt(); j++) {
                            int compensation = scanner.nextInt();
                            String destination = scanner.nextLine();
                            LocalDate begin = LocalDate.parse(scanner.nextLine());
                            LocalDate end = LocalDate.parse(scanner.nextLine());
                            String description = scanner.nextLine();
                            businessTravel = new BusinessTravel(compensation, begin, end,
                                    description, destination);
                            staffEmployee.add(businessTravel);
                        }
                        employeeGroup.add(staffEmployee);
                    } else {
                        partTimeEmployee = new PartTimeEmployee(scanner.nextLine(), scanner.nextLine(),
                                JobTitleEnum.valueOf(scanner.nextLine()), scanner.nextInt());
                        employeeGroup.add(partTimeEmployee);
                    }
                }
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void store(EmployeeGroup employeeGroup) {
        try {
            File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(employeeGroup.getClass().getSimpleName());
            printWriter.println(employeeGroup.size());
            for (Employee emp : employeeGroup) {
                printWriter.println(emp.getFullString());
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

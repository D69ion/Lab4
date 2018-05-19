package com.company.io;

import com.company.humanResources.*;

import java.io.*;
import java.time.LocalDate;

public class GroupsManagerBinaryFileSource extends GroupsManagerFileSource {
    public GroupsManagerBinaryFileSource() {
        super();
    }

    public GroupsManagerBinaryFileSource(String path) {
        super(path);
    }

    @Override
    public void load(EmployeeGroup employeeGroup) {
        File file = new File(super.getPath(), employeeGroup.getName() + ".bin");
        try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))){
            StaffEmployee staffEmployee;
            PartTimeEmployee partTimeEmployee;
            BusinessTravel businessTravel;
            if(dataInputStream.readUTF().equals("Department")){
                Department department = (Department) employeeGroup;
                Employee[] employees = new Employee[dataInputStream.readInt()];
                for(int i = 0; i < employees.length; i++){
                    if(dataInputStream.readUTF().equals("StaffEmployee")){
                        staffEmployee = (StaffEmployee) employees[i];
                        staffEmployee.setName(dataInputStream.readUTF());
                        staffEmployee.setSurname(dataInputStream.readUTF());
                        staffEmployee.setJobTitle(JobTitleEnum.valueOf(dataInputStream.readUTF()));
                        staffEmployee.setSalary(dataInputStream.readInt());
                        staffEmployee.setBonus(dataInputStream.readInt());
                        for(int j = 0; j < dataInputStream.readInt(); j++){
                            int compensation = dataInputStream.readInt();
                            String destination = dataInputStream.readUTF();
                            LocalDate begin = LocalDate.parse(dataInputStream.readUTF());
                            LocalDate end = LocalDate.parse(dataInputStream.readUTF());
                            String description = dataInputStream.readUTF();
                            businessTravel = new BusinessTravel(compensation, begin, end,
                                    description, destination);
                            staffEmployee.addTravel(businessTravel);
                        }
                        employees[i] = staffEmployee;
                    }
                    else{
                        partTimeEmployee = (PartTimeEmployee) employees[i];
                        partTimeEmployee.setName(dataInputStream.readUTF());
                        partTimeEmployee.setSurname(dataInputStream.readUTF());
                        partTimeEmployee.setJobTitle(JobTitleEnum.valueOf(dataInputStream.readUTF()));
                        partTimeEmployee.setSalary(dataInputStream.readInt());
                        partTimeEmployee.setBonus(dataInputStream.readInt());
                        employees[i] = partTimeEmployee;
                    }
                }
                department.setEmployees(employees);
                department.setSize(employees.length);
                employeeGroup = department;
            }
            else{
                Project project = (Project) employeeGroup;
                int size = dataInputStream.readInt();
                for(int i = 0; i < size; i++){
                    if(dataInputStream.readUTF().equals("StaffEmployee")){
                        staffEmployee = new StaffEmployee(dataInputStream.readUTF(), dataInputStream.readUTF(),
                                JobTitleEnum.valueOf(dataInputStream.readUTF()), dataInputStream.readInt());
                        staffEmployee.setBonus(dataInputStream.readInt());
                        for(int j = 0; j < dataInputStream.readInt(); j++){
                            int compensation = dataInputStream.readInt();
                            String destination = dataInputStream.readUTF();
                            LocalDate begin = LocalDate.parse(dataInputStream.readUTF());
                            LocalDate end = LocalDate.parse(dataInputStream.readUTF());
                            String description = dataInputStream.readUTF();
                            businessTravel = new BusinessTravel(compensation, begin, end,
                                    description, destination);
                            staffEmployee.addTravel(businessTravel);
                        }
                        project.add(staffEmployee);
                    }
                    else{
                        partTimeEmployee = new PartTimeEmployee(dataInputStream.readUTF(), dataInputStream.readUTF(),
                                JobTitleEnum.valueOf(dataInputStream.readUTF()), dataInputStream.readInt());
                        partTimeEmployee.setBonus(dataInputStream.readInt());
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
        File file = new File(super.getPath(), employeeGroup.getName() + ".bin");
        try(DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))){
            dataOutputStream.writeUTF(employeeGroup.getClass().getSimpleName());
            dataOutputStream.writeInt(employeeGroup.getSize());
            Employee[] employees = employeeGroup.getEmployees();
            StaffEmployee staffEmployee;
            PartTimeEmployee partTimeEmployee;
            BusinessTravel[] businessTravels;
            for(int i = 0; i < employeeGroup.getSize(); i++){
                dataOutputStream.writeUTF(employees[i].getClass().getSimpleName());
                dataOutputStream.writeUTF(employees[i].getName());
                dataOutputStream.writeUTF(employees[i].getJobTitle().toString());
                dataOutputStream.write(employees[i].getSalary());
                if(employees[i] instanceof StaffEmployee){
                    staffEmployee = (StaffEmployee) employees[i];
                    dataOutputStream.write(staffEmployee.getBonus());
                    businessTravels = staffEmployee.getTravels();
                    dataOutputStream.write(businessTravels.length);
                    for(int j = 0; j < businessTravels.length; j++){
                        dataOutputStream.write(businessTravels[j].getCompensation());
                        dataOutputStream.writeUTF(businessTravels[j].getDestination());
                        dataOutputStream.writeUTF(businessTravels[j].getBeginTravel().toString());
                        dataOutputStream.writeUTF(businessTravels[j].getEndTravel().toString());
                        dataOutputStream.writeUTF(businessTravels[j].getDescription());
                    }
                }
                else{
                    partTimeEmployee = (PartTimeEmployee) employees[i];
                    dataOutputStream.write(partTimeEmployee.getBonus());
                }
            }
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

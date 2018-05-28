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
        //todo логикаа просто - очистил employeeGroup, а потом каждого считанного сторудника добавил в нее
        File file = new File(super.getPath(), employeeGroup.getName() + ".bin");
        try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))){
            employeeGroup.clear();
            StaffEmployee staffEmployee;
            PartTimeEmployee partTimeEmployee;
            BusinessTravel businessTravel;
            if(dataInputStream.readUTF().equals("Department")){
                Department department = (Department) employeeGroup;
                for(int i = 0; i < dataInputStream.readInt(); i++){
                    if(dataInputStream.readUTF().equals("StaffEmployee")){
                        staffEmployee = new StaffEmployee(dataInputStream.readUTF(), dataInputStream.readUTF(),
                                JobTitleEnum.valueOf(dataInputStream.readUTF()), dataInputStream.readInt()); //todo здесь надо создать
                        staffEmployee.setBonus(dataInputStream.readInt());
                        for(int j = 0; j < dataInputStream.readInt(); j++){
                            int compensation = dataInputStream.readInt();
                            String destination = dataInputStream.readUTF();
                            LocalDate begin = LocalDate.parse(dataInputStream.readUTF());
                            LocalDate end = LocalDate.parse(dataInputStream.readUTF());
                            String description = dataInputStream.readUTF();
                            businessTravel = new BusinessTravel(compensation, begin, end,
                                    description, destination);
                            staffEmployee.add(businessTravel);
                        }
                        employeeGroup.add(staffEmployee);
                    }
                    else{
                        partTimeEmployee = new PartTimeEmployee(dataInputStream.readUTF(), dataInputStream.readUTF(),
                                JobTitleEnum.valueOf(dataInputStream.readUTF()), dataInputStream.readInt());//todo здесь надо создать
                        employeeGroup.add(partTimeEmployee);
                    }
                }
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
                            staffEmployee.add(businessTravel);
                        }
                        employeeGroup.add(staffEmployee);
                    }
                    else{
                        partTimeEmployee = new PartTimeEmployee(dataInputStream.readUTF(), dataInputStream.readUTF(),
                                JobTitleEnum.valueOf(dataInputStream.readUTF()), dataInputStream.readInt());
                        employeeGroup.add(partTimeEmployee);
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void store(EmployeeGroup employeeGroup) {
        File file = new File(super.getPath(), employeeGroup.getName() + ".bin");
        try(DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))){
            dataOutputStream.writeUTF(employeeGroup.getClass().getSimpleName());
            dataOutputStream.writeInt(employeeGroup.size());
            //Employee[] employees = (Employee[]) employeeGroup.toArray(); //todo нахер тебе массив, когда можно исопльзовать итератор
            StaffEmployee staffEmployee;
            for (Employee employee: employeeGroup //todo вот здесь просто foreach и фсеееееее
                 ) {
                dataOutputStream.writeUTF(employee.getClass().getSimpleName());
                dataOutputStream.writeUTF(employee.getName());
                dataOutputStream.writeUTF(employee.getJobTitle().toString());
                dataOutputStream.write(employee.getSalary());
                if(employee instanceof StaffEmployee){
                    staffEmployee = (StaffEmployee) employee;
                    dataOutputStream.write(staffEmployee.getBonus());
                    dataOutputStream.write(staffEmployee.size());
                    for (BusinessTravel businessTravel: staffEmployee
                         ) {
                        dataOutputStream.write(businessTravel.getCompensation());
                        dataOutputStream.writeUTF(businessTravel.getDestination());
                        dataOutputStream.writeUTF(businessTravel.getBeginTravel().toString());
                        dataOutputStream.writeUTF(businessTravel.getEndTravel().toString());
                        dataOutputStream.writeUTF(businessTravel.getDescription());
                    }
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

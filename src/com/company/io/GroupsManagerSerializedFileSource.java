package com.company.io;

import com.company.humanResources.*;

import java.io.*;

public class GroupsManagerSerializedFileSource extends GroupsManagerFileSource {
    public GroupsManagerSerializedFileSource() {
        super();
    }

    public GroupsManagerSerializedFileSource(String path) {
        super(path);
    }

    @Override
    public void load(EmployeeGroup employeeGroup) {
        try {
            File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            EmployeeGroup employeeGroup1 = (EmployeeGroup) objectInputStream.readObject();
            objectInputStream.close();
            //employeeGroup = employeeGroup1; //todo фигня
            employeeGroup.clear();
            for (Employee employee: employeeGroup1
                 ) {
                employeeGroup.add(employee);
            }
        }
        catch (FileNotFoundException f){
            f.printStackTrace();
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void store(EmployeeGroup employeeGroup) {
        try{
            File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(employeeGroup);
            objectOutputStream.close();
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

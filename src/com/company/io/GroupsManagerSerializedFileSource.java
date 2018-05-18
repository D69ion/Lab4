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
            employeeGroup = employeeGroup1;
            objectInputStream.close();
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
    public void delete(EmployeeGroup employeeGroup) {
        try {
            File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
            if(!file.delete())
                throw new FileNotFoundException();
        }
        catch (FileNotFoundException f){
            f.printStackTrace();
        }
    }

    @Override
    public void create(EmployeeGroup employeeGroup) {
        try {
            File file = new File(super.getPath(), employeeGroup.getName() + ".dat");
            file.createNewFile();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

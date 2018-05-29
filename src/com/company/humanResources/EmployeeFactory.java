package com.company.humanResources;

public abstract class EmployeeFactory {

    static public EmployeeFactory getEmployeeFactory(OrdersFactoryTypesEnumeration type, String path){
        switch (type){
            case ORDINARY_GROUPS_FACTORY:{
                return new OrdinaryEmployeeFactory();
            }
            case TEXT_FILE_BASED_GROUPS_FACTORY:{
                return new TextFileBasedEmployeeFactory(path);
            }
            case BINARY_FILE_BASED_GROUPS_FACTORY:{
                return new BinaryFileBasedEmployeeFactory(path);
            }
            case SERIALIZED_FILE_BASED_GROUPS_FACTORY:{
                return new SerializedFileBasedEmployeeFactory(path);
            }
            case SOCKET_BASED_GROUPS_FACTORY:{
                return null;
            }
        }
        return null;
    }

    public abstract EmployeeGroup createDepartment();

    public abstract EmployeeGroup createDepartment(String name);

    public abstract EmployeeGroup createDepartment(String name, int size);

    public abstract EmployeeGroup createDepartment(String name, Employee[] employees);

    public abstract EmployeeGroup createProject();

    public abstract EmployeeGroup createProject(String name);

    public abstract EmployeeGroup createProject(String name, Employee[] employees);

    public abstract GroupsManager createDepartmentManager();

    public abstract GroupsManager createDepartmentManager(String name);

    public abstract GroupsManager createDepartmentManager(String name, int size);

    public abstract GroupsManager createDepartmentManager(String name, Department[] groups);

    public abstract GroupsManager createProjectManager();

    public abstract GroupsManager createProjectManager(EmployeeGroup[] employeeGroups);
}

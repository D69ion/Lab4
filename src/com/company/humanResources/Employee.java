package com.company.humanResources;

public abstract class Employee {
    private String name;
    private String surname;
    private JobTitleEnum jobTitle;
    private int salary;

    /*private static final String DEFAULT_NAME = null; //""
    private static final String DEFAULT_SURNAME = null; // ""*/
    private static final JobTitleEnum DEFAULT_JOB_TITLE = JobTitleEnum.NONE; //""
    private static final int DEFAULT_SALARY = 0;

    /*public Employee(){//???
        this (DEFAULT_NAME, DEFAULT_SURNAME, DEFAULT_JOB_TITLE, DEFAULT_SALARY);
    }*/

    protected Employee(String name, String surname){
        this (name, surname, DEFAULT_JOB_TITLE, DEFAULT_SALARY);
   }

    protected Employee(String name, String surname, JobTitleEnum jobTitle, int salary){
        this.name = name;
        this.surname = surname;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public JobTitleEnum getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitleEnum jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public abstract int getBonus();

    public abstract void setBonus(int bonus);

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        if (surname != null)
            res.append(surname).append(" ");
        if (name != null)
            res.append(name).append(" ");
        if (jobTitle != JobTitleEnum.NONE)
            res.append(jobTitle.toString()).append(" ");
        if (salary != 0)
            res.append(salary).append("р.");
        return res.toString();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;
        Employee employee = (Employee) obj;
        return this.name.equals(employee.getName()) && this.surname.equals(employee.getSurname())
                && this.jobTitle == employee.getJobTitle() && this.salary == employee.getSalary();
    }

    @Override
    public int hashCode(){
        int hash = name.hashCode() ^ surname.hashCode() ^ jobTitle.hashCode() ^ salary;
        return hash;
    }
}

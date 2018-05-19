package com.company.humanResources;

import java.io.Serializable;

public abstract class Employee implements Comparable<Employee>, Serializable{
    private String name;
    private String surname;
    private JobTitleEnum jobTitle;
    private int salary;

    private static final String DEFAULT_NAME = "";
    private static final String DEFAULT_SURNAME = "";
    private static final JobTitleEnum DEFAULT_JOB_TITLE = JobTitleEnum.NONE; //""
    private static final int DEFAULT_SALARY = 0;

    public Employee(){
        this (DEFAULT_NAME, DEFAULT_SURNAME, DEFAULT_JOB_TITLE, DEFAULT_SALARY);
    }

    protected Employee(String name, String surname){
        this (name, surname, DEFAULT_JOB_TITLE, DEFAULT_SALARY);
   }

    protected Employee(String name, String surname, JobTitleEnum jobTitle, int salary) {
        if (salary < 0)
            throw new IllegalArgumentException("Передано отрицательное значение заработной платы");
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
        String res = "";// String.format("%s %s, %s, %dр.", surname, name, jobTitle.toString(), salary);
        if (surname != null && name.equals(""))
            res += String.format("%s ", this.surname);
        if (name != null && name.equals(""))
            res += String.format("%s, ", this.name);
        if (jobTitle != JobTitleEnum.NONE)
            res += String.format("%s, ", this.jobTitle.toString());
        if (salary != 0)
            res += String.format("%dр.", this.salary);
        return res;
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

    @Override
    public int compareTo(Employee o) {
        return this.salary - o.salary;
    }
}

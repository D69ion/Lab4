package com.company.humanResources;

public class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, String surname){
        super(name, surname);
    }

    public PartTimeEmployee(String name, String surname, JobTitleEnum jobTitle, int salary){
        super(name, surname, jobTitle, salary);
    }

    @Override
    public int getBonus() {
        return 0;
    }

    @Override
    public void setBonus(int bonus) {

    }

    @Override
    public String toString(){
        String res = String.format("%s %s, %s(внешний совместитель), %dр.", super.getSurname(), super.getName(), super.getJobTitle().toString(), super.getSalary());
        /*StringBuilder res = new StringBuilder();
        if (super.getSurname() != null)
            res.append(super.getSurname()).append(" ");
        if (super.getName() != null)
            res.append(super.getName()).append(" ");
        if (super.getJobTitle() != JobTitleEnum.NONE)
            res.append(super.getJobTitle().toString()).append("(внешний совместитель) ");
        if (super.getSalary() != 0)
            res.append(super.getSalary()).append("р.");*/
        return res;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof PartTimeEmployee)) return false;
        PartTimeEmployee partTimeEmployee = (PartTimeEmployee) obj;
        return this.getName().equals(partTimeEmployee.getName()) && this.getSurname().equals(partTimeEmployee.getSurname())
                && this.getJobTitle() == partTimeEmployee.getJobTitle() && this.getSalary() == partTimeEmployee.getSalary();
    }

    @Override
    public int hashCode(){
        /*int hash = super.getName().hashCode() ^ super.getSurname().hashCode() ^
                super.getJobTitle().hashCode() ^ super.getSalary();*/
        return super.hashCode();
    }
}

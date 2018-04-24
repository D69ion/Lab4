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
        String res = "";//String.format("%s %s, %s(внешний совместитель), %dр.", super.getSurname(), super.getName(), super.getJobTitle().toString(), super.getSalary());
        if (super.getSurname() != null && super.getSurname().equals(""))
            res += String.format("%s ", super.getSurname());
        if (super.getName() != null && super.getName().equals(""))
            res += String.format("%s, ", super.getName());
        if (super.getJobTitle() != JobTitleEnum.NONE)
            res += String.format("%s(анешний совместитель), ", super.getJobTitle().toString());
        if (super.getSalary() != 0)
            res += String.format("%dр.", super.getSalary());
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

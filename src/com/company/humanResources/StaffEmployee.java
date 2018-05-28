package com.company.humanResources;

import java.io.Serializable;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class StaffEmployee extends Employee implements BusinessTraveller, Serializable{
    private int bonus;
    private HashSet<BusinessTravel> businessTravelList;

    private static final int DEFAULT_BONUS = 0;
    private static final HashSet<BusinessTravel> DEFAULT_BUSINESS_TRAVEL_LIST = null;


    public StaffEmployee(String name, String surname){
        super(name, surname);
        this.bonus = DEFAULT_BONUS;
        this.businessTravelList = DEFAULT_BUSINESS_TRAVEL_LIST;
    }

    public StaffEmployee(String name, String surname, JobTitleEnum jobTitle, int salary){
        super(name, surname, jobTitle, salary);
        this.bonus = DEFAULT_BONUS;
        this.businessTravelList = DEFAULT_BUSINESS_TRAVEL_LIST;
    }

    public StaffEmployee(String name, String surname, JobTitleEnum jobTitle, int salary, BusinessTravel[] businessTravels){
        super(name, surname, jobTitle, salary);
        this.bonus = DEFAULT_BONUS;
        for (BusinessTravel businessTravel: businessTravels
             ) {
            this.businessTravelList.add(businessTravel);
        }
    }

    @Override
    public int getBonus() {
        return this.bonus;
    }

    @Override
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean isTravelNow() {
        BusinessTravel[] businessTravels = (BusinessTravel[]) toArray();
        BusinessTravel businessTravel = businessTravels[businessTravels.length - 1];
        return LocalDate.now().isAfter(businessTravel.getBeginTravel()) && LocalDate.now().isBefore(businessTravel.getEndTravel());
    }

    @Override
    public int getTravelDaysQuantityFromTimeLapse(LocalDate startDate, LocalDate endDate) {
        for (BusinessTravel businessTravel: this.businessTravelList
             ) {
            if(startDate.isAfter(businessTravel.getBeginTravel()) && endDate.isBefore(businessTravel.getEndTravel()))
                return (int)ChronoUnit.DAYS.between(startDate, endDate);
            if(startDate.isAfter(businessTravel.getBeginTravel())&& startDate.isBefore(businessTravel.getEndTravel()) && endDate.isAfter(businessTravel.getEndTravel()))
                return (int)ChronoUnit.DAYS.between(startDate, businessTravel.getEndTravel());
            if(endDate.isAfter(businessTravel.getBeginTravel())&& endDate.isBefore(businessTravel.getEndTravel()) && startDate.isBefore(businessTravel.getBeginTravel()))
                return (int) ChronoUnit.DAYS.between(businessTravel.getBeginTravel(), endDate);
        }
        return 0;
    }

    @Override
    public String getFullString() {
        StringBuilder res = new StringBuilder("");
        res.append("StaffEmployee ").append(super.getName()).append(" ").append(super.getSurname())
                .append(" ").append(super.getJobTitle().toString()).append(" ").append(super.getSalary())
                .append(" ").append(getBonus()).append(" ").append(size()).append("\r\n");
        for (BusinessTravel businessTravel: this.businessTravelList
                ) {
            res.append(businessTravel.getFullString()).append("\r\n");
        }
        return res.toString();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(super.toString()).append(this.bonus).append("р.").append("\r\n").append("Командировки: \r\n");
        for (BusinessTravel businessTravel: this.businessTravelList
             ) {
            res.append(businessTravel.toString()).append("\r\n");
        }
        return res.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof StaffEmployee)) return false;
        StaffEmployee staffEmployee = (StaffEmployee)obj;
        return super.equals(staffEmployee) && this.bonus == staffEmployee.getBonus() &&
                this.toArray().length == staffEmployee.toArray().length;
    }

    @Override
    public int hashCode(){
        int hash = super.hashCode() ^ this.bonus;
        for (BusinessTravel businessTravel: this.businessTravelList
             ) {
            hash ^= businessTravel.hashCode();
        }
        return hash;
    }

    @Override
    public int size() {
        return this.businessTravelList.size();
    }

    @Override
    public boolean isEmpty() {
        if(this.businessTravelList == null)
            return false;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        return businessTravelList.contains(o);
    }

    @Override
    public Iterator<BusinessTravel> iterator() {
        return this.businessTravelList.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.businessTravelList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.businessTravelList.toArray(a);
    }

    @Override
    public boolean add(BusinessTravel travel) {
        try{
            for (BusinessTravel businessTravel : this.businessTravelList
                    ) {
                if (travel.getBeginTravel().isAfter(businessTravel.getEndTravel()) || travel.getEndTravel().isBefore(businessTravel.getBeginTravel()))
                    throw new IllegalDatesException("Добавляемая командировка пересекается с другими командировками");
            }
            this.businessTravelList.add(travel);
        }
        catch (IllegalDatesException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return this.businessTravelList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.businessTravelList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends BusinessTravel> c) {
        return this.businessTravelList.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.businessTravelList.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.businessTravelList.removeAll(c);
    }

    @Override
    public void clear() {
        this.businessTravelList.clear();
    }


}

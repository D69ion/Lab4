package com.company.humanResources;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class StaffEmployee extends Employee implements BusinessTraveller{
    private int bonus;
    private ArrayList<BusinessTravel> businessTravelList;

    private static final int DEFAULT_BONUS = 0;
    private static final ArrayList<BusinessTravel> DEFAULT_BUSINESS_TRAVEL_LIST = null;


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
        for(int i = 0 ; i < businessTravels.length; i++){
            businessTravelList.add(businessTravels[i]);
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
    public void addTravel(BusinessTravel travel) throws IllegalDatesException{
            BusinessTravel[] businessTravels = getTravels();
            for(int i = 0; i < this.businessTravelList.size(); i++) {
                if (travel.getBeginTravel().isAfter(businessTravels[i].getEndTravel()) || travel.getEndTravel().isBefore(businessTravels[i].getBeginTravel()))
                    throw new IllegalDatesException("Добавляемая командировка пересекается с другими командировками");
            }
        businessTravelList.add(travel);
    }

    @Override
    public BusinessTravel[] getTravels() {
        if(businessTravelList.size() == 0)
            return null;
        return (BusinessTravel[]) businessTravelList.toArray();
    }

    @Override
    public boolean isTravelNow() {
        BusinessTravel[] businessTravels = getTravels();
        BusinessTravel businessTravel = businessTravels[businessTravels.length - 1];
        return LocalDate.now().isAfter(businessTravel.getBeginTravel()) && LocalDate.now().isBefore(businessTravel.getEndTravel());
    }

    @Override
    public int getTravelDaysQuantityFromTimeLapse(LocalDate startDate, LocalDate endDate) {
        BusinessTravel[] businessTravels = getTravels();
        for(int i = 0; i < this.businessTravelList.size(); i++){
            if(startDate.isAfter(businessTravels[i].getBeginTravel()) && endDate.isBefore(businessTravels[i].getEndTravel()))
                return (int)ChronoUnit.DAYS.between(startDate, endDate);
            if(startDate.isAfter(businessTravels[i].getBeginTravel())&& startDate.isBefore(businessTravels[i].getEndTravel()) && endDate.isAfter(businessTravels[i].getEndTravel()))
                return (int)ChronoUnit.DAYS.between(startDate, businessTravels[i].getEndTravel());
            if(endDate.isAfter(businessTravels[i].getBeginTravel())&& endDate.isBefore(businessTravels[i].getEndTravel()) && startDate.isBefore(businessTravels[i].getBeginTravel()))
                return (int) ChronoUnit.DAYS.between(businessTravels[i].getBeginTravel(), endDate);
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(super.toString()).append(this.bonus).append("р.").append("\r\n").append("Командировки: \r\n");
        BusinessTravel[] businessTravels = (BusinessTravel[]) businessTravelList.toArray();
        for(int i = 0; i < businessTravels.length; i++){
            res.append(businessTravels[i].toString()).append("\r\n");
        }
        return res.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof StaffEmployee)) return false;
        StaffEmployee staffEmployee = (StaffEmployee)obj;
        return super.equals(staffEmployee) && this.bonus == staffEmployee.getBonus() &&
                this.getTravels().length == staffEmployee.getTravels().length;
    }

    @Override
    public int hashCode(){
        int hash = super.hashCode() ^ this.bonus;
        BusinessTravel[] businessTravels = (BusinessTravel[]) businessTravelList.toArray();
        for(int i = 0; i < businessTravels.length; i++){
            hash ^= businessTravels[i].hashCode();
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
        BusinessTravel businessTravel = (BusinessTravel) o;
        return businessTravelList.contains(businessTravel);
    }

    @Override
    public Iterator<BusinessTravel> iterator() {//?????
        //return this.businessTravelList.iterator();
        Iterator<BusinessTravel> iterator = new Iterator<BusinessTravel>() {
            public BusinessTravel[] businessTravels = getTravels();
            public int size = businessTravelList.size();
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public BusinessTravel next() {
                return null;
            }
        };
        return iterator;
    }

    @Override
    public Object[] toArray() {
        return getTravels();
    }

    @Override
    public <T> T[] toArray(T[] a) {//?????
        return (T[]) getTravels();
    }

    @Override
    public boolean add(BusinessTravel travel) {
        try{
            addTravel(travel);
        }
        catch (IllegalDatesException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return this.businessTravelList.remove((BusinessTravel) o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {//????
        return this.businessTravelList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends BusinessTravel> c) {
        return this.businessTravelList.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {//????
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

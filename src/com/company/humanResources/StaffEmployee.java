package com.company.humanResources;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class StaffEmployee extends Employee implements BusinessTraveller{
    private int bonus;
    private List businessTravelList;

    private static final int DEFAULT_BONUS = 0;
    private static final List DEFAULT_BUSINESS_TRAVEL_LIST = null;


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
            businessTravelList.addLast(businessTravels[i]);
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
            for(int i = 0; i < this.businessTravelList.length; i++) {
                if (travel.getBeginTravel().isAfter(businessTravels[i].getEndTravel()) || travel.getEndTravel().isBefore(businessTravels[i].getBeginTravel()))
                    throw new IllegalDatesException("Добавляемая командировка пересекается с другими командировками");
            }
        businessTravelList.addLast(travel);
    }

    @Override
    public BusinessTravel[] getTravels() {
        if(businessTravelList.length == 0)
            return null;
        return businessTravelList.toArray();
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
        for(int i = 0; i < this.businessTravelList.length; i++){
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
        BusinessTravel[] businessTravels = businessTravelList.toArray();
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
        BusinessTravel[] businessTravels = businessTravelList.toArray();
        for(int i = 0; i < businessTravels.length; i++){
            hash ^= businessTravels[i].hashCode();
        }
        return hash;
    }

    private class ListElement{
        ListElement next;
        BusinessTravel data;
    }

    private class List{
        private ListElement head;
        private ListElement tail;
        private int length = 0;

        public void addFirst(BusinessTravel data){
            ListElement a = new ListElement();
            a.data = data;
            if(head == null){
                head = a;
                tail = a;
            }
            else{
                tail.next = a;
                tail = a;
            }
            length++;
        }

        public void addLast(BusinessTravel data){
            ListElement a = new ListElement();
            a.data = data;
            if(tail == null){
                head = a;
                tail = a;
            }
            else{
                tail.next = a;
                tail = a;
            }
            length++;
        }

        public void deleteElement(BusinessTravel data){
            if(head == null)
                return;
            if(head.equals(tail)){
                head = null;
                tail = null;
                length--;
                return;
            }
            if(head.data.equals(data)){
                head = head.next;
                length--;
                return;
            }
            ListElement t = head;
            while(t.next != null){
                if(t.next.data.equals(data)){
                    if(tail.equals(t.next))
                        tail = t;
                    t.next = t.next.next;
                    length--;
                    return;
                }
                t=t.next;
            }
        }

        public BusinessTravel[] toArray(){
            BusinessTravel[] res = new BusinessTravel[length];
            int count = 0;
            ListElement t = head;
            while(t != null){
                res[count] = t.data;
                t = t.next;
                count++;
            }
            return res;
        }
    }
}

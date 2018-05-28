package com.company.humanResources;

import java.time.LocalDate;
import java.util.Set;

public interface BusinessTraveller extends Set<BusinessTravel>{
    //void addTravel(BusinessTravel travel) throws IllegalDatesException;
    //BusinessTravel[] getTravels(); //todo убери дубли методов - toArray
    boolean isTravelNow();
    int getTravelDaysQuantityFromTimeLapse(LocalDate startDate, LocalDate endDate);
}

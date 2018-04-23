package com.company.humanResources;

import java.time.LocalDate;

public interface BusinessTraveller {
    void addTravel(BusinessTravel travel);
    BusinessTravel[] getTravels();
    boolean isTravelNow();
    int getTravelDaysQuantityFromTimeLapse(LocalDate startDate, LocalDate endDate);
}

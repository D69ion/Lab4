package com.company.humanResources;

import java.time.*;
import java.time.temporal.ChronoUnit;

public final class BusinessTravel {
    private int compensation;
    private LocalDate beginTravel;
    private LocalDate endTravel;
    private String description;
    private String destination;

    private final static int DEFAULT_COMPENSATION = 0;
    private final static LocalDate DEFAULT_BEGIN_TRAVEL = LocalDate.now();
    private final static LocalDate DEFAULT_END_TRAVEL = LocalDate.now().plusDays(1);
    private final static String DEFAULT_DESCRIPTION = "";
    private final static String DEFAULT_DESTINATION = "";


    public BusinessTravel(){
        this (DEFAULT_COMPENSATION, DEFAULT_BEGIN_TRAVEL, DEFAULT_END_TRAVEL, DEFAULT_DESCRIPTION, DEFAULT_DESTINATION);
    }

    public BusinessTravel(int compensation, LocalDate beginTravel, LocalDate endTravel, String description, String destination){
        if(endTravel.isBefore(beginTravel) && compensation < 0)
            throw new IllegalArgumentException("Дата завершения командировки раньше даты начала и сумма компенсации\n" +
                    "меньше 0");
        this.compensation = compensation;
        this.beginTravel = beginTravel;
        this.endTravel = endTravel;
        this.description = description;
        this.destination = destination;
    }

    public int getCompensation() {
        return compensation;
    }

    public LocalDate getBeginTravel() {
        return beginTravel;
    }

    public LocalDate getEndTravel() {
        return endTravel;
    }

    public String getDescription() {
        return description;
    }

    public String getDestination() {
        return destination;
    }

    public int getDaysCount(){
        return (int) ChronoUnit.DAYS.between(this.beginTravel, this.endTravel);
    }

    @Override
    public String toString(){
        String res = String.format("%s %tF – %tF (%d). %s", destination, beginTravel, endTravel, compensation, description);
        /*StringBuilder res = new StringBuilder();
        if (destination != null)
            res.append(destination).append(" ");
        if (daysCount != 0)
            res.append(daysCount).append(" ");
        if (compensation != 0)
            res.append("(").append(compensation).append(").").append(" ");
        if (description != null)
            res.append(description);*/
        return res;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof BusinessTravel)) return false;
        BusinessTravel businessTravel = (BusinessTravel) obj;
        return this.compensation == businessTravel.getCompensation() && this.beginTravel.equals(businessTravel.getBeginTravel()) && this.endTravel.equals(businessTravel.getEndTravel())
                && this.destination.equals(businessTravel.getDestination()) && this.description.equals(businessTravel.getDescription());
    }

    @Override
    public int hashCode(){
        int hash = compensation ^ beginTravel.hashCode() ^ endTravel.hashCode() ^ description.hashCode() ^ destination.hashCode();
        return hash;
    }
}

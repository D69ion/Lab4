package com.company.humanResources;

public final class BusinessTravel {
    private int compensation;
    private int daysCount;
    private String description;
    private String destination;

    private final static int DEFAULT_COMPENSATION = 0;
    private final static int DEFAULT_DAYS_COUNT = 0;
    private final static String DEFAULT_DESCRIPTION = "";
    private final static String DEFAULT_DESTINATION = "";


    public BusinessTravel(){
        this (DEFAULT_COMPENSATION, DEFAULT_DAYS_COUNT, DEFAULT_DESCRIPTION, DEFAULT_DESTINATION);
    }

    public BusinessTravel(int compensation, int daysCount, String description, String destination){
        this.compensation = compensation;
        this.daysCount = daysCount;
        this.description = description;
        this.destination = destination;
    }

    public int getCompensation() {
        return compensation;
    }

    public int getDaysCount() {
        return daysCount;
    }

    public String getDescription() {
        return description;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        if (destination != null)
            res.append(destination).append(" ");
        if (daysCount != 0)
            res.append(daysCount).append(" ");
        if (compensation != 0)
            res.append("(").append(compensation).append(").").append(" ");
        if (description != null)
            res.append(description);
        return res.toString();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof BusinessTravel)) return false;
        BusinessTravel businessTravel = (BusinessTravel) obj;
        return this.compensation == businessTravel.getCompensation() && this.daysCount == businessTravel.getDaysCount()
                && this.destination.equals(businessTravel.getDestination()) && this.description.equals(businessTravel.getDescription());
    }

    @Override
    public int hashCode(){
        int hash = compensation ^ daysCount ^ description.hashCode() ^ destination.hashCode();
        return hash;
    }
}

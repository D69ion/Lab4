package com.company.humanResources;

public class IllegalDatesException extends Exception{
    public IllegalDatesException(){
        super();
    }

    public IllegalDatesException(String message){
        super(message);
    }
}

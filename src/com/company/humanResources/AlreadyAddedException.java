package com.company.humanResources;

public class AlreadyAddedException extends Exception{
    public AlreadyAddedException(String message){
        super(message);
    }
}

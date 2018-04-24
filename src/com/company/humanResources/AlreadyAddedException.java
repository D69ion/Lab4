package com.company.humanResources;

public class AlreadyAddedException extends Exception{
    public AlreadyAddedException(){
        super();
    }

    public AlreadyAddedException(String message){
        super(message);
    }
}

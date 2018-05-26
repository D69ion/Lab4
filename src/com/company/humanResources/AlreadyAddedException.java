package com.company.humanResources;

public class AlreadyAddedException extends RuntimeException{
    public AlreadyAddedException(){
        super();
    }

    public AlreadyAddedException(String message){
        super(message);
    }
}

package com.company.humanResources;

public class NegativeSizeException extends NegativeArraySizeException {
    public NegativeSizeException(){
        super();
    }

    public NegativeSizeException(String message){
        super(message);
    }
}

package com.example.springSecurity.exception;

public class RecordNotFoundException extends RuntimeException{
    
    public RecordNotFoundException(String message){
        super(message);
    }

}

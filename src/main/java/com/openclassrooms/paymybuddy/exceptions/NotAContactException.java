package com.openclassrooms.paymybuddy.exceptions;

public class NotAContactException extends Exception{
    public NotAContactException(){
        super();
    }
    public NotAContactException(String message) {
        super(message);
    }
}

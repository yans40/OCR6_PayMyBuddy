package com.openclassrooms.paymybuddy.exceptions;

public class MailAlreadyExistException extends Exception{
    public MailAlreadyExistException(){
        super();
    }
    public MailAlreadyExistException(String message){
        super(message);
    }
}

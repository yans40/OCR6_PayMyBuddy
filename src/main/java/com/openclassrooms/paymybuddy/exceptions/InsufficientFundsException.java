package com.openclassrooms.paymybuddy.exceptions;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(){
        super();
    }
    public InsufficientFundsException(String message){
        super(message);
    }
}

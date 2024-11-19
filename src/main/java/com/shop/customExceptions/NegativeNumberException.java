package com.shop.customExceptions;
/*
Used to throw an exception if negative number is entered
 */
public class NegativeNumberException extends NumberFormatException{
    public NegativeNumberException() {
        super("The value can't be < 0");
    }
}

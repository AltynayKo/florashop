package com.shop.customExceptions;
/*
Used when an empty value is passed while entering a name value for Bouquets, Flowers, Accessories
 */
public class EmptyValueException extends Exception{
    public EmptyValueException(String message) {
        super(message);
    }
}

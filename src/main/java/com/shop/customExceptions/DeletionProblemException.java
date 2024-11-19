package com.shop.customExceptions;
/*
No exception should be generated during removing a record,
so this exception is created to observe whether to catch any exception while deleting
 */
public class DeletionProblemException extends Exception{
    public DeletionProblemException(String message) {
        super(message);
    }
    public DeletionProblemException() {
        super("A problem occurred while removing a bouquet");
    }
}

package com.joshua.lilly.dnsdynamic;

public class NoNumberFoundException extends Exception{
    //Parameterless Constructor
    public NoNumberFoundException() {}

    //Constructor that accepts a message
    public NoNumberFoundException(String message)
    {
       super(message);
    }
}

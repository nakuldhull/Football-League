package com.stackroute.oops.league.exception;

public class TeamAlreadyFormedException extends Exception {
    public TeamAlreadyFormedException(){
        super("Team Created Already");
    }
}

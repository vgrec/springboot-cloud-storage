package com.udacity.jwdnd.course1.cloudstorage.controllers.strategy;

public class UserDataValidatorStrategy {

    private ValidatorStrategy strategy;

    public UserDataValidatorStrategy(ValidatorStrategy strategy){
        this.strategy = strategy;
    }

    public boolean isValid(){
        return strategy.isValid();
    }
}

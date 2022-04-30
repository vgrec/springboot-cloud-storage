package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Component;

public class UserDataValidatorStrategy {

    private ValidatorStrategy strategy;

    UserDataValidatorStrategy(ValidatorStrategy strategy){
        this.strategy = strategy;
    }

    boolean isValid(){
        return strategy.isValid();
    }
}

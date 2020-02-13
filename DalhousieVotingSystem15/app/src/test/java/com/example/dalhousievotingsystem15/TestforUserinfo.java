package com.example.dalhousievotingsystem15;

import org.junit.Assert;
import org.junit.Test;

public class TestforUserinfo {
    @Test
    public void User_Exists() {
        try {
            Class.forName("com.example.dalhousievotingsystem15.UserInfo");
        } catch (ClassNotFoundException e) {
            Assert.fail("Should create a class called 'UserInfo'.");
        }
    }}


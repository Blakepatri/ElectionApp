package com.example.dalhousievotingsystem15;

import org.junit.Assert;
import org.junit.Test;


public class TestforAdminlogin {

    @Test
    public void Admin_Exists(){
        try {
            Class.forName("com.example.dalhousievotingsystem15.Administer_LogIn");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'Administer_LogIn'.");
        }

    }

}

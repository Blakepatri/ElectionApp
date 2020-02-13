package com.example.dalhousievotingsystem15;


import org.junit.Assert;
import org.junit.Test;


public class TestforStudentlogin {


    @Test
    public void Student_Exists(){
        try {
            Class.forName("com.example.dalhousievotingsystem15.Student_Login");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'Student_LogIn'.");
        }

    }

}

package com.example.dalhousievotingsystem15;


import org.junit.Assert;
import org.junit.Test;




public class TestforCandidate {

    @Test
    public void Candidate_Exists(){
        try {
            Class.forName("com.example.dalhousievotingsystem15.Candidate_Login");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'Candidate_LogIn'.");
        }

    }

}

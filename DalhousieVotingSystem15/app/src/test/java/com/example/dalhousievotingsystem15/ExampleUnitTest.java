package com.example.dalhousievotingsystem15;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
     @Test
    public void IDTest(){
        Validator v1 = new Validator("","AABBCCDDEE");
        Validator v2 = new Validator("123","ABCDEFG");
        Validator v3 = new Validator("","2333333");



        assertFalse(v1.IDNotEmpty());
        assertTrue(v2.IDNotEmpty());
        assertFalse(v3.IDNotEmpty());

    }
}
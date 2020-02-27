package com.example.dalhousievotingsystem15;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class InputTest {
    @Test
    public void IDTest(){
        Validator v1 = new Validator("","AABBCCDDEE");
        Validator v2 = new Validator("123","ABCDEFG");
        Validator v3 = new Validator("","2333333");


        assertFalse(v1.IDNotEmpty());
        assertTrue(v2.IDNotEmpty());
        assertFalse(v3.IDNotEmpty());

    }

    @Test
    public void PWDTest(){
        Validator v1 = new Validator("ABC","AABBCCDDEE");
        Validator v2 = new Validator("123","");
        Validator v3 = new Validator("","2333333");


        assertTrue(v1.PWDNotEmpty());
        assertFalse(v2.PWDNotEmpty());
        assertTrue(v3.PWDNotEmpty());

    }

    @Test
    public void LengthTest(){
        Validator v1 = new Validator("ABC","AABBCCDDEE");
        Validator v2 = new Validator("Someone","");
        Validator v3 = new Validator("","233");
        Validator v4 = new Validator("Someone","123vd");

        assertTrue(v1.lengthCheck());
        assertFalse(v2.lengthCheck());
        assertFalse(v3.lengthCheck());
        assertFalse(v4.lengthCheck());

    }

    @Test
    public void capitalTest(){
        Validator v1 = new Validator("ABC","AABBCCDDEE");
        Validator v2 = new Validator("Someone","abDCed");
        Validator v3 = new Validator("bb","233");
        Validator v4 = new Validator("Someone","123Bd");

        assertTrue(v1.ContainsCaptial());
        assertTrue(v2.ContainsCaptial());
        assertFalse(v3.ContainsCaptial());
        assertTrue(v4.ContainsCaptial());
    }

    @Test
    public void numTest(){
        Validator v1 = new Validator("ABC","AABBCCDDEE");
        Validator v2 = new Validator("Someone","abDCed");
        Validator v3 = new Validator("bb","233");
        Validator v4 = new Validator("Someone","123Bd");

        assertFalse(v1.CombineNumandLetter());
        assertFalse(v2.CombineNumandLetter());
        assertFalse(v3.CombineNumandLetter());
        assertTrue(v4.CombineNumandLetter());
    }

    @Test
    public void strongEnough(){
        Validator v1 = new Validator("ABC","AAVVCCCED");
        Validator v2 = new Validator("Someone","abDCed123");
        Validator v3 = new Validator("bb","2AvC");
        Validator v4 = new Validator("Someone","");

        assertFalse(v1.StrongPWD());
        assertTrue(v2.StrongPWD());
        assertFalse(v3.StrongPWD());
        assertFalse(v4.StrongPWD());
    }



}

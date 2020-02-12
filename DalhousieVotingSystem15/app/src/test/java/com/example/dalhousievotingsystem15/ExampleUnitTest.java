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
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void candidate_pagetest(){
        Candidate_Page c1 =new Candidate_Page;
        c1.setStudent(true);
        c1.setCandidate(false);
        Candidate_Page c2 =new Candidate_Page;
        c1.setStudent(false);
        c1.setCandidate(true);
    }
}
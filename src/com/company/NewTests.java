package com.company;


import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

/**
 * Created by Admin on 08.04.15.
 */

@RunWith(JUnit4.class)
public class NewTests {

@Test
    public void Test1(){
        Assert.fail();

    }

@Test

    public void Test2 () {

    int a = 10;
    int b = 5;
    int expectedResult = 15;
    Assert.assertEquals("Expected results did not match.", expectedResult, a + b);
    }
}

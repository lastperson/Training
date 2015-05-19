package com.company;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.company.UZ_PageObject.*;

/**
 * Created by Admin on 18.05.15.
 */

@RunWith(JUnit4.class)

public class UZ_test {

    @Before

    public void initialize(){

        open();

    }

    @After

    public void cleanUp () {

        clean();
    }

    @Test

    public void Test1(){

        openUrl("http://booking.uz.gov.ua/en/"); // Why not in @Before? Other links planned in this test suite?
        setFromField("Kyiv");
        setToField ("Ivano-Frankivsk");
        setDepDate("June 2015", "20");
        search();
        Assert.assertTrue(verifySearchResult("043 К"));
        Assert.assertTrue(verifySearchResult("143 К")); // What if there is other(wrong) results on the screen?
        selectTrain("043 К");
        verifyPresent(routeWindow);
        close (routeWindow);
        openPlan("043 К"); // What if I want to buy other ticket class, not Coupe?
        chooseCoach("5");
        choosePlace("29");
        setField(lastName, "Doe");
        setField(firstName, "John");
    }
}

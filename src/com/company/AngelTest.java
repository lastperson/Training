package com.company;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * Created by Admin on 08.04.15.
 */

@RunWith(JUnit4.class)

public class AngelTest {

    public static WebDriver driver;

    @After

    public void clean() {

        driver.quit();

    }

    @Before

    public void initialize() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new org.openqa.selenium.chrome.ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://oxogamestudio.com/passwd.current9.htm");
        while (driver.findElements(By.xpath("//input[@value='Generate']")).size() <= 0){
            Thread.sleep(100);
        }
        PassGenPage.buttonGenerate = driver.findElement(By.xpath("//input[@value='Generate']"));
        PassGenPage.masterField = driver.findElement(By.xpath("//input[@type='password']"));
        PassGenPage.siteField = driver.findElement(By.xpath("(//tr/td/input)[2]"));
        PassGenPage.passwordField = driver.findElement(By.xpath("(//tr/td/input)[4]"));
        }



    @Test

    public void Test1() throws Exception {

        PassGenPage.passwordIsCorrect("12345678", "gmail.com", "W3Hdka0clbEI+@1a");
        Assert.assertTrue(PassGenPage.verifyNotCleared());
        Assert.assertTrue(PassGenPage.verifyFieldsEnabled());
        Assert.assertEquals(PassGenPage.getLabel("Your master password"), "Your master password");
        Assert.assertEquals(PassGenPage.getLabel("Site name"), "Site name");
        Assert.assertEquals(PassGenPage.getLabel("Generated password"), "Generated password");
    }

    @Test

    public void Test2() throws Exception{

        PassGenPage.passwordIsCorrect("", "gmail.com", "zmcHOAyf2oZm+@1a");
        Assert.assertTrue(PassGenPage.verifyFieldsEnabled());
        Assert.assertEquals(PassGenPage.getLabel("Your master password"), "Your master password");
        Assert.assertEquals(PassGenPage.getLabel("Site name"), "Site name");
        Assert.assertEquals(PassGenPage.getLabel("Generated password"), "Generated password");

    }


    @Test

    public void Test3() throws Exception{

        PassGenPage.passwordIsCorrect("12345678", "", "9Ixm2r5Xnm41Q@1a");
        Assert.assertTrue(PassGenPage.verifyFieldsEnabled());
        Assert.assertEquals(PassGenPage.getLabel("Your master password"), "Your master password");
        Assert.assertEquals(PassGenPage.getLabel("Site name"), "Site name");
        Assert.assertEquals(PassGenPage.getLabel("Generated password"), "Generated password");

    }


    @Test

    public void Test4()throws Exception{

        PassGenPage.generate(driver);
        PassGenPage.passwordIsCorrect("", "", "BaefBs8/Z/cm2@1a");
        Assert.assertTrue(PassGenPage.verifyFieldsEnabled());
        Assert.assertEquals(PassGenPage.getLabel("Your master password"), "Your master password");
        Assert.assertEquals(PassGenPage.getLabel("Site name"), "Site name");
        Assert.assertEquals(PassGenPage.getLabel("Generated password"), "Generated password");

    }


    @Test

    public void Test5()throws Exception {

        String s = "";

        for (int i = 0; i < 200; i++) {
           s += 1;
           }

        PassGenPage.setField("Your master password", s);
        PassGenPage.setField("Site name", s);
        PassGenPage.generate(driver);
        String password = PassGenPage.getField("Generated password");
        Assert.assertEquals("Password did not match!", password, "aR8ztwNBbSqe5@1a");
        Assert.assertTrue(PassGenPage.verifyNotCleared());
        Assert.assertTrue(PassGenPage.verifyFieldsEnabled());
        Assert.assertEquals(PassGenPage.getLabel("Your master password"), "Your master password");
        Assert.assertEquals(PassGenPage.getLabel("Site name"), "Site name");
        Assert.assertEquals(PassGenPage.getLabel("Generated password"), "Generated password");

    }


    @Test

    public void Test6()throws Exception {

        PassGenPage.setField("Your master password", "/';*&#&$@^!_...\\n");
        PassGenPage.setField("Site name", "/';*&#&$@^!_...\\n");
        PassGenPage.generate(driver);
        String password = PassGenPage.getField("Generated password");
        Assert.assertEquals("Password did not match!", password, "ctolW6AdI0te1@1a");
        Assert.assertTrue(PassGenPage.verifyNotCleared());
        Assert.assertTrue(PassGenPage.verifyFieldsEnabled());
        Assert.assertEquals(PassGenPage.getLabel("Your master password"), "Your master password");
        Assert.assertEquals(PassGenPage.getLabel("Site name"), "Site name");
        Assert.assertEquals(PassGenPage.getLabel("Generated password"), "Generated password");

    }


    @Test

    public void Test7() throws  InterruptedException{

        String buttonLabel = PassGenPage.buttonGenerate.getAttribute("value");
        Assert.assertEquals("Label did not match!", buttonLabel, "Generate");
        Assert.assertTrue(PassGenPage.verifyFieldsEnabled());
        Assert.assertEquals(PassGenPage.getLabel("Your master password"), "Your master password");
        Assert.assertEquals(PassGenPage.getLabel("Site name"), "Site name");
        Assert.assertEquals(PassGenPage.getLabel("Generated password"), "Generated password");

    }

}



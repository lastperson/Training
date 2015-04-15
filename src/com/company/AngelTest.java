package com.company;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

/**
 * Created by Admin on 08.04.15.
 */

@RunWith(JUnit4.class)

public class AngelTest {

    public WebDriver driver;
    WebElement buttonGenerate;
    List<WebElement> td;

    @After

    public void clean() {

        driver.quit();

    }

    @Before

    public void initialize() {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.get("http://angel.net/~nic/passwd.current.html");
        driver.get("http://oxogamestudio.com/passwd.current8.htm");
        List<WebElement> input = driver.findElements(By.tagName("input"));
        Homework_6.buttonGenerate = buttonGenerate = input.get(2);
        Homework_6.masterField = driver.findElement(By.name("master"));
        Homework_6.siteField = driver.findElement(By.name("site"));
        Homework_6.passwordField = driver.findElement(By.name("password"));
        td = driver.findElements(By.tagName("td"));

    }

    public String generatePassword(String master, String site) throws InterruptedException {

        Homework_6.setMaster(master);
        Homework_6.setSite(site);
        Homework_6.generate();

        return Homework_6.getPassword();

    }

    public boolean compare(String a, String b, String example) throws Exception{

        String generated = generatePassword(a, b);
        if (generated.equals(example)) {
            return true;
        } else return false;
    }

    public boolean verifyNotCleared(){

        if(!Homework_6.getMaster().equals("") && !Homework_6.getSite().equals(""))
        return true;
        else return false;
    }

    public boolean verifyFieldsEnabled(){

        if (Homework_6.masterField.isEnabled() && Homework_6.siteField.isEnabled() && Homework_6.passwordField.isEnabled()) return true;
        else return false;
    }

    public String getMasterLabel(){

    String s = td.get(0).getText();
    return s;

    }

    public String getSiteLabel(){

        String s = td.get(2).getText();
        return s;
    }

    public String getPasswordLabel(){

        String s = td.get(5).getText();
        return s;
    }

    @Test

    public void Test1() throws Exception {

        Assert.assertTrue("Password did not match.", compare("12345678", "gmail.com", "W3Hdka0clbEI+@1a"));
        Assert.assertTrue(verifyNotCleared());
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");
    }

    @Test

    public void Test2() throws Exception{

        Assert.assertTrue("Password did not match.", compare("", "gmail.com", "zmcHOAyf2oZm+@1a"));

    }


    @Test

    public void Test3() throws Exception{

        Assert.assertTrue("Password did not match.", compare("12345678", "", "9Ixm2r5Xnm41Q@1a"));

    }


    @Test

    public void Test4()throws Exception{

        Homework_6.generate();
        Assert.assertTrue("Password did not match.", compare("", "", "BaefBs8/Z/cm2@1a"));

    }


    @Test

    public void Test5()throws Exception {

        String s = "";

        for (int i = 0; i < 200; i++) {
           s += 1;
           }

        Homework_6.setMaster(s);
        Homework_6.setSite(s);
        Homework_6.generate();
        String password = Homework_6.getPassword();
        Assert.assertEquals("Password did not match!", password, "aR8ztwNBbSqe5@1a");

    }


    @Test

    public void Test6()throws Exception {

        Homework_6.setMaster("/';*&#&$@^!_...\\n");
        Homework_6.setSite("/';*&#&$@^!_...\\n");
        Homework_6.generate();
        String password = Homework_6.getPassword();
        Assert.assertEquals("Password did not match!", password, "ctolW6AdI0te1@1a");

    }


    @Test

    public void Test7(){

        String buttonLabel = buttonGenerate.getAttribute ("value");
        Assert.assertEquals("Label did not match!", buttonLabel, "Generate");

    }



}



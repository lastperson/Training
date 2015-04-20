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


/**
 * Created by Admin on 08.04.15.
 */

@RunWith(JUnit4.class)

public class AngelTest {

    public WebDriver driver;

    @After

    public void clean() {

        driver.quit();

    }

    @Before

    public void initialize() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.get("http://angel.net/~nic/passwd.current.html");
        driver.get("http://oxogamestudio.com/passwd.current8.htm");
        while (driver.findElements(By.xpath("//input[@value='Generate']")).size() <= 0){
            Thread.sleep(100);
        }
        Homework_6.buttonGenerate = driver.findElement(By.xpath("//input[@value='Generate']"));
        Homework_6.masterField = driver.findElement(By.xpath("//input[@type='password']"));
        Homework_6.siteField = driver.findElement(By.xpath("(//tr/td/input)[2]"));
        Homework_6.passwordField = driver.findElement(By.xpath("(//tr/td/input)[4]"));
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

    String s = driver.findElement(By.xpath("//td[text()='Your master password']")).getText();

    return s;

    }

    public String getSiteLabel(){

        String s = driver.findElement(By.xpath("//td[text()='Site name']")).getText();
        return s;
    }

    public String getPasswordLabel(){

        String s = driver.findElement(By.xpath("//td[text()='Generated password']")).getText();
        return s;
    }

    public void passwordIsCorrect (String masterPassword, String siteName, String generatedPassword) throws InterruptedException {

        setField("Your master password", masterPassword);
        setField("Site name",siteName);
        Homework_6.generate();
        String pwd = getField("Generated password");
        Assert.assertEquals("Password did not match.", generatedPassword, pwd);

    }

    public void setField (String labelName, String fieldValue){

        WebElement field = driver.findElement(By.xpath("//td[text()='" + labelName + "']/following::input[1]"));

        field.sendKeys(fieldValue);

    }

    public String getField (String labelName){

        String s = driver.findElement(By.xpath("//td[text()='" + labelName + "']/following::input[1]")).getAttribute("value");

        return s;
    }

    @Test

    public void Test1() throws Exception {

        passwordIsCorrect("12345678", "gmail.com", "W3Hdka0clbEI+@1a");
        Assert.assertTrue(verifyNotCleared());
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");
    }

    @Test

    public void Test2() throws Exception{

        passwordIsCorrect("", "gmail.com", "zmcHOAyf2oZm+@1a");
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");

    }


    @Test

    public void Test3() throws Exception{

        passwordIsCorrect("12345678", "", "9Ixm2r5Xnm41Q@1a");
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");

    }


    @Test

    public void Test4()throws Exception{

        Homework_6.generate();
        passwordIsCorrect("", "", "BaefBs8/Z/cm2@1a");
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");

    }


    @Test

    public void Test5()throws Exception {

        String s = "";

        for (int i = 0; i < 200; i++) {
           s += 1;
           }

        setField("Your master password", s);
        setField("Site name", s);
        Homework_6.generate();
        String password = getField("Generated password");
        Assert.assertEquals("Password did not match!", password, "aR8ztwNBbSqe5@1a");
        Assert.assertTrue(verifyNotCleared());
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");

    }


    @Test

    public void Test6()throws Exception {

        setField("Your master password", "/';*&#&$@^!_...\\n");
        setField("Site name", "/';*&#&$@^!_...\\n");
        Homework_6.generate();
        String password = getField("Generated password");
        Assert.assertEquals("Password did not match!", password, "ctolW6AdI0te1@1a");
        Assert.assertTrue(verifyNotCleared());
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");

    }


    @Test

    public void Test7() throws  InterruptedException{

        String buttonLabel = Homework_6.buttonGenerate.getAttribute("value");
        Assert.assertEquals("Label did not match!", buttonLabel, "Generate");
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");

    }

}



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
    WebElement buttonGenerate;

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
        driver.get("http://oxogamestudio.com/passwd.current7.htm");
        while (driver.findElements(By.xpath("//input[@value='Generate']")).size() <= 0){
            Thread.sleep(100);
        }
        Homework_6.buttonGenerate = driver.findElement(By.xpath("//input[@value='Generate']"));
        Homework_6.masterField = driver.findElement(By.xpath("//input[@type='password']"));
        Homework_6.siteField = driver.findElement(By.xpath("(//tr/td/input)[2]"));
        Homework_6.passwordField = driver.findElement(By.xpath("(//tr/td/input)[4]"));

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

    public void titleIsCorrect (){

        setField("Your master password", "12345678");
        setField("Site name","gmail.com");
        generate();
        String pwd = getField("Generated password");
        Assert.assertEquals("W3Hdka0clbEI+@1a", pwd);

    }

    public void setField (String labelName, String fieldValue){

        int fieldNumber = driver.findElements(By.xpath("//td[text()='" + labelName + "'")).size();
        WebElement field = driver.findElement(By.xpath(""));

        for (int i = 0; i < fieldNumber; i++){

            field.sendKeys(fieldValue);
        }

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
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");

    }


    @Test

    public void Test3() throws Exception{

        Assert.assertTrue("Password did not match.", compare("12345678", "", "9Ixm2r5Xnm41Q@1a"));
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");

    }


    @Test

    public void Test4()throws Exception{

        Homework_6.generate();
        Assert.assertTrue("Password did not match.", compare("", "", "BaefBs8/Z/cm2@1a"));
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

        Homework_6.setMaster(s);
        Homework_6.setSite(s);
        Homework_6.generate();
        String password = Homework_6.getPassword();
        Assert.assertEquals("Password did not match!", password, "aR8ztwNBbSqe5@1a");
        Assert.assertTrue(verifyNotCleared());
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");

    }


    @Test

    public void Test6()throws Exception {

        Homework_6.setMaster("/';*&#&$@^!_...\\n");
        Homework_6.setSite("/';*&#&$@^!_...\\n");
        Homework_6.generate();
        String password = Homework_6.getPassword();
        Assert.assertEquals("Password did not match!", password, "ctolW6AdI0te1@1a");
        Assert.assertTrue(verifyNotCleared());
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");

    }


    @Test

    public void Test7(){

        String buttonLabel = buttonGenerate.getAttribute ("value");
        Assert.assertEquals("Label did not match!", buttonLabel, "Generate");
        Assert.assertTrue(verifyFieldsEnabled());
        Assert.assertEquals(getMasterLabel(), "Your master password");
        Assert.assertEquals(getSiteLabel(), "Site name");
        Assert.assertEquals(getPasswordLabel(), "Generated password");

    }



}



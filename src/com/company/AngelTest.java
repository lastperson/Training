package com.company;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 08.04.15.
 */

@RunWith(JUnit4.class)

public class AngelTest {

    public WebDriver driver;
    WebElement masterField;
    WebElement siteField;
    WebElement passwordField;
    WebElement buttonGenerate;

    @After

    public void clean() {

        driver.quit();

    }

    @Before

    public void initialize() {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://angel.net/~nic/passwd.current.html");
        masterField = driver.findElement(By.name("master"));
        masterField.clear();
        siteField = driver.findElement(By.name("site"));
        siteField.clear();
        passwordField = driver.findElement(By.name("password"));
        List<WebElement> input = driver.findElements(By.tagName("input"));
        buttonGenerate = input.get(2);
    }

    public String generatePassword(String master, String site) {

        WebElement masterField = driver.findElement(By.name("master"));
        masterField.clear();
        masterField.sendKeys(master);
        WebElement siteField = driver.findElement(By.name("site"));
        siteField.clear();
        siteField.sendKeys(site);
        buttonGenerate.click();
        WebElement passwordField = driver.findElement(By.name("password"));
        String password = passwordField.getAttribute("value");
        return password;

    }

    public boolean compare(String a, String b, String example) {

        String generated = generatePassword(a, b);
        if (generated.equals(example)) {
            return true;
        } else return false;
    }

    @Test

    public void Test1() {

        Assert.assertTrue("Password did not match.", compare("12345678", "gmail.com", "W3Hdka0clbEI+@1a"));

    }

    @Test

    public void Test2() {

        Assert.assertTrue("Password did not match.", compare("", "gmail.com", "zmcHOAyf2oZm+@1a"));

    }


    @Test

    public void Test3() {

        Assert.assertTrue("Password did not match.", compare("12345678", "", "9Ixm2r5Xnm41Q@1a"));

    }


    @Test

    public void Test4(){

        buttonGenerate.click();
        Assert.assertTrue("Password did not match.", compare("", "", "BaefBs8/Z/cm2@1a"));

    }


    @Test

    public void Test5(){

        for (int i = 0; i < 200; i++) {
           masterField.sendKeys("1");
           siteField.sendKeys("1");
           }

        siteField.sendKeys(Keys.ENTER);
        String password = passwordField.getAttribute("value");
        Assert.assertEquals("Password did not match!", password, "aR8ztwNBbSqe5@1a");

    }


    @Test

    public void Test6(){

        masterField.sendKeys("/';*&#&$@^!_...\\n");
        siteField.sendKeys("/';*&#&$@^!_...\\n");
        siteField.sendKeys(Keys.ENTER);
        String password = passwordField.getAttribute("value");
        Assert.assertEquals("Password did not match!", password, "ctolW6AdI0te1@1a");

    }


    @Test

    public void Test7(){

        String buttonLabel = buttonGenerate.getAttribute ("value");
        Assert.assertEquals("Label did not match!", buttonLabel, "Generate");

    }



}



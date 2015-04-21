package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



import java.util.List;

/**
 * Created by Dimonaz on 13.04.2015.
 */
public class Homework_6 {

    public static WebDriver driver;
    public static WebElement masterField;
    public static WebElement siteField;
    public static WebElement passwordField;
    public static WebElement buttonGenerate;

    public static void setMaster(String master) {

        masterField.clear();
        masterField.sendKeys(master);

    }

    public static String getMaster(){

        String master = "";
        master = masterField.getAttribute("value");
        return master;

    }

    public static void setSite(String site) {

        siteField.clear();
        siteField.sendKeys(site);

    }

    public static String getSite(){

        String site = "";
        site = siteField.getAttribute("value");
        return site;

    }

    public static void generate(WebDriver driver) throws InterruptedException {

        buttonGenerate.click();

        try {
            driver.switchTo().alert().accept();
        } catch (Exception e){

        }

    while (passwordField.getAttribute("value").equals(""))
        Thread.sleep(100);
    }

    public static String getPassword() {

        String password = "";
        password = passwordField.getAttribute("value");
        return password;

    }

    public static void verify (String password){

        String generatedPassword = getPassword();
        if (generatedPassword.equals(password)){

            System.out.println("Password matches!");
        }

        else System.out.println("Passwords do not match!");
    }

    public static String title (){

        String title = "";
        title = driver.getTitle();
        return title;

    }

    public static String button(){

        String button = "";
        button = buttonGenerate.getAttribute("value");
        return button;

    }

    public static void main(String[] args) throws Exception{

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

        setMaster("12345678");
        setSite("gmail.com");
        generate(driver);
        System.out.println(getPassword());
        verify("W3Hdka0clbEI+@1a");

        System.out.println(getMaster());

        System.out.println(getSite());

        System.out.println(title());

        System.out.println(button());

        setSite("");
        generate(driver);
        System.out.println(getPassword());
        verify("9Ixm2r5Xnm41Q@1a");

        driver.quit();

    }

}

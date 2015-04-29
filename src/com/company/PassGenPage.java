package com.company;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Admin on 22.04.15.
 */
public class PassGenPage {

    public static WebDriver driver;
    public static WebElement masterField;
    public static WebElement siteField;
    public static WebElement passwordField;
    public static WebElement buttonGenerate;



    public static void open (WebDriver dr){
        
        driver = dr;
        dr.get("http://oxogamestudio.com/passwd.current9.htm");
        PassGenPage.buttonGenerate = driver.findElement(By.xpath("//input[@value='Generate']"));
        PassGenPage.masterField = driver.findElement(By.xpath("//input[@type='password']"));
        PassGenPage.siteField = driver.findElement(By.xpath("(//tr/td/input)[2]"));
        PassGenPage.passwordField = driver.findElement(By.xpath("(//tr/td/input)[4]"));

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

   public static void verify (String password){

        String generatedPassword = getField("Generated password");
        if (generatedPassword.equals(password)){

            System.out.println("Password matches!");
        }

        else System.out.println("Passwords do not match!");
    }

    public static String title (){

        String title = driver.getTitle();
        return title;

    }

    public static String button(){

        String button;
        button = buttonGenerate.getAttribute("value");
        return button;

    }

    public static boolean verifyNotCleared(){

        if(!PassGenPage.getField("Your master password").equals("") && !PassGenPage.getField("Site name").equals(""))
            return true;
        else return false;
    }

    public static boolean verifyFieldsEnabled(){

        if (PassGenPage.masterField.isEnabled() && PassGenPage.siteField.isEnabled() && PassGenPage.passwordField.isEnabled()) return true;
        else return false;
    }

    public static String getLabel(String labelName){

        String s = driver.findElement(By.xpath("//td[text()='" + labelName + "']")).getText();

        return s;

    }

    public static void passwordIsCorrect (String masterPassword, String siteName, String generatedPassword) throws InterruptedException {

        setField("Your master password", masterPassword);
        setField("Site name",siteName);
        PassGenPage.generate(driver);
        String pwd = getField("Generated password");
        Assert.assertEquals("Password did not match.", generatedPassword, pwd);

    }

    public static void setField (String labelName, String fieldValue){

        WebElement field = driver.findElement(By.xpath("//td[text()='" + labelName + "']/following::input[1]"));

        field.sendKeys(fieldValue);

    }

    public static String getField (String labelName){

        String s = driver.findElement(By.xpath("//td[text()='" + labelName + "']/following::input[1]")).getAttribute("value");

        return s;
    }


}

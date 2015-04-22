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

    static WebDriver driver = PassGenPage.driver;

    public static void main(String[] args) throws Exception{

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
        PassGenPage.open(driver);
        driver.manage().window().maximize();
        PassGenPage.buttonGenerate = driver.findElement(By.xpath("//input[@value='Generate']"));
        PassGenPage.masterField = driver.findElement(By.xpath("//input[@type='password']"));
        PassGenPage.siteField = driver.findElement(By.xpath("(//tr/td/input)[2]"));
        PassGenPage.passwordField = driver.findElement(By.xpath("(//tr/td/input)[4]"));

        PassGenPage.masterField.clear();
        PassGenPage.siteField.clear();

        PassGenPage.setField("Your master password", "12345678");
        PassGenPage.setField("Site name", "gmail.com");
        PassGenPage.generate(driver);
        System.out.println(PassGenPage.getField("Generated password"));
        PassGenPage.verify("W3Hdka0clbEI+@1a");

        System.out.println(PassGenPage.getField("Your master password"));

        System.out.println(PassGenPage.getField("Site name"));

        System.out.println(PassGenPage.title());

        System.out.println(PassGenPage.button());

        PassGenPage.setField("Site name", "");
        PassGenPage.generate(driver);
        System.out.println(PassGenPage.getField("Generated password"));
        PassGenPage.verify("9Ixm2r5Xnm41Q@1a");

        driver.quit();

    }

}

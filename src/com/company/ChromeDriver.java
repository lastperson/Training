package com.company;

import org.openqa.selenium.WebDriver;

/**
 * Created by Dimonaz on 31.03.2015.
 */
public class ChromeDriver {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        WebDriver driver = new org.openqa.selenium.chrome.ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.google.com");

        Thread.sleep(1000);


        driver.quit();

    }







}

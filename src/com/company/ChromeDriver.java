package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.plaf.ListUI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimonaz on 31.03.2015.
 */
public class ChromeDriver {

    public static String generatePassword (WebDriver driver, String master, String site){

        WebElement masterField = driver.findElement(By.name("master"));
        masterField.clear();
        masterField.sendKeys(master);
        WebElement siteField = driver.findElement(By.name("site"));
        siteField.clear();
        siteField.sendKeys(site, Keys.ENTER);
        WebElement passwordField = driver.findElement(By.name("password"));
        String password = passwordField.getAttribute("value");
        return password;

    }

    public static void compare (WebDriver driver, String a, String b, String example){

        String generated = generatePassword(driver, a, b);
        if (generated.equals(example)){
            System.out.println("Test PASSED!");
        } else System.out.println("Test FAILED!");
    }

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        WebDriver driver = new org.openqa.selenium.chrome.ChromeDriver();

        driver.manage().window().maximize();
        driver.get("http://angel.net/~nic/passwd.current.html");
        /*driver.get("http://www.google.com");

        String title = driver.getTitle();
        WebElement searchField = driver.findElement(By.id("lst-ib"));
        searchField.sendKeys("QA factory", Keys.ENTER);
        Thread.sleep(3000);
        WebElement rso = driver.findElement(By.id("rso"));
        WebElement firstLink = rso.findElement(By.tagName("a"));
        System.out.println(firstLink.getText());

        List<WebElement> list = rso.findElements(By.tagName("a"));

        for (int i = 0; i < list.size(); i++){

            System.out.println(list.get(i).getText() + "\n" + list.get(i).getAttribute("href"));

            }

        searchField.clear();
        searchField.sendKeys("new search");
        driver.findElement(By.name("btnG")).click();*/

        compare(driver, "MyPassword123","dmitriy.naydenov@gmail.com", "lUhqIJa8e9/bl@1a");
        compare(driver, "","", "BaefBs8/Z/cm2@1a");

        //driver.quit();

    }







}

package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.plaf.ListUI;
import java.util.List;

/**
 * Created by Dimonaz on 31.03.2015.
 */
public class ChromeDriver {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        WebDriver driver = new org.openqa.selenium.chrome.ChromeDriver();

        driver.manage().window().maximize();
        driver.get("http://www.google.com");

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
        driver.findElement(By.name("btnG")).click();

        //driver.quit();

    }







}

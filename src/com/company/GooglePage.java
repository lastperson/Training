package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by Admin on 29.04.15.
 */
public class GooglePage {

    public static WebDriver driver;
    public final static int WAIT_COUNTER = 20000;
    public static String inputField = "//textarea[@id = 'source']";
    public static String resultField = "//span[@id = 'result_box']";
    public static String translateButton = "//input[@id='gt-submit']";
    public static String languageArrow = "//div[@id='gt-sl-gms']";



    public static void open (WebDriver driver){

        GooglePage.driver = driver;
        driver.get("https://translate.google.com");
        }

    public static WebElement findElement(String xpath){

        WebElement result = driver.findElement(By.xpath(xpath));
        return result;
    }

    public static boolean isEmpty (String xpath){

        if (driver.findElement(By.xpath(xpath)).getText().equals("")) return true;
        else return false;
    }

    public static String getTitle (WebDriver driver){

        String s = driver.getTitle();
        return s;
    }

    public static boolean verifyPresent (String xpath)throws Exception{

        int counter = 0;
        while(driver.findElements(By.xpath(xpath)).size() == 0) {
            Thread.sleep(100);
            counter += 100;
            if (counter > WAIT_COUNTER) {
                return false;
            }
        }
        return true;
    }

    public static boolean verifyEditable (String xpath){

    if (findElement(xpath).isEnabled() && !findElement(xpath).getTagName().equals("span")) return true;
        else return false;
    }
}

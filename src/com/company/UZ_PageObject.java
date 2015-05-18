package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.Charset;
import java.util.NoSuchElementException;

/**
 * Created by Admin on 18.05.15.
 */
public class UZ_PageObject {

    public static final String fromField = "//input[@name='station_from']";
    public static final String toField = "//input[@name='station_till']";
    public static final String depDate = "//input[@id='date_dep']";
    public static final String searchButton = "//button[@name='search']";
    public static final String resultTable = "//table[@id='ts_res_tbl']";
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void open(){

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new org.openqa.selenium.chrome.ChromeDriver();
        }

    public static void openUrl (String url){

        driver.get(url);
    }

    public static void clean(){

        driver.quit();

    }

    public static WebElement getElement (String xpath){

        return driver.findElement(By.xpath(xpath));

    }

    /*public static void clearInput (){

        getElement(clearButton).click();
    }*/

    public static void setACField (String xpath, String value){

        getElement(xpath).sendKeys(value);
        getElement("//div[@id='stations_from']/div[@title='" + value +   "']").click();

    }

    public static String getField (String xpath){

        if (getElement(xpath).getTagName().equals("span")){
            String tmp = getElement(xpath).getText();
            return new String (tmp.getBytes(Charset.forName("utf-8")));
        }
        else return getElement(xpath).getAttribute("value");
    }

    public static void setDepDate (String month, String day){

        getElement(depDate).click();
        getElement("//caption[text()='" + month + "']/..//td[text()='" + day + "']").click();
    }

    public static void click (String xpath){

        getElement(xpath).click();
    }

    public static boolean verifySearchResult(String trainNo){

        try{

            getElement(resultTable).findElement(By.xpath("//a[text()='" + trainNo + "']"));
        }catch (NoSuchElementException e){
            System.out.println("No such train found!");
            return false;
        }
        return true;
    }
}


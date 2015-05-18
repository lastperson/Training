package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    public static final String routeWindow = "//span[text()='Train route']/../..";
    public static final String chooseCoupeButton = "//div[contains(@title,'Coupe')]";
    public static WebDriver driver;

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

    public static void setFromField (String value){

        getElement(fromField).sendKeys(value);
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='stations_from']/div[@title='" + value + "']"))).click();
        }
        catch (TimeoutException e){
            System.out.println("No such autocomplete option!");
            }
        }

    public static void setToField (String value){

        getElement(toField).sendKeys(value);
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='stations_till']/div[@title='" + value + "']"))).click();
        }
        catch (TimeoutException e){
            System.out.println("No such autocomplete option!");
        }
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

    public static void search(){

        click(searchButton);
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ts_res']")));
        } catch (TimeoutException e){
            System.out.println("No results present!");
        }
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

    public static void selectTrain (String trainNo){

        getElement(resultTable).findElement(By.xpath("//a[text()='" + trainNo + "']")).click();

    }

    public static boolean verifyPresent (String xpath){

        try {
            WebElement element = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        }catch (TimeoutException e){
            System.out.println("No element found!");
            return false;
        }
        return true;
    }

    public static void close (String xpath){

        getElement(xpath).findElement(By.xpath("//a[@title='close']")).click();

    }

    public static void openPlan(String trainNo) {

        getElement("//a[text()='" + trainNo + "']/../following-sibling::td[@class='place']/div[starts-with(@title,'Coupe')]/button[text()='Choose']").click();

    }

    public static void chooseCoach(String coach) {

        getElement("//span[@class='coaches']/a[@href='#" +  coach + "']").click();

    }

    public static void choosePlace(String place) {

        getElement("//span[@id='places']//span[text()='" + place + "']");

    }
}


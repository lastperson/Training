package com.company;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty7.util.thread.Timeout;

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
    public static final String lastName = "//input[@class='lastname']";
    public static final String firstName = "//input[@class='firstname']";
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

    public static void setField (String field, String value){

        getElement(field).sendKeys(value);
    }

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

         new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='" + trainNo + "']")));
        }catch (TimeoutException e){
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
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        }catch (TimeoutException e){
            System.out.println("No element found!");
            return false;
        }
        return true;
    }

    public static void close (String xpath){

        getElement(xpath).findElement(By.xpath("//a[@title='close']")).click();

        int count = 0;
        while (driver.findElements(By.xpath(xpath)).size() > 0) {
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                   }
            count++;
            if (count > 50){
                System.out.println("Cannot close!");
                break;
            }
        }
    }

    public static void openPlan(String trainNo){

        try {
            WebElement selectButton = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + trainNo + "']/../following-sibling::td[@class='place']/div[starts-with(@title,'Coupe')]/button[text()='Choose']")));
            selectButton.click();
        }catch (TimeoutException e){
            System.out.println("Could not click Select button!");
        }

    }

    public static void chooseCoach(String coach) {

        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Select your place']")));
        }catch (TimeoutException e){
            System.out.println("Plan did not open!");
        }
        getElement("//span[@class='coaches']/a[@href='#" + coach + "']").click();
        new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading_img']")));


    }

    public static void choosePlace(String place) {

        try {
            WebElement placeButton = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='places']//span[text()='" + place + "']")));
            if (placeButton.getCssValue("color").equals("rgba(204, 204, 204, 1)")) {
                System.out.println("Place already taken!");
            }
            int count = 0;
            while (driver.findElements(By.xpath("//input[@class='lastname']")).size() < 1) {
                try {
                    placeButton.click();
                }catch (StaleElementReferenceException e){
                    placeButton = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='places']//span[text()='" + place + "']")));
                    placeButton.click();
                }
                Thread.sleep(500);
                System.out.println("Clicking again!");
                count++;
                if (count > 5){
                System.out.println("Last name field did not show up!");
                    break;
                    }
                }
            }catch (InterruptedException e) {
        }
    }

}


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

    public static void open(){ // This method is not related to this particular page object.

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new org.openqa.selenium.chrome.ChromeDriver();
        }

    public static void openUrl (String url){ // This method is not related to this particular page object.

        driver.get(url);
    }

    public static void clean(){ 

        driver.quit(); // Contents of this method is not related to this particular page object.

    }

    public static WebElement getElement (String xpath){ // This method is not related to this particular page object.

        return driver.findElement(By.xpath(xpath));

    }

    public static void setField (String field, String value){ // Why is it public?

        getElement(field).sendKeys(value);
    }

    public static void setFromField (String value){

        getElement(fromField).sendKeys(value);
        try { // There is the same wait everywhere why create it everytime?
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='stations_from']/div[@title='" + value + "']"))).click();
        }
        catch (TimeoutException e){ 
            System.out.println("No such autocomplete option!"); // Test won't fail, only message in console will appear.
            }
        }

    public static void setToField (String value){

        getElement(toField).sendKeys(value);
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='stations_till']/div[@title='" + value + "']"))).click();
        }
        catch (TimeoutException e){
            System.out.println("No such autocomplete option!"); // Test won't fail, only message in console will appear.
        }
    }

   public static void setDepDate (String month, String day){

        getElement(depDate).click(); // You made click(xpath) method, why not using it?
        getElement("//caption[text()='" + month + "']/..//td[text()='" + day + "']").click(); // You made click(xpath) method, why not using it?
    }

    public static void click (String xpath){ // This method is not related to this particular page object.

        getElement(xpath).click();
    }

    public static void search(){

        click(searchButton);
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='ts_res']")));
        } catch (TimeoutException e){
            System.out.println("No results present!"); // Test won't fail, only message in console will appear.
        }
    }

    public static boolean verifySearchResult(String trainNo){

        try{

         new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='" + trainNo + "']")));
        }catch (TimeoutException e){
            System.out.println("No such train found!"); // What the point of this message if there is return value having the same sense?
            return false;
        }
        return true;
    }

    public static void selectTrain (String trainNo){

        getElement(resultTable).findElement(By.xpath("//a[text()='" + trainNo + "']")).click(); // You made click(xpath) method, why not using it?

    }

    public static boolean verifyPresent (String xpath){ // This method is not related to this particular page object.

        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        }catch (TimeoutException e){
            System.out.println("No element found!"); // What the point of this message if there is return value having the same sense?
            return false;
        }
        return true;
    }

    public static void close (String xpath){
        // Why do searching 2 times, you can just join two xpath's into one.
        getElement(xpath).findElement(By.xpath("//a[@title='close']")).click(); // You made click(xpath) method, why not using it?

        int count = 0;
        while (driver.findElements(By.xpath(xpath)).size() > 0) { // Why not using until here? Inconsistent with your own code style.
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                   }
            count++;
            if (count > 50){
                System.out.println("Cannot close!"); // Test won't fail, only message in console will appear.
                break;
            }
        }
    }

    public static void openPlan(String trainNo){

        try {
            WebElement selectButton = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + trainNo + "']/../following-sibling::td[@class='place']/div[starts-with(@title,'Coupe')]/button[text()='Choose']")));
            selectButton.click(); 
        }catch (TimeoutException e){
            System.out.println("Could not click Select button!"); // Test won't fail, only message in console will appear.
        }

    }

    public static void chooseCoach(String coach) {

        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Select your place']")));
        }catch (TimeoutException e){
            System.out.println("Plan did not open!"); // Test won't fail, only message in console will appear.
        }
        getElement("//span[@class='coaches']/a[@href='#" + coach + "']").click(); // You made click(xpath) method, why not using it?
        new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading_img']")));


    }

    public static void choosePlace(String place) {

        try {
            WebElement placeButton = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='places']//span[text()='" + place + "']")));
            if (placeButton.getCssValue("color").equals("rgba(204, 204, 204, 1)")) {
                System.out.println("Place already taken!"); // So what? What the point for test to show this message in console?
            }
            int count = 0;
            while (driver.findElements(By.xpath("//input[@class='lastname']")).size() < 1) { // Why not using until here? Inconsistent with your own code style.
                try { // We always test on isolated environments where we always know the state of the system, if something unexpected happen test should just fail.
                    placeButton.click(); // You made click(xpath) method, why not using it?
                }catch (StaleElementReferenceException e){ // This may happen because you found element at the start of method, and it changes with the time. Should always find element right before performing any action on it.
                    placeButton = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='places']//span[text()='" + place + "']"))); // This is what I mentioned above.
                    placeButton.click(); // You made click(xpath) method, why not using it?
                }
                Thread.sleep(500); // Why using different sleeps? 100/500...?
                System.out.println("Clicking again!");
                count++;
                if (count > 5){
                System.out.println("Last name field did not show up!"); // Test won't fail, only message in console will appear.
                    break;
                    }
                }
            }catch (InterruptedException e) {
        }
    }

}


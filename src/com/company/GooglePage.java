package com.company;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;


/**
 * Created by Admin on 29.04.15.
 */
public class GooglePage {

    public static WebDriver driver;
    public final static int WAIT_COUNTER = 20000;
    public static String inputField = "//textarea[@id = 'source']";
    public static String resultField = "//span[@id = 'result_box']";
    public static String result = "//span[@id = 'result_box']/span";
    public static String translateButton = "//input[@id='gt-submit']";
    public static String inputLanguageArrow = "//div[@id='gt-sl-gms']";
    public static String outputLanguageArrow = "//div[@id='gt-tl-gms']";
    public static String loudspeakerButton = "//div[@id = 'gt-res-listen']";
    public static String clearButton = "//div[@id = 'gt-clear']";

    public static void open() {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        driver = new ChromeDriver(options);
        driver.get("https://translate.google.com");
    }

    public static void clean(){

        driver.quit();

    }

    public static WebElement getElement (String xpath){

        return driver.findElement(By.xpath(xpath));

    }

    public static void setField (String xpath, String value){

        getElement(xpath).sendKeys(value);
    }

    public static void clearInput (){

        getElement(clearButton).click();
    }

   public static boolean verifyIsEmpty(String xpath) {

       if (getElement(xpath).getTagName().equals("span")){
           if (getElement(xpath).getText().equals("")) return true;
                return false;}
           else if (getElement(xpath).getAttribute("value").equals("")) return true;
       return false;

    }

    public static boolean verifyIsNotEmpty(String xpath) {

        if (getElement(xpath).getTagName().equals("span")){
            if (!getElement(xpath).getText().equals("")) return true;
            return false;}
        else if (!getElement(xpath).getAttribute("value").equals("")) return true;
            return false;
    }

    public static String getTitle() {

        String s = driver.getTitle();
        return s;
    }

    public static boolean verifyPresent(String xpath) throws Exception {

        int counter = 0;
        while (driver.findElements(By.xpath(xpath)).size() == 0) {
            Thread.sleep(100);
            counter += 100;
            if (counter > WAIT_COUNTER) {
                return false;
            }
        }
        return true;
    }

   public static boolean verifyNotEditable(String xpath) {

        WebElement element = getElement(xpath);
        String test = "test";

        try {
            element.sendKeys(test);
        } catch (WebDriverException e) {
            System.out.println("Cannot focus element");
            return true;
        }
        if (element.getAttribute("value").equals(test)) return false;
        return true;
    }

    public static void setInputLanguage (String language){

        getElement(inputLanguageArrow).click();
        WebElement lang = driver.findElement(By.xpath("//div[@id = 'gt-sl-gms-menu']//*[text()='" + language + "']"));
        lang.click();

    }

    public static void setOutputLanguage(String language){

        getElement(outputLanguageArrow).click();
        WebElement lang = driver.findElement(By.xpath("//div[@id = 'gt-tl-gms-menu']//*[text()='" + language + "']"));
        lang.click();
    }

    public static List<String> getLaunguages() {

        getElement(inputLanguageArrow).click();
        List<String> languages = new ArrayList<String>();

        List<WebElement> items = driver.findElements(By.xpath("//div[@class='goog-menuitem-content']"));

        for (WebElement item : items) {
            languages.add(item.getText());
        }

        Iterator<String> itr = languages.iterator();

        while (itr.hasNext()) {
            String lang = itr.next();
            if (lang.isEmpty()) itr.remove();
        }
        return languages;
    }

    public static boolean verifyLanguagePresent (List<String> langList, String[] langs){

        List<String> list = Arrays.asList(langs);

        return langList.containsAll(list);
    }

    public static String getTranslation (String inputLanguage, String outputLanguage, String phrase)throws Exception{

        setInputLanguage(inputLanguage);
        setOutputLanguage(outputLanguage);
        setField(inputField, phrase);
        verifyPresent(result);

        List<String> results = new ArrayList<String>();
        List<WebElement> items = driver.findElements(By.xpath(result));

        for (WebElement item : items) {
            results.add(item.getText());
        }

        String s = "";

        if (outputLanguage == "Chinese (Simplified)") {

            for (String word : results) s += word;
            return s;
        } else {
            for (int i = 0;i < results.size(); i++ ){
                if (i+1 == results.size() || results.get(i+1).equals("!")) {
                    s += results.get(i);
                } else s += results.get(i) + " ";
            }

            return s;
        }
    }
}

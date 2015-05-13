package com.company;

import com.google.common.primitives.Chars;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.charset.Charset;
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
    public static String upperLeftLanguageButtons = "//div[@id = 'gt-sl-sugg']/div[@role = 'button']";
    public static String upperRightLanguageButtons = "//div[@id = 'gt-tl-sugg']/div[@role = 'button']";
    public static String selectedInputLanguage = "//div[@id = 'gt-sl-sugg']/div[@role = 'button'][@aria-pressed = 'true']";
    public static String selectedOutputLanguage = "//div[@id = 'gt-tl-sugg']/div[@role = 'button'][@aria-pressed = 'true']";

    public static void open() {

        //System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        driver = new ChromeDriver(options);
        driver.get("https://translate.google.com");
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

    public static void clearInput (){

        getElement(clearButton).click();
    }

    public static void setField (String xpath, String value){

        getElement(xpath).sendKeys(value);
    }

    public static String getField (String xpath){

        if (getElement(xpath).getTagName().equals("span")){
            String tmp = getElement(xpath).getText();
            return new String (tmp.getBytes(Charset.forName("utf-8")));
        }
        else return getElement(xpath).getAttribute("value");
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

    public static String getInputLanguageSelected (){

        return getElement(selectedInputLanguage).getText();

    }

    public static String getOutputLanguageSelected (){

        return getElement(selectedOutputLanguage).getText();

    }

    public static boolean verifyUpperLeftButtons (String[] languages){

        List<String> list = new ArrayList<String>();

        String lang1 = getElement(upperLeftLanguageButtons + "[1]").getText();
        String lang2 = getElement(upperLeftLanguageButtons + "[2]").getText();
        String lang3 = getElement(upperLeftLanguageButtons + "[3]").getText();

        list.add(lang1);
        list.add(lang2);
        list.add(lang3);

        if (list.containsAll(Arrays.asList(languages))) return true;
        return false;
    }

    public static boolean verifyUpperRightButtons (String[] languages){

        List<String> list = new ArrayList<String>();

        String lang1 = getElement(upperRightLanguageButtons + "[1]").getText();
        String lang2 = getElement(upperRightLanguageButtons + "[2]").getText();
        String lang3 = getElement(upperRightLanguageButtons + "[3]").getText();

        list.add(lang1);
        list.add(lang2);
        list.add(lang3);

        if (list.containsAll(Arrays.asList(languages))) return true;
        return false;
    }

    public static void translate() {
        getElement(translateButton).click();
    }

}

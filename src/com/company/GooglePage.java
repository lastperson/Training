package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


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


    public static void open(WebDriver driver) {

        GooglePage.driver = driver;
        driver.get("https://translate.google.com");
    }

    public static WebElement findElement(String xpath) {

        WebElement result = driver.findElement(By.xpath(xpath));
        return result;
    }

    public static boolean verifyIsEmpty(String xpath) {

        if (driver.findElement(By.xpath(xpath)).getAttribute("value").equals("")) return true;
        else return false;
    }

    public static boolean verifyIsNotEmpty(String xpath) {

        if (!driver.findElement(By.xpath(xpath)).getAttribute("value").equals("")) return true;
        else return false;
    }

    public static boolean verifySpanIsEmpty(String xpath) {

        if (driver.findElement(By.xpath(xpath)).getText().equals("")) return true;
        else return false;
    }

    public static boolean verifySpanIsNotEmpty(String xpath) {

        if (!driver.findElement(By.xpath(xpath)).getText().equals("")) return true;
        else return false;
    }

    public static String getTitle(WebDriver driver) {

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

    public static boolean verifyEditable(String xpath) {

        if (findElement(xpath).isEnabled() && !findElement(xpath).getTagName().equals("span")) return true;
        else return false;
    }

    public static boolean verifyNotEditable(String xpath) {

        String test = "test";
        WebElement inputField = driver.findElement(By.xpath(xpath));
        try {
            inputField.sendKeys(test);
        } catch (WebDriverException e) {
            System.out.println("Cannot focus element");
            return true;
        }
        if (inputField.getAttribute("value").equals(test)) return false;
        return true;
    }

    public static void setInputLanguage (String language){

        findElement(inputLanguageArrow).click();
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='goog-menuitem-content']"));

        for (WebElement item : items){

            if (item.getText().equals(language)) item.click();
        }
    }

    public static void setOtputLanguage (String language){

        findElement(outputLanguageArrow).click();
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='goog-menuitem-content']"));

        for (WebElement item : items){

            if (item.getText().equals(language)) item.click();
        }
    }

    public static List<String> getLaunguages() {

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

        if (langList.containsAll(list))return true;
        return false;
    }

    public static String getValue (String xpath){

        String s = "";
        WebElement element = driver.findElement(By.xpath(xpath));
        s = element.getAttribute("value");
        return s;
    }

    public static String getSpan (String xpath){

        List<String> result = new ArrayList<String>();
        List<WebElement> items = driver.findElements(By.xpath(xpath));

        for (WebElement item : items) {
            result.add(item.getText());
        }

        String s = "";

        for (int i = 0;i < result.size(); i++ ){
            if (i+1 == result.size() || result.get(i+1).equals("!")) {
                s += result.get(i);
                } else s += result.get(i) + " ";
        }

    return s;
    }

    public static String getSpanNoSpaces (String xpath){

        List<String> result = new ArrayList<String>();
        List<WebElement> items = driver.findElements(By.xpath(xpath));

        for (WebElement item : items) {
            result.add(item.getText());
        }

        String s = "";

        for (String word : result) s+= word;
        return s;
    }
}

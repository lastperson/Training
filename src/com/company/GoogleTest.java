package com.company;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static com.company.GooglePage.*;
import static com.company.GooglePage.findElement;


/**
 * Created by Admin on 29.04.15.
 */
@RunWith(JUnit4.class)

public class GoogleTest {

    public WebDriver driver;


@Before

    public void initialize(){

    System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--lang=en");
    driver = new ChromeDriver(options);
    //driver = new FirefoxDriver();
    open(driver);
}

@After

    public void clean(){

    driver.quit();

}

    @Test

    public void Test1(){

        Assert.assertTrue(verifyIsEmpty(inputField));

    }

    @Test

    public void Test2(){

        Assert.assertEquals("Google Translate", getTitle(driver));

    }

    @Test

    public void Test3()throws Exception{

        Assert.assertTrue(verifyPresent(inputField));
        Assert.assertTrue(verifyPresent(resultField));
        Assert.assertTrue(verifyPresent(translateButton));

    }

    @Test

    public void Test4(){

    Assert.assertTrue(verifyNotEditable(resultField));

    }

    @Test

    public void Test5(){

        findElement(inputLanguageArrow).click();
        List<String> languages = getLaunguages();
        Assert.assertTrue(verifyLanguagePresent(languages, new String[]{"Greek", "Maltese", "Slovenian"}));
    }

    @Test

    public void Test6()throws Exception{

        findElement(inputField).sendKeys("Hello");
        Assert.assertTrue(verifyPresent(loudspeakerButton));

    }

    @Test

    public void Test7()throws Exception{

        findElement(inputField).sendKeys("Hello");
        Assert.assertTrue(verifyPresent(result));
        Assert.assertTrue(verifySpanIsNotEmpty(result));

    }

    @Test

    public void Test8()throws Exception{

        setInputLanguage("English");
        setOtputLanguage("Spanish");
        findElement(inputField).sendKeys("Hello");
        Assert.assertTrue(verifyPresent(result));
        Assert.assertEquals("hola", getSpan(result));

    }

    @Test

    public void Test9()throws Exception{

        findElement(inputField).sendKeys("Hello");
        Assert.assertTrue(verifyPresent(result));
        findElement(clearButton).click();
        Assert.assertTrue(verifySpanIsEmpty(resultField));

    }

    @Test

    public void Test10()throws Exception{

        setInputLanguage("Ukrainian");
        setOtputLanguage("Chinese (Simplified)");
        findElement(inputField).sendKeys("Слава Україні! Героям Слава!");
        Assert.assertTrue(verifyPresent(result));
        Assert.assertEquals("光荣属于乌克兰！光荣属于英雄！", getSpanNoSpaces(result));

        findElement(inputField).clear();
        findElement(inputField).sendKeys("Слава Україні! Героям Слава!");
        setOtputLanguage("Spanish");
        Assert.assertTrue(verifyPresent(result));
        Assert.assertEquals("Gloria a Ucrania! ¡Gloria a los héroes!", getSpan(result));

    }






}

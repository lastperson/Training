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
import static com.company.GooglePage.*;


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

        Assert.assertTrue(isEmpty(inputField));

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

    Assert.assertTrue(verifyEditable(resultField)); //IMPLEMENT VERIFY NOT EDITABLE!!

    }

    @Test

    public void Test5(){

        findElement(languageArrow).click();


    }

    @Test

    public void Test6(){

    }

    @Test

    public void Test7(){

    }

    @Test

    public void Test8(){

    }

    @Test

    public void Test9(){

    }

    @Test

    public void Test10(){

    }






}

package com.company;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static com.company.GooglePage.*;



/**
 * Created by Admin on 29.04.15.
 */
@RunWith(JUnit4.class)

public class GoogleTest {

@Before

    public void initialize(){

    open();

}

@After

   public void cleanUp () {

        clean();
    }

    @Test

    public void Test1(){

        Assert.assertTrue(verifyIsEmpty(inputField));

    }

    @Test

    public void Test2(){

        Assert.assertEquals("Google Translate", getTitle());

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

        Assert.assertTrue(verifyLanguagePresent(getLaunguages(), new String[]{"Greek", "Maltese", "Slovenian"}));
    }

    @Test

    public void Test6()throws Exception{

        setField(inputField, "Hello");
        Assert.assertTrue(verifyPresent(loudspeakerButton));

    }

    @Test

    public void Test7()throws Exception{

        setField(inputField, "Hello");
        Assert.assertTrue(verifyPresent(result));
        Assert.assertTrue(verifyIsNotEmpty(result));

    }

    @Test

    public void Test8()throws Exception{

        setInputLanguage("Ukrainian");
        setOutputLanguage("Spanish");
        setField(inputField, "Слава Україні! Героям Слава!");
        verifyPresent(result);
        Assert.assertEquals("Gloria a Ucrania! ¡Gloria a los héroes!", getField(resultField));

    }

    @Test

    public void Test9()throws Exception{

        setField(inputField, "Hello");
        Assert.assertTrue(verifyPresent(result));
        clearInput();
        Assert.assertTrue(verifyIsEmpty(resultField));

    }

    @Test

    public void Test10()throws Exception{

        setInputLanguage("Ukrainian");
        setOutputLanguage("Indonesian");
        setField(inputField, "Слава Україні! Героям Слава!");
        verifyPresent(result);
        Assert.assertEquals("Glory ke Ukraina! Kemenangan bagi Pahlawan!", getField(resultField));

        setOutputLanguage("Spanish");
        verifyPresent(result);
        Assert.assertEquals("Gloria a Ucrania! ¡Gloria a los héroes!", getField(resultField));

        }

    @Test

    public void Test11(){

        openUrl("https://translate.google.com/#uk/jw/Hello");
        Assert.assertEquals("Ukrainian", getInputLanguageSelected());
        Assert.assertEquals("Javanese", getOutputLanguageSelected());
        Assert.assertEquals("Hello", getField(inputField));
        Assert.assertEquals("Hello", getField(resultField));
    }

    @Test

    public void Test12(){

    Assert.assertTrue(verifyUpperLeftButtons(new String[]{"English","Spanish","French"}));
    Assert.assertTrue(verifyUpperRightButtons(new String[]{"English","Spanish","Arabic"}));

    }
}

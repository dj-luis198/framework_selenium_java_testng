package com.demoqa.tests.buttons;

import com.demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.sleep;


public class ButtonTest extends BaseTest {

    @Test
    public void homeTest() {
        getDriver().get("https://demoqa.com/");
        String title = getDriver().getTitle();
        System.out.println(title);
//        try {
//            sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        Assert.assertTrue(true);
    }

    @Test
    public void homeTest2() {
        getDriver().get("https://demoqa.com/");
        String url = getDriver().getCurrentUrl();
        System.out.println(url);
//        try {
//            sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        Assert.assertTrue(true);
    }
}

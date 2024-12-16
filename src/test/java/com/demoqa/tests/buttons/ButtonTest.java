package com.demoqa.tests.buttons;

import com.demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class ButtonTest extends BaseTest {

    @Test
    public void buttonsTest() {
        getDriver().get("https://demoqa.com/text-box");
        String title = getDriver().getTitle();
        System.out.println(title);
        Assert.assertTrue(true);
    }

    @Test
    public void buttonsTest2() {
        getDriver().get("https://demoqa.com/checkbox");
        String url = getDriver().getCurrentUrl();
        System.out.println(url);
        Assert.assertTrue(true);
    }
}

package com.demoqa.tests.home;

import com.demoqa.base.Base;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomeTest extends Base {

    @Test
    public void homeTest() {
        getDriver().get("https://demoqa.com/");
        String title = getDriver().getTitle();
        System.out.println(title);
        Assert.assertTrue(true);
    }

    @Test
    public void homeTest2() {
        getDriver().get("https://demoqa.com/radio-button");
        String url = getDriver().getCurrentUrl();
        System.out.println(url);
        Assert.fail();
    }
}

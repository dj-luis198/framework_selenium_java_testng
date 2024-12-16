package com.demoqa.tests.widgets;

import com.demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccordianTest extends BaseTest {
    @Test
    public void accordianTest() {
        getDriver().get("https://demoqa.com/accordian");
        String title = getDriver().getTitle();
        System.out.println(title);
        Assert.assertTrue(true);
    }

    @Test
    public void accordianTest2() {
        getDriver().get("https://demoqa.com/auto-complete");
        String url = getDriver().getCurrentUrl();
        System.out.println(url);
        Assert.fail();
    }
}

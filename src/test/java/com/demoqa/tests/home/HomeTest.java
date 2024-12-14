package com.demoqa.tests.home;

import com.demoqa.base.BaseTest;
import org.testng.annotations.*;

public class HomeTest extends BaseTest {

    @Test
    public void homeTest() {
        driver.get("https://demoqa.com/");
        String title = driver.getTitle();
        System.out.println(title);
    }

    @Test
    public void homeTest2() {
        driver.get("https://demoqa.com/");
        String url = driver.getCurrentUrl();
        System.out.println(url);
    }
}

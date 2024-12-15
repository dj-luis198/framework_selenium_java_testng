package com.demoqa.tests.home;

import com.demoqa.base.BaseTest;
import org.testng.annotations.*;

import static java.lang.Thread.sleep;

public class HomeTest extends BaseTest {

    @Test
    public void homeTest() {
        driver.get("https://demoqa.com/");
        String title = driver.getTitle();
        System.out.println(title);
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void homeTest2() {
        driver.get("https://demoqa.com/");
        String url = driver.getCurrentUrl();
        System.out.println(url);
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

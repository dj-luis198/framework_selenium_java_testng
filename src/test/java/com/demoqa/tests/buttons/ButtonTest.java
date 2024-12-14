package com.demoqa.tests.buttons;

import com.demoqa.base.BaseTest;
import org.testng.annotations.*;

import static java.lang.Thread.sleep;


public class ButtonTest extends BaseTest {

    @Test
    public void homeTest() {
        driver.get("https://demoqa.com/");
        String title = driver.getTitle();
        System.out.println(title);
        try {
            sleep(20000);
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
            sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

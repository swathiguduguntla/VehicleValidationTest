package com.car.qa.stepDefs;

import com.car.qa.base.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class TestHooks extends TestBase {

    @Before
    public void setUp() throws InterruptedException {
        initialization();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}

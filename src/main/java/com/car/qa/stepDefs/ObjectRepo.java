package com.car.qa.stepDefs;

import com.car.qa.pages.CarTaxCheckHomePage;
import com.car.qa.pages.VehicleSummary;
import com.car.qa.util.TestUtil;

public class ObjectRepo {

    CarTaxCheckHomePage carTaxCheckHomePage;
    TestUtil testUtil;
    VehicleSummary vehicleSummary;

    protected synchronized CarTaxCheckHomePage carTaxCheckHomePage() {
        if (carTaxCheckHomePage == null) {
            carTaxCheckHomePage = new CarTaxCheckHomePage();
        }
        return carTaxCheckHomePage;
    }

    protected synchronized TestUtil testUtil() {
        if (testUtil == null) {
            testUtil = new TestUtil();
        }
        return testUtil;
    }

    protected synchronized VehicleSummary vehicleSummary() {
        if (vehicleSummary == null) {
            vehicleSummary = new VehicleSummary();
        }
        return vehicleSummary;
    }
}

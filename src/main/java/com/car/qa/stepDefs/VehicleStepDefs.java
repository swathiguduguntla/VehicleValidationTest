package com.car.qa.stepDefs;

import com.car.qa.pages.CarTaxCheckHomePage;
import com.car.qa.pages.VehicleSummary;
import com.car.qa.util.TestUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class VehicleStepDefs extends ObjectRepo {

    ArrayList<String> inputData;
    ArrayList<String> regNumbers = new ArrayList<String>();
    List<HashMap<String, String>> appVehicleDetails = new ArrayList<HashMap<String, String>>();

    @Given("user reads the input file name {string} from the project path")
    public void userReadsTheInputFileNameFromTheProjectPath(String inputFileName) {
        testUtil = new TestUtil();
        vehicleSummary = new VehicleSummary();
        carTaxCheckHomePage = new CarTaxCheckHomePage();
        Assert.assertTrue(carTaxCheckHomePage.verifyContactsLabel(), "Landing page for Car Tax Check is displayed");
        inputData = TestUtil.readInputFile(inputFileName);
        System.out.println("CAR Registrations from input file:" + inputData);
    }

    @When("vehicle registration numbers are extracted from the above file")
    public void vehicleRegistrationNumbersAreExtractedFromTheAboveFile() {
        for (String carRegistration : inputData) {
            vehicleSummary().clickHomePage();
            carTaxCheckHomePage().setCarRegistrationNumber(carRegistration);
            carTaxCheckHomePage().clickFreeCarCheck();
            Assert.assertTrue(vehicleSummary.isVehicleSummarydisplayed(), "Vehicle Summary Page displayed");
            appVehicleDetails.add(vehicleSummary.readVehilcleDetails());
            String carRegistrationNumber = vehicleSummary.readVehilcleDetails().get("REGISTRATION");
            regNumbers.add(carRegistrationNumber);
        }
    }

    @Then("compare the results with the given output file name {string}")
    public void compareTheResultsWithTheGivenOutputFileName(String outPutFileName) {

        try {
            SoftAssert softAssert = new SoftAssert();
            LinkedHashMap<String, HashMap<String, String>> outputVehicleDetails = TestUtil.readOutputFile(outPutFileName);

            for (String carRegistrationNumber : regNumbers) {
                if (outputVehicleDetails.containsKey(carRegistrationNumber)) {
                    HashMap<String, String> outputCarDetails = outputVehicleDetails.get(carRegistrationNumber);
                    for (String key : outputCarDetails.keySet()) {
                        if (outputCarDetails.get(key).trim().equalsIgnoreCase(appVehicleDetails.get(regNumbers.indexOf(carRegistrationNumber)).get(key).trim())) {
                            softAssert.assertEquals(true, "Vehicle Registraion:" + carRegistrationNumber + " Expected value for " + key + " is " + outputCarDetails.get(key) + " actual is:" + appVehicleDetails.get(regNumbers.indexOf(carRegistrationNumber)).get(key));
                        } else {
                            softAssert.assertEquals(false, "Vehicle Registraion:" + carRegistrationNumber + " Expected value for " + key + " is " + outputCarDetails.get(key) + " but actual is:" + appVehicleDetails.get(regNumbers.indexOf(carRegistrationNumber)).get(key));
                        }
                    }
                } else {
                    softAssert.assertEquals(false, "Expected Car Registration number in outfile not available: " + inputData.get(regNumbers.indexOf(carRegistrationNumber)));
                }
            }
            softAssert.assertAll();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}


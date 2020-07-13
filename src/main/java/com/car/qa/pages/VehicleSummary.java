package com.car.qa.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.car.qa.base.TestBase;

public class VehicleSummary extends TestBase {

	@FindBy(css = "img[aria-label='Car Tax Check']")
	WebElement homepageimg;
	
	@FindAll(@FindBy(how = How.CSS, using = "div[class*=d-w-3] dl"))
	List<WebElement> carSummary;

	@FindBy(xpath = "//h4[contains(text(),'Tax & MOT')]")
	WebElement taxAndMotHeader;

	// Initialising the Page Objects:
	public VehicleSummary() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickHomePage(){
	 homepageimg.click();
	}
	
	public boolean isVehicleSummarydisplayed(){
		return taxAndMotHeader.isDisplayed();
	}

	public HashMap<String,String> readVehilcleDetails(){
		HashMap<String,String> vehicleDetails = new HashMap<String,String>();
		for (int i=0; i<5;i++){ //if you need to read all vehicle details then condition should be i<carSummary.size()
			vehicleDetails.put(carSummary.get(i).findElement(By.cssSelector("dt")).getText().toUpperCase().trim(),carSummary.get(i).findElement(By.cssSelector("dd")).getText());
		}
		
		return vehicleDetails;
	}

}

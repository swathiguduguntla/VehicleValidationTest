package com.car.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.car.qa.base.TestBase;

public class CarTaxCheckHomePage extends TestBase {

	@FindBy(css = "div[class*=landing] h1")
	WebElement carHistoryCheckTitle;
	
	@FindBy(id="vrm-input")
	WebElement carRegistrationEditBox;
	
	@FindBy(css="form button")
	WebElement freeCarCheck;
	
	@FindBy(css="img[aria-label='Car Tax Check']")
	WebElement homepageIcon;
	
	// Initialising the Page Objects:
	public CarTaxCheckHomePage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyContactsLabel(){
		return carHistoryCheckTitle.isDisplayed();
	}
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	public void setCarRegistrationNumber(String carRegistrationNumber){
		carRegistrationEditBox.sendKeys(carRegistrationNumber);
	}

	public void clickFreeCarCheck(){
		freeCarCheck.click();
	}
}

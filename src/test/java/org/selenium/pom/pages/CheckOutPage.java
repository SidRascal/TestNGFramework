package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.testng.Assert;

public class CheckOutPage extends BasePage {

	private final By firstNameField = By.id("billing_first_name");
	private final By lastNameField = By.id("billing_last_name");
	private final By addressLineOneField = By.id("billing_address_1");
	private final By billingCityField = By.id("billing_city");
	private final By billingPostCodeField = By.id("billing_postcode");
	private final By billingEmailField = By.id("billing_email");
	private final By placeOrderButton = By.id("place_order");
	private final By successNotice = By.cssSelector(".woocommerce-notice");

	public CheckOutPage(WebDriver driver) {
		super(driver);
	}

	public CheckOutPage enterFirstName(String firstName) {
		driver.findElement(firstNameField).sendKeys(firstName);
		return this;
	}

	public CheckOutPage enterLastName(String lastName) {
		driver.findElement(lastNameField).sendKeys(lastName);
		return this;
	}

	public CheckOutPage enterAddressLineOne(String addressLineOne) {
		driver.findElement(addressLineOneField).sendKeys(addressLineOne);
		return this;
	}

	public CheckOutPage enterBillingCity(String billingCity) {
		driver.findElement(billingCityField).sendKeys(billingCity);
		return this;
	}

	public CheckOutPage enterPostalCode(String postalCode) {
		driver.findElement(billingPostCodeField).sendKeys(postalCode);
		return this;
	}

	public CheckOutPage enterEmail(String emailAddress) {
		driver.findElement(billingEmailField).sendKeys(emailAddress);
		return this;
	}
	public CheckOutPage placeOrder()
	{
		driver.findElement(placeOrderButton).click();
		return this;
	}
	public String getNotice()
	{
		return driver.findElement(successNotice).getText();
	}

}

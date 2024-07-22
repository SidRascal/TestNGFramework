package org.selenium.pom.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
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
	private final By clickHereToLogin = By.className("showlogin");
	private final By userName = By.id("username");
	private final By passWord = By.id("password");
	private final By countryDropDown = By.id("billing_country");
	private final By stateDropDown = By.xpath("//select[@id='billing_state']");
	private final By stateDropDown2 = By.className("select2-selection.select2-selection--single");

	private final By overlay = By.cssSelector(".blockUI.blockOverlay");

	public CheckOutPage(WebDriver driver) {
		super(driver);
	}

	public CheckOutPage enterFirstName(String firstName) {
		WebElement e = waitForElementToBeVisible(firstNameField);
		e.clear();
		e.sendKeys(firstName);
		return this;
	}

	public CheckOutPage enterLastName(String lastName) {
		WebElement e = waitForElementToBeVisible(lastNameField);
		e.clear();
		e.sendKeys(lastName);
		return this;
	}
	
	public CheckOutPage selectCountry(String countryName)
	{
		Select select = new Select(driver.findElement(countryDropDown));
		select.selectByVisibleText(countryName);
		return this;
	}

	public CheckOutPage enterAddressLineOne(String addressLineOne) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneField));
		e.clear();
		e.sendKeys(addressLineOne);
		return this;
	}

	public CheckOutPage enterBillingCity(String billingCity) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityField));
		e.clear();
		e.sendKeys(billingCity);
		return this;
		
	}
	
	public CheckOutPage selectState(String stateName) throws InterruptedException
	{
		Select select = new Select(driver.findElement(stateDropDown));
		select.selectByValue(stateName);
		return this;
	}

	public CheckOutPage enterPostalCode(String postalCode) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingPostCodeField));
		e.clear();
		e.sendKeys(postalCode);
		return this;
	}

	public CheckOutPage enterEmail(String emailAddress) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailField));
		e.clear();
		e.sendKeys(emailAddress);
		return this;
	}

	public CheckOutPage placeOrder() {
		waitForOverlayToDisappear(overlay);
		driver.findElement(placeOrderButton).click();
		return this;
	}

	public String getNotice() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
	}

	public CheckOutPage clickHereToLoginLink() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(clickHereToLogin)).click();
		return this;
	}

	public CheckOutPage login(String usrNm, String pwd) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(userName)).sendKeys(usrNm);
		wait.until(ExpectedConditions.visibilityOfElementLocated(passWord)).sendKeys(pwd);

		return this;
	}

	public CheckOutPage setBillingAddress(BillingAddress billingAddress) throws InterruptedException {
		return enterFirstName(billingAddress.getFirstName())
				.enterLastName(billingAddress.getLastName())
				.selectCountry(billingAddress.getCountry())
				.enterAddressLineOne(billingAddress.getAddressLineOne())
				.enterBillingCity(billingAddress.getCity())
				.selectState(billingAddress.getState())
				.enterPostalCode(billingAddress.getPostalCode())
				.enterEmail(billingAddress.getEmail());

	}

}

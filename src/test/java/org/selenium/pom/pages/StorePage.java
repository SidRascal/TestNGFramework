package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {

	private final By searchField = By.id("woocommerce-product-search-field-0");
	private final By searchButton = By.cssSelector("button[value='Search']");
	private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
	private final By viewCartLink = By.cssSelector("a[title='View cart']");

	public StorePage(WebDriver driver) {
		super(driver);

	}

	public StorePage enterTextInSearchField(String txt) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(txt);

		return this;
	}

	public StorePage clickSearchBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
		return this;
	}

	public String getTitle() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();

	}

	public StorePage search(String txt) {
		// driver.findElement(searchField).sendKeys(txt);
		enterTextInSearchField(txt);
		// driver.findElement(searchButton).click();
		clickSearchBtn();
		return this;

	}

	private By getAddToCartBtnElement(String productName) {
		return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");

	}

	public StorePage clickAddToCartBtn(String productName) {
		By addToCartBtn = getAddToCartBtnElement(productName);
		driver.findElement(addToCartBtn).click();

		return this;
	}

	public CartPage clickViewCart() {
		wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
		return new CartPage(driver);
	}
}

package org.selenium;

import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {

	@Test
	public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
		driver.get("https://askomdch.com");

		HomePage homePage = new HomePage(driver);
		StorePage storePage = homePage.navigateToStoreUsingMenu();

//		storePage.enterTextInSearchField("Blue").clickSearchBtn(); //Structural Page Object where it provides structure of individual methods
		storePage.search("Blue"); // Functional Page Object where it provide the methods which represents a
									// functionality and not individual methods

		Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
		storePage.clickAddToCartBtn("Blue Shoes");

		Thread.sleep(5000);

		CartPage cartPage = storePage.clickViewCart();

		Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");
		CheckOutPage checkOutPage = cartPage.checkout();

		checkOutPage.enterFirstName("Sam").enterLastName("Curran").enterAddressLineOne("England")
				.enterBillingCity("London").enterPostalCode("590890").enterEmail("sam.curran@gmail.com").placeOrder();
		Thread.sleep(3000);
		Assert.assertEquals(checkOutPage.getNotice(), "Thank you. Your order has been received.");

	}

	@Test
	public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {

		driver.get("https://askomdch.com");

		driver.findElement(By.cssSelector("#menu-item-1227 > a")).click();
		driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
		driver.findElement(By.cssSelector("button[value='Search']")).click();
		Assert.assertEquals(
				driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText(),
				"Search results: “Blue”");
		driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("a[title='View cart']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("td[class='product-name'] a")).getText(), "Blue Shoes");
		driver.findElement(By.cssSelector(".checkout-button")).click();

		driver.findElement(By.className("showlogin")).click();
		driver.findElement(By.id("username")).sendKeys("demouser22");
		driver.findElement(By.id("password")).sendKeys("demouser22");
		driver.findElement(By.name("login")).click();

		driver.findElement(By.id("billing_first_name")).sendKeys("demo");
		driver.findElement(By.id("billing_last_name")).sendKeys("user");
		driver.findElement(By.id("billing_address_1")).sendKeys("San Francisco");
		driver.findElement(By.id("billing_city")).sendKeys("San Francisco");
		driver.findElement(By.id("billing_postcode")).clear();
		driver.findElement(By.id("billing_postcode")).sendKeys("94188");
		driver.findElement(By.id("billing_email")).clear();
		driver.findElement(By.id("billing_email")).sendKeys("sid.lot@gmail.com");
		driver.findElement(By.id("place_order")).click();
		Thread.sleep(5000);
		Assert.assertEquals(driver.findElement(By.cssSelector(".woocommerce-notice")).getText(),
				"Thank you. Your order has been received.");

	}
}

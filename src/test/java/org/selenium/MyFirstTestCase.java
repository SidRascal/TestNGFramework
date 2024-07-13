package org.selenium;

import java.io.IOException;
import java.io.InputStream;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {

	@Test
	public void guestCheckoutUsingDirectBankTransfer() throws IOException {

		// This is by using PoJo class
		/*
		 * BillingAddress billingAddress = new
		 * BillingAddress().setFirstName("Sam").setLastName("Curran")
		 * .setAddressLineOne("England").setCity("London").setPostalCode("94188").
		 * setEmail("sam.curran@gmail.com");
		 */

		// This is by using Parameterized Constructor
		/*
		 * BillingAddress billingAddress = new BillingAddress("Sam", "Curran",
		 * "England", "London", "94188", "sam.curran@gmail.com");
		 */
		BillingAddress billingAddress = new BillingAddress();
		InputStream is = getClass().getClassLoader().getResourceAsStream("myBillingAddress.json");
		billingAddress = JacksonUtils.deserializeJson(is, billingAddress);

		StorePage storePage = new HomePage(driver).load().navigateToStoreUsingMenu().search("Blue");

		Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
		storePage.clickAddToCartBtn("Blue Shoes");

		CartPage cartPage = storePage.clickViewCart();
		Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

		CheckOutPage checkOutPage = cartPage.checkout().setBillingAddress(billingAddress).placeOrder();

		Assert.assertEquals(checkOutPage.getNotice(), "Thank you. Your order has been received.");

	}

	@Test
	public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {

		driver.get("https://askomdch.com");

		HomePage homePage = new HomePage(driver);
		StorePage storePage = homePage.navigateToStoreUsingMenu();
		storePage.enterTextInSearchField("Blue").clickSearchBtn();
		Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");

		storePage.clickAddToCartBtn("Blue Shoes");

		CartPage cartPage = storePage.clickViewCart();
		Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

		CheckOutPage checkoutPage = cartPage.checkout();
		checkoutPage.clickHereToLoginLink();

		checkoutPage.login("demouser22", "demouser22").enterFirstName("Sam").enterLastName("Curran")
				.enterAddressLineOne("England").enterBillingCity("London").enterPostalCode("59089")
				.enterEmail("sam.curran@gmail.com").placeOrder();

		Thread.sleep(3000);
		Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

	}
}

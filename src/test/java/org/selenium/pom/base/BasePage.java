package org.selenium.pom.base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void load(String endPoint) {
		driver.get("https://askomdch.com" + endPoint);
	}

	public void waitForOverlayToDisappear(By overlay) {
		List<WebElement> overlays = driver.findElements(overlay);
		System.out.println("Overlays Size: " + overlays.size());
		if (overlays.size() > 0) {
			wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
			System.out.println("Overlays are invisible now");
		}

		else {
			System.out.println("Overlays not found");
		}

	}
	
	public WebElement waitForElementToBeVisible(By element)
	{
		return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

}

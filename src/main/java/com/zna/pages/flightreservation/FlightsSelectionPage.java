package com.zna.pages.flightreservation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.zna.pages.AbstractPage;

import java.util.concurrent.ThreadLocalRandom;

public class FlightsSelectionPage extends AbstractPage {

	@FindBy(name = "departure-flight")
	private List<WebElement> depatureFlightsOptions;
	
	@FindBy(name = "arrival-flight")
	private List<WebElement> arrivalFlightsOptions;
	
	@FindBy(id = "confirm-flights")
	private WebElement confirmFlightsButton;
	
	public FlightsSelectionPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsButton));
		return this.confirmFlightsButton.isDisplayed();
	}
	
	public void selectFlights() {
		int random = ThreadLocalRandom.current().nextInt(0, depatureFlightsOptions.size());
		this.depatureFlightsOptions.get(random).click();
		this.depatureFlightsOptions.get(random).click();
	}
	
	public void confirmFlights() {
		this.confirmFlightsButton.click();
	}

}

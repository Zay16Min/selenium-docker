package com.zna.tests.flightreservation;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zna.pages.flightreservation.FlightConfirmationPage;
import com.zna.pages.flightreservation.FlightSearchPage;
import com.zna.pages.flightreservation.FlightsSelectionPage;
import com.zna.pages.flightreservation.RegistrationConfirmationPage;
import com.zna.pages.flightreservation.RegistrationPage;
import com.zna.tests.AbstractTest;
import com.zna.tests.flightreservation.model.FlightReservationTestData;
import com.zna.util.JsonUtil;


class FlightReservationTest extends AbstractTest{
	
	private FlightReservationTestData testData;
	
	@BeforeTest
	@Parameters({"testDataPath"})
	public void setParameter(String testDataPath) {
		this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
	}
	
	@Test
	public void userRegistrationTest() {
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
		
		registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
		registrationPage.enterUserCrendentials(testData.email(), testData.password());
		registrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
		registrationPage.register();
		
	}
	
	@Test(dependsOnMethods = "userRegistrationTest")
	public void registrationConfirmationTest() {
		RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
		Assert.assertTrue(registrationConfirmationPage.isAt());
		Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
		registrationConfirmationPage.goToFlightSearch();
	}
	
	@Test(dependsOnMethods = "registrationConfirmationTest")
	public void flightsSearchTest() {
		FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
		Assert.assertTrue(flightSearchPage.isAt());
		flightSearchPage.selectPassengers(testData.passengersCount());
		flightSearchPage.searchFlights();
	}
	
	@Test(dependsOnMethods = "flightsSearchTest")
	public void flightsSelectionTest() {
		FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
		Assert.assertTrue(flightsSelectionPage.isAt());
		flightsSelectionPage.selectFlights();
		flightsSelectionPage.confirmFlights();
	}
	
	@Test(dependsOnMethods = "flightsSelectionTest")
	public void flightsReservationConfirmationTest() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		Assert.assertTrue(flightConfirmationPage.isAt());
		Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
	}
}

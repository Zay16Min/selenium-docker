package com.zna.tests.vendorportal;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zna.pages.vendorportal.DashBoardPage;
import com.zna.pages.vendorportal.LoginPage;
import com.zna.tests.AbstractTest;
import com.zna.tests.vendorportal.model.VendorPortalTestData;
import com.zna.util.JsonUtil;
public class VendorPortalTest extends AbstractTest {

	private LoginPage loginPage;
	private DashBoardPage dashboardPage;
	private VendorPortalTestData testData;
	
	@BeforeTest
	@Parameters("testDataPath")
	public void setPageObjects(String testDataPath) {
		this.loginPage = new LoginPage(driver);
		this.dashboardPage = new DashBoardPage(driver);
		this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
	}
	
	@Test
	public void loginTest() {
		loginPage = new LoginPage(driver);
		loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
		Assert.assertTrue(loginPage.isAt());
		loginPage.login(testData.username(), testData.password());
	}
	@Test(dependsOnMethods = "loginTest")
	public void dashboardTest() {
		dashboardPage = new DashBoardPage(driver);
		Assert.assertTrue(dashboardPage.isAt());
		
		Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
		
		Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
		Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
		Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());
		
		dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
		Assert.assertEquals(dashboardPage.getSearchResultCount(), testData.searchResultsCount());
		
	}
	
	@Test(dependsOnMethods = "dashboardTest")
	public void logoutTest() {
		dashboardPage.logout();
		Assert.assertTrue(loginPage.isAt());
	}
	
}

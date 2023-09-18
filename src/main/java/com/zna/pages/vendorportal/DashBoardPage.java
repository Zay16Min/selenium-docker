package com.zna.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zna.pages.AbstractPage;

public class DashBoardPage extends AbstractPage {
	
	private static final Logger log = LoggerFactory.getLogger(DashBoardPage.class);
	@FindBy(id = "monthly-earning")
	private WebElement monthlyEarningElement;
	
	@FindBy(id = "annual-earning")
	private WebElement annualEarningElement;
	
	@FindBy(id = "profit-margin")
	private WebElement profitEarningElement;
	
	@FindBy(id = "available-inventory")
	private WebElement availableInventoryElement;
	
	@FindBy(css = "#dataTable_filter input")
	private WebElement searchInput;
	
	@FindBy(id = "dataTable_info")
	private WebElement searchResultsCountElement;
	
	@FindBy(css = "img.img-profile")
	private WebElement userProfilePictureElement;
	
	@FindBy(linkText = "Logout")
	private WebElement logoutLink;
	
	@FindBy(css = "#logoutModal a")
	private WebElement modalLogoutButton;

	public DashBoardPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarningElement));		
		return this.monthlyEarningElement.isDisplayed();
	}
	
	public String getMonthlyEarning() {
		return this.monthlyEarningElement.getText();
	}
	
	public String getAnnualEarning() {
		return this.annualEarningElement.getText();
	}
	
	public String getProfitMargin() {
		return this.profitEarningElement.getText();
	}

	public String getAvailableInventory() {
		return this.availableInventoryElement.getText();
	}
	
	public void searchOrderHistoryBy(String keyword) {
		this.searchInput.sendKeys(keyword);;
	}
	
	public int getSearchResultCount() {
		this.wait.until(ExpectedConditions.visibilityOf(this.searchResultsCountElement));
		String resultsText = this.searchResultsCountElement.getText();
		String[] arr = resultsText.split(" ");
		int count = Integer.parseInt(arr[5]);
		log.info("Results count : {}", count);
		return count;
	}
	public void logout() {
		this.userProfilePictureElement.click();
		this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
		this.logoutLink.click();
		this.wait.until(ExpectedConditions.visibilityOf(this.modalLogoutButton));
		this.modalLogoutButton.click();
	}
	
}

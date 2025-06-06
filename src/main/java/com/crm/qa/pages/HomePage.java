package com.crm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.BaseTest;
import com.crm.qa.util.TestUtil;

public class HomePage extends BaseTest {
	String searchReporBoxXpath = "//input[@id='dashboard-repos-filter-left']";
	final static String dropdownArrow = ".//span[@class='dropdown-caret']//parent::summary[@aria-label='View profile and more']";
	static String viewRepoLinkXpath= ".//a[@role='menuitem'][@class='dropdown-item']";
	final static String repositoryText = "Your repositories";
	String searchBox ="";
	String dropdownValue="//div[contains(text(),'kreetanshu/com.github.test')]";
	public HomePage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='Sign up for GitHub']")
	WebElement exploreGithub;
	
	@FindBy(id="dashboard-repos-filter-left")
	WebElement searchRepoBox;
	
	@FindBy(xpath =dropdownArrow)
	WebElement viewProfileDropdown;
    
	@FindBy(name="q")
	WebElement searchBoxTop;
	
	public boolean validateExploreGithubText() {
		return exploreGithub.isDisplayed();
	}
	
	public void clickProfileDropdown(){
		viewProfileDropdown.click();
	}
	
	public void selectFromDropdown(){
		searchBoxTop.sendKeys("");
		TestUtil.selectFromDropdown(dropdownValue);
	}
	
	public RepositoriesPage clickAndNavigateRepositories() throws IOException{
		clickProfileDropdown();
		TestUtil.selectBootstrapDropDownValue(viewRepoLinkXpath, repositoryText);
		return new RepositoriesPage();
	}
	public String searchRepository(String searchText){
		TestUtil.waitForElementVisiblibility(searchRepoBox,10);
		searchRepoBox.sendKeys(searchText);
		TestUtil.changeBackgroundColorJS("yellow", searchRepoBox);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement searchLink= driver.findElement(By.xpath("//ul[@class='list-style-none filterable-active']//span[@class='css-truncate css-truncate-target'][contains(text(),'"+searchText+"')]"));
		TestUtil.waitForElementVisiblibility(searchLink,10);
		return searchLink.getText(); 
	}
	
}

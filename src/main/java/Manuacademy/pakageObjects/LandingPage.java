package Manuacademy.pakageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Abstract_component;

public class LandingPage extends Abstract_component {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		//Initializing
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement incorrectMessage;
	
	public ProductCatalogue login(String userNameData, String passwordData) {
		
		userName.sendKeys(userNameData);
		password.sendKeys(passwordData);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	public String validateIncorrectPassword() {
		
		wait_till_Webelements_loads(incorrectMessage);
		return incorrectMessage.getText();
	
	}
	
	public void go_to() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}

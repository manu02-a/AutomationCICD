package Manuacademy.pakageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Abstract_component;

public class FinalPage extends Abstract_component {

	WebDriver driver;
	public FinalPage(WebDriver driver) {
		//Initializing
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement conMeassage;
	
	public String getConfermationMessage() {
		String confirmationMessage = conMeassage.getText();
		return confirmationMessage;
	}
}

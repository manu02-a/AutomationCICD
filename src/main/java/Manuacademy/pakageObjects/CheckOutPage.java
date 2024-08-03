package Manuacademy.pakageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Abstract_component;

public class CheckOutPage extends Abstract_component {

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		//Initializing
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".form-group .input.txt.text-validated")
	WebElement countryField;
	
	@FindBy(xpath="(//button/span[@class='ng-star-inserted'])[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By waitForListedCountry = By.cssSelector("button .ng-star-inserted");
	
	public void selectCountry() {
		Actions a = new Actions(driver);
		a.sendKeys(countryField,"india").build().perform();
		wait_till_elements_loads(waitForListedCountry);
		selectCountry.click();
	}
	
	/*public void selectCountry1() {
		
		
		
	}*/
	/*//entering value in country field   //button .ng-star-inserted
	driver.findElement(By.cssSelector(".form-group .input.txt.text-validated")).sendKeys("ind");
	
	//storing all the resultant values in list
	List<WebElement> countries =driver.findElements(By.cssSelector("button .ng-star-inserted"));
	
	//looping
	for(WebElement i:countries) {
		if(i.getText().equalsIgnoreCase("India")) {
			i.click();
			break;
		}
	}*/
	
	public FinalPage placeOrder() {
		submit.click();
		FinalPage finalpage = new FinalPage(driver);
		return finalpage;
	}
	
}

package Manuacademy.pakageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Abstract_component;

public class CartPage extends Abstract_component {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		//Initializing
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	public List<WebElement> cartProducts() {
		return cartproducts;
	}
	
	public boolean check_cartProducts(String productName) {
		boolean match = cartProducts().stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage check_out_button() {
		checkOut.click();
		CheckOutPage checkout = new CheckOutPage(driver);
		return checkout;
	}
}

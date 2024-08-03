package Manuacademy.pakageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Abstract_component;

public class OrderPage extends Abstract_component {
	
	WebDriver driver;

	public OrderPage(WebDriver driver){
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> orderedProducts;
	
	public boolean check_ordered_product(String productName) {
		boolean match = orderedProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}

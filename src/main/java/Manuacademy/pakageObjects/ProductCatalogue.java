package Manuacademy.pakageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Abstract_component;

public class ProductCatalogue extends Abstract_component{

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".card-body")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement scroll;
	
	By products1 = By.cssSelector(".card-body");
	By addToCartButton = By.cssSelector("button:last-of-type");
	By messagePoUp = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList() {
		wait_till_elements_loads(products1);
		return products;
	}
	
	public WebElement filtering_desired_product(String productName) {
		WebElement prod =  getProductList().stream().filter(
				product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addToCart(String productName) {
		WebElement prod =filtering_desired_product(productName);
		prod.findElement(addToCartButton).click();
		wait_till_elements_loads(messagePoUp);
		wait_element_to_Disapear(scroll);
	}
	
	
	
}

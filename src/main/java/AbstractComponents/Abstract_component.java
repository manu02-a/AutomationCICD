package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Manuacademy.pakageObjects.CartPage;
import Manuacademy.pakageObjects.OrderPage;

public class Abstract_component {

	WebDriver driver;
	public Abstract_component(WebDriver driver) {
		this.driver=driver;
		
	}
	
	@FindBy(css=".ng-animating")
	WebElement scroll;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart_button;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorloginMessage;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderButton;
	
	By findBy = By.cssSelector(".card-body");
	
	public void wait_till_elements_loads(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	public void wait_till_Webelements_loads(WebElement errorloginMessage) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOf(errorloginMessage));
	}
	
	public void wait_element_to_Disapear(WebElement scroll) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(scroll));
	}
	
	public CartPage click_on_cart() {
		cart_button.click();
		CartPage cart_page = new CartPage(driver);
		return cart_page;
	}
	
	public OrderPage click_on_Order() {
		orderButton.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}

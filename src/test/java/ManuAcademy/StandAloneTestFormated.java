package ManuAcademy;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ManuAcademy.Basecomponents.BaseComponents;
import Manuacademy.pakageObjects.CartPage;
import Manuacademy.pakageObjects.CheckOutPage;
import Manuacademy.pakageObjects.FinalPage;
import Manuacademy.pakageObjects.LandingPage;
import Manuacademy.pakageObjects.OrderPage;
import Manuacademy.pakageObjects.ProductCatalogue;

public class StandAloneTestFormated extends BaseComponents {
	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData", groups = "parameterization")
	public void SubmitOrder(HashMap<String,String> input) throws IOException{
		// TODO Auto-generated method stub
		
		ProductCatalogue productCatalogue = landingPage.login(input.get("email"), input.get("password"));
		List<WebElement> prod = productCatalogue.getProductList();
		productCatalogue.filtering_desired_product(input.get("product"));
		productCatalogue.addToCart(input.get("product"));
		
		CartPage cart_page = productCatalogue.click_on_cart();
		cart_page.cartProducts();
		boolean match = cart_page.check_cartProducts(input.get("product"));
	
		
		//Assertion to validate
		Assert.assertTrue(match);
		
		
		
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
		CheckOutPage checkout = cart_page.check_out_button();
		checkout.selectCountry();
		FinalPage finalpage = checkout.placeOrder();
		String confirmationMessage = finalpage.getConfermationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void verifyOrderPresent() {
		
		ProductCatalogue productCatalogue = landingPage.login("manuselenium@gamil.com", "Manu@selenium1");
		OrderPage orderPage = productCatalogue.click_on_Order();
		boolean match = orderPage.check_ordered_product(productName);
		Assert.assertTrue(match);
	}
	
	
	//DataProvider using array
	/*@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"manuselenium@gamil.com","Manu@selenium1","ZARA COAT 3"},{"kumar@12gmail.com","Kumar@12","ADIDAS ORIGINAL"}};
	}*/
	
	@DataProvider
	public Object[][] getData() {
		
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("email", "manuselenium@gamil.com");
		map.put("password", "Manu@selenium1");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String,String>();
		map1.put("email", "kumar@12gmail.com");
		map1.put("password", "Kumar@12");
		map1.put("product", "ADIDAS ORIGINAL");
		
		
		
		return new Object[][] {{map},{map1}};
	}
	
	/*@DataProvider
	public Object[][] getData() throws IOException, ParseException {
		
		String[] data = jsonReader();
		
		for(int i=0; i<data.length; i++) {
			data[i].split(",");
		}
		
		
		
		
		
		
		return new Object[][] {{user[0]},{user[1]},{user[2]}};
		
		
		
	
	}*/

}

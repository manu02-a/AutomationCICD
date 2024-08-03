package ManuAcademy;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ManuAcademy.Basecomponents.BaseComponents;
import Manuacademy.pakageObjects.CartPage;
import Manuacademy.pakageObjects.ProductCatalogue;

public class ErrorValidation extends BaseComponents{

	@Test(groups= {"ErrorValidation"}, retryAnalyzer = ManuAcademy.Basecomponents.Retry.class)
	public void incorrectLogin() {
		//String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.login("manuselenium@gamil.com", "Manu@selenium");
		String errorMessage = landingPage.validateIncorrectPassword();
		Assert.assertEquals(errorMessage, "Incorrect email  password.");
		
	}
	
	@Test(retryAnalyzer = ManuAcademy.Basecomponents.Retry.class)
	public void ProductErrorValidation() {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.login("manuselenium@gamil.com", "Manu@selenium1");
		List<WebElement> prod = productCatalogue.getProductList();
		productCatalogue.filtering_desired_product(productName);
		productCatalogue.addToCart(productName);
		
		CartPage cart_page = productCatalogue.click_on_cart();
		cart_page.cartProducts();
		boolean match = cart_page.check_cartProducts(productName);
	
		
		//Assertion to validate
		Assert.assertTrue(match);
		
		
	}
	
	
	
	
}

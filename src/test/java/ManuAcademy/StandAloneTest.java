package ManuAcademy;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Manuacademy.pakageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://rahulshettyacademy.com/client");
		// Entering user name
		driver.findElement(By.id("userEmail")).sendKeys("manuselenium@gamil.com");
		// Entering password
		driver.findElement(By.id("userPassword")).sendKeys("Manu@selenium1");
		// clicking on login
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".card-body")));
		
		// listing the items in a list
		List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));
		
		WebElement prod = products.stream().filter(
				product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector("button:last-of-type")).click();
		
		//explicitly wait till message pop ups
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//explicitly wait till page loads
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//clicking on cart button
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//storing all products in cart into list
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		
		//checking the product present in list
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
		//Assertion to validate
		Assert.assertTrue(match);
		
		//clicking on checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
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
		
		//By using Action
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group .input.txt.text-validated")),"india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button .ng-star-inserted")));
		
		driver.findElement(By.xpath("(//button/span[@class='ng-star-inserted'])[2]")).click();
		
		
		
		//clicking on place order
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//grabbing the text
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();

	}

}

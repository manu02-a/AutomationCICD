package ManuAcademy.Basecomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.google.common.io.Files;

import Manuacademy.pakageObjects.LandingPage;

public class BaseComponents {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initialization() throws IOException{
		//initialization
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Manuacademy//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		
		}
		
		else if(browserName.contains("Edge")){
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage LaunchApplication() throws IOException {
		
		driver = initialization();
		landingPage = new LandingPage(driver);
		landingPage.go_to();
		return landingPage;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeWindow() {
		driver.quit();;
	}
	
	public String[] jsonReader() throws IOException, ParseException {
		//Parsing the file
		JSONParser parser = new JSONParser();
		
		FileReader reader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\ManuAcademy\\Testdata\\PurchaseOrder.json");
		Object obj = parser.parse(reader);
		
		JSONObject jsonObject=(JSONObject)obj;
		JSONArray array = (JSONArray) jsonObject.get("Userlogins");
		
		String[] arr = new String[array.size()];
		for(int i=0; i<array.size(); i++) {
			JSONObject users = (JSONObject) array.get(i);
			String email = (String)users.get("email");
			String password = (String)users.get("password");
			String product = (String)users.get("product");
			
			arr[i]= email+","+password+","+product;
			
		}
		return arr;
		
	}
	
	public String getScreenshot(String testcase, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports"+testcase+".png");
		Files.copy(source, file);
		return System.getProperty("user.dir")+"//reports"+testcase+".png";
	}
	
}

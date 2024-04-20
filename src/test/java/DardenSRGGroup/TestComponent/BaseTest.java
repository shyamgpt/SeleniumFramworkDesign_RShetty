package DardenSRGGroup.TestComponent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import DardenSRGGroup.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public LandingPage landingPage;

	public WebDriver driver;

	public WebDriver initialization() throws IOException {

		Properties prop = new Properties();
//		FileInputStream fis = new FileInputStream(
//				System.getProperty("user.dir") + "\\src\\main\\java\\DardenSRGGroup\\resources\\GobalData.propertie");
//		
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Sumit\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\DardenSRGGroup\\resources\\GobalData.properties");
		prop.load(fis);
		//C:\Users\Sumit\eclipse-workspace\SeleniumFrameworkDesign\src\main\java\DardenSRGGroup\resources\GobalData.properties
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			// FireFox

		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
		driver = initialization();
//		LandingPage landingPage = new LandingPage(driver);
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
		
	}

}

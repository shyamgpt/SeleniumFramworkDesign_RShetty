package DardenSRGGroup.test;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import DardenSRGGroup.pageobjects.CartPage;
import DardenSRGGroup.pageobjects.LandingPage;
import DardenSRGGroup.pageobjects.ProductCatalouge;
import io.github.bonigarcia.wdm.WebDriverManager;

//https://www.youtube.com/shorts/SwJtiHfID-o
public class StandAloneTest {

	public static void main(String[] args) {

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("shyamg@gmail.com", "Kartmen@123");

		ProductCatalouge ProdCatalouge = new ProductCatalouge(driver);
		List<WebElement> products = ProdCatalouge.getProductList();
		ProdCatalouge.addProductToCart(productName);
		ProdCatalouge.goToCartPage();
		
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay(productName);
		//validation cannnot got the page object file
		Assert.assertTrue(match);
		
		cartPage.goToCheckOut();
		
		
		
		
		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-result")));

		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hello-primary")).getText();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

}

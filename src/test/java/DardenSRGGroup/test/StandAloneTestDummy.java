package DardenSRGGroup.test;

import java.time.Duration;
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

import DardenSRGGroup.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

//https://www.youtube.com/shorts/SwJtiHfID-o
public class StandAloneTestDummy {

	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
         
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://rahulshettyacademy.com/client");
		
//		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("shyamg@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Kartmen@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait1 = new WebDriverWait(driver,30);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		
		//ADIDAS ORIGINAL
		//ZARA COAT 3
		 
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(0));
		
		WebDriverWait wait = new WebDriverWait(driver,30);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//By Css Selector-->[routerlink*='cart']
		//By xpath --> //button[@routerlink='/dashboard/cart']
		
		// By Css Selector -->  .cartSection h3
		// By Xpath --> //*[@class='cartSection']/h3  Or //div[@class='cartSection']/h3
		// In place of tagName you can write '*' also
		
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	    Boolean match = cartProducts.stream().anyMatch(s-> s.getText().equalsIgnoreCase(productName));
	    Assert.assertTrue(match);
	    driver.findElement(By.cssSelector(".totalRow button")).click();
	    
	    //xpath --> (//button[contains(@class,'ta-item')])[2]
	    
	    // css --> .ta-item:nth-of-type(2)
	    
	    Actions act = new Actions(driver);
	    act.sendKeys(driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")), "india").build().perform();
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-result")));
	    
	    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	    driver.findElement(By.cssSelector(".action__submit")).click();
	    
	    String confirmMessage = driver.findElement(By.cssSelector(".hello-primary")).getText();
	    
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		
		
		
		
		
		
		
		
		
		
		

//		for(int i =0; i<Products.size(); i++) {
//			
//			String name =Products.get(i).getText();
//			System.out.println(name);
//			
//		}

	}

}

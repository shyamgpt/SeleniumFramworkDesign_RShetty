package DardenSRGGroup.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import DardenSRGGroup.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);

		// Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	
	
	@FindBy(css="tr td:nth-child(3)" )
	List<WebElement> orderProducts;
	
	@FindBy(css = ".totalRow button")
	WebElement chekoutEle;
	
	public boolean verifyOrderDisplay(String prodctName) {
		Boolean match = orderProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(prodctName));
		return match;
	}
	
	public CheckoutPage goToCheckOut() {
		chekoutEle.click();
		
		return new CheckoutPage(driver);
	}
	
	

//	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

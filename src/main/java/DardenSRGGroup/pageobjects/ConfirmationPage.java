package DardenSRGGroup.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import DardenSRGGroup.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	
	@FindBy(css= ".hello-primary")
	WebElement confiramtionMessage;
	
	
	public String getConfirmationMessage() {
		return confiramtionMessage.getText();
		
	}
	
	
//	String confirmMessage = driver.findElement(By.cssSelector(".hello-primary")).getText();
//
//	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

}

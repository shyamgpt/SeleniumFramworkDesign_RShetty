package DardenSRGGroup.test;

import java.io.IOException;
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
import org.testng.annotations.Test;

import DardenSRGGroup.TestComponent.BaseTest;
import DardenSRGGroup.pageobjects.CartPage;
import DardenSRGGroup.pageobjects.CheckoutPage;
import DardenSRGGroup.pageobjects.ConfirmationPage;
import DardenSRGGroup.pageobjects.LandingPage;
import DardenSRGGroup.pageobjects.ProductCatalouge;
import io.github.bonigarcia.wdm.WebDriverManager;

//https://www.youtube.com/shorts/SwJtiHfID-o
public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidtion() throws IOException {

		String productName = "ZARA COAT 3";
		landingPage.loginApplication("shyamg@gmail.com", "Kartmen@123");
		landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password", landingPage.getErrorMessage());
	}

	@Test
	public void productErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";

		ProductCatalouge ProdCatalouge = landingPage.loginApplication("shyamg@gmail.com", "Kartmen@123");

		List<WebElement> products = ProdCatalouge.getProductList();
		ProdCatalouge.addProductToCart(productName);
		CartPage cartPage = ProdCatalouge.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);

		Assert.assertTrue(match);

	}
	
	

}

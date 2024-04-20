package DardenSRGGroup.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DardenSRGGroup.TestComponent.BaseTest;
import DardenSRGGroup.pageobjects.CartPage;
import DardenSRGGroup.pageobjects.CheckoutPage;
import DardenSRGGroup.pageobjects.ConfirmationPage;
import DardenSRGGroup.pageobjects.LandingPage;
import DardenSRGGroup.pageobjects.OrderPage;
import DardenSRGGroup.pageobjects.ProductCatalouge;
import io.github.bonigarcia.wdm.WebDriverManager;

//https://www.youtube.com/shorts/SwJtiHfID-o
public class SubmitOrder extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {

//		LandingPage landingPage=	launchApplication(); --> Now declared in  @BeforeMethos
		ProductCatalouge ProdCatalouge = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = ProdCatalouge.getProductList();
		ProdCatalouge.addProductToCart(input.get("product"));
		CartPage cartPage = ProdCatalouge.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		// validation cannot got the page object file
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckOut();

		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		driver.close();--> Now declared in @AfterMethod

	}

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getData")
	public void OrderHistoryTest() {

		// ZARA COAT 3

		ProductCatalouge ProdCatalouge = landingPage.loginApplication("shyamg@gmail.com", "Kartmen@123");
		OrderPage orderPage = ProdCatalouge.goToOrderPage();
		orderPage.verifyOrderDisplay(productName);
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

	}

	public String getScreenshot(String testCaseName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports//" + testCaseName+ ".Png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" + testCaseName+ ".Png";

	}

//		public Object[][] getData() {
//		return new Object[] [] {{map1},{"Sonal@gmail.com","Sonalmen@123", "ADIDAS ORIGINAL"}};
//}

	@DataProvider
	public Object[][] getData() {

		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
		map1.put("email", "shyamg@gmail.com");
		map1.put("password", "Kartmen@123");
		map1.put("product", "ZARA COAT 3");

		HashMap<Object, Object> map2 = new HashMap<Object, Object>();
		map2.put("email", "Sonal@gmail.com");
		map2.put("password", "Sonalmen@123");
		map2.put("product", "ADIDAS ORIGINAL");

		return new Object[][] { { map1 }, { map2 } };

	}

}

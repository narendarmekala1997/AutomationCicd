package com.naren.automation.seleniumframeworkdesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

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

import pageobjects.CartPage;
import pageobjects.CheckOut;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.LandingPage;
import pageobjects.OrderPage;
import pageobjects.ProductCatalogue;
import testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";
	String username = "narendarmekala.1997@gmail.com";
	String password = "Naren@1997";	
	@Test(dataProvider="getData",groups= {"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {

			
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("username"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(input.get("productName"));
		//System.out.println("cmong hree");
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));

		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		System.out.println("cmong");
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		/*
		 * HashMap<String, String> map = new HashMap<String,String>();
		 * map.put("username", "narendarmekala.1997@gmail.com"); map.put("password",
		 * "Naren@1997"); map.put("productName","ZARA COAT 3"); HashMap<String, String>
		 * map1 = new HashMap<String,String>(); map1.put("username",
		 * "narendarmekala.1997@gmail.com"); map1.put("password", "Naren@1997");
		 * map1.put("productName","ZARA COAT 3");
		 */
		List<HashMap<String,String>> data = getJsonDataToMap("C:\\Users\\laxmi\\OneDrive\\Desktop\\seleniumframeworkdesign\\src\\test\\java\\data\\purchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	

}

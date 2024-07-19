package com.naren.automation.seleniumframeworkdesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.ProductCatalogue;
import testcomponents.BaseTest;

public class ErrorValidation extends BaseTest{
	
	@Test
	public void submitOrder() throws IOException {

		landingPage.loginApplication("narendarmekala.199@gmail.com", "naren");
		
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
				
		
	}
	@Test
	public void productErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";
		//WebDriver driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		String username = "narendarmekala.1997@gmail.com";
		String password = "Naren@1997";
		
	
		//LandingPage landingPage = new LandingPage(driver);
		//landingPage.goTo();
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);

		// ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);

		Assert.assertTrue(match);
	}

}

package com.naren.automation.seleniumframeworkdesign;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {

		//adding comments for demo
		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage = new LandingPage(driver);
		
		String username = "narendarmekala.1997@gmail.com";
		String password = "Naren@1997";
		
		driver.findElement(By.id("userEmail")).sendKeys(username);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.className("mb-3"));
		
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.tagName("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts =  driver.findElements(By.cssSelector(".cart h3"));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results button")));
		driver.findElement(By.cssSelector(".totalRow button")).click();
	
		/*driver.findElement(By.xpath("//input[@placeholder='Select Country']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results button")));
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button"));
		
		WebElement coun = countries.stream().filter(country -> country.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		coun.click();*/
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results button")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		
		

		

	}

}

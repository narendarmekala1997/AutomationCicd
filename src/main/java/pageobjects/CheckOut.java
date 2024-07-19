package pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstractcomponets.AbstractComponent;

public class CheckOut extends AbstractComponent{

	public CheckOut(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css=".cart h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement count;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement placeholder;
	
	By results = By.cssSelector(".ta-results button");
	
	@FindBy(css=".ta-results")
	WebElement disapper;
	
	@FindBy(css=".hero-primary")
	WebElement message;
	
	
	public void checkOutCart(String productName) throws InterruptedException {
		//System.out.println("coming here");
		cart.click();
		Boolean match = getCartProducts().stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		waitForElementToAppear(By.cssSelector(".items"));
		checkoutButton.click();
		waitForElementToDisappear(disapper);
		submit.click();
	
		
	}
	public List<WebElement> getCartProducts(){
		
		return cartProducts;
		
	}
	
	public void orderPlacing(WebDriver driver,String country) throws InterruptedException {
		Actions a = new Actions(driver);
		a.sendKeys(placeholder,country).build().perform();
		waitForElementToAppear(results);
		//Thread.sleep(Duration.ofSeconds(5));
		//System.out.println("coming");
	//	driver.findElement(By.cssSelector(".action__submit")).click();
	//	submit.click();
	}
	public void orderSuccess(String confirmMessage) {
		waitForElementToAppear(By.cssSelector(".hero-primary"));
		Assert.assertTrue(message.getText().equalsIgnoreCase(confirmMessage));
		
	}
}

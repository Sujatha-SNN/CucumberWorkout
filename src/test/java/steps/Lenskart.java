package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Lenskart extends BaseClass {

	Actions build;

	@Given("Open {string}")
	public void goTo(String string) {
		driver.get(string);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("Mouseover on Contact Lenses")
	public void mouseoverOnContactLenses() throws InterruptedException {
		build = new Actions(driver);
		build.moveToElement(driver.findElementByXPath("//a[text()='Contact Lenses']")).build().perform();
		Thread.sleep(500);
	}

	@Given("Click on Monthly under Explore By Disposability")
	public void clickOnMonthlyUnderExploreByDisposability() throws InterruptedException {
		driver.findElementByXPath("//span[text()='Monthly']").click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}

	@Given("Select brand as Aqualens")
	public void selectBrandAsAqualens() {
		WebElement aqualens = driver
				.findElementByXPath("//span[contains(text(),'Aqualens')]/preceding-sibling::input[@type='checkbox']");
		// js.executeScript("arguments[0].click();",aqualens);
		build.click(aqualens).build().perform();

	}

	@Given("Click on the first product")
	public void clickOnTheFirstProduct() throws InterruptedException {
		driver.findElementByXPath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div[1]").click();
		Thread.sleep(1000);
	}

	@Given("Click Buy Now")
	public void clickBuyNow() {
		driver.findElementByXPath("//button[text()='BUY NOW']").click();
	}

	@Given("Select No of boxes as {int} and Power as {int} for both eyes.")
	public void selectNoOfBoxesAsAndPowerAsForBothEyes(Integer int1, Integer int2) throws InterruptedException {
		driver.findElementByXPath("//select[@name='boxes']").click();

		WebElement selBoxesLE = driver.findElementByXPath("(//select[@name='boxes'])[1]");
		Select boxesDDLE = new Select(selBoxesLE);
		boxesDDLE.selectByValue("2");
		Thread.sleep(500);
		WebElement selBoxesRE = driver.findElementByXPath("(//select[@name='boxes'])[2]");
		Select boxesDDRE = new Select(selBoxesRE);
		boxesDDRE.selectByValue("2");
		driver.findElementByXPath("(//div[@class=' dropdown-display cl-dd']/i)[1]").click();
		driver.findElementByXPath("//div[text()='-1.00']").click();

		driver.findElementByXPath("(//div[@class=' dropdown-display cl-dd']/i)[1]").click();
		driver.findElementByXPath("//div[text()='-1.00']").click();

	}

	@Given("Type your name in User's name")
	public void typeYourNameInUserSName() {
	driver.findElementById("example-text-input").sendKeys("Nethra");
}

@Then("click Save and continue")
public void clickSaveAndContinue() throws InterruptedException {
	Thread.sleep(1000);
	driver.findElementByXPath("(//button[@unbxdattr='AddToCart'])[1]").click();
}

@Then("Print total amount and click Proceed to Checkout")
public void printTotalAmountAndClickProceedToCheckout() {
	String totalCost = driver.findElementByXPath("//span[@class='span6']/following-sibling::span").getText();
	System.out.println("totalCost"+totalCost);
	driver.findElementByXPath("//span[text()='Proceed To Checkout']/parent::a").click();
	
}
}

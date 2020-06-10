package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Ajio {
	ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	@Given("Open Ajio.com")
	public void openAjioCom() {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		//To disable Unwanted Notifications
		ChromeOptions options =  new ChromeOptions();
		options.addArguments("--disable-notifications");
		// To disable unwanted Alerts
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		driver = new ChromeDriver(options);
		//Get the url
		driver.get("https://www.ajio.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("MouseOver on Women,Categories")
	public void mouseoverOnWomenCategories() throws InterruptedException {
		driver.findElementByXPath("//a[@href='/shop/women']").click();
		Thread.sleep(3000);
		Actions build = new Actions(driver);
		WebElement women = driver.findElementByXPath("//a[@title='WOMEN']");
		build.moveToElement(women).perform();
		Thread.sleep(2000);
		
		WebElement categories = driver.findElementByXPath("(//a[text()='CATEGORIES'])[1]");
		Point location = categories.getLocation();
		System.out.println(location.x + "----" + location.y);
		build.moveToElement(categories, location.x, location.y).perform();
		
	}

	@Given("Click on Kurtas")
	public void clickOnKurtas() {
		driver.findElementByXPath("(//a[text()='Kurtas'])[2]").click();
	}

	@Given("Select Ajio Brand")
	public void selectAjioBrand() {
		driver.findElementByXPath("//span[text()='brands']").click();
		driver.findElementByXPath("//label[@for='AJIO']").click();
		
	}

	@Given("Check all the Kurtas are Ajio Brand")
	public void checkAllTheKurtasAreAjioBrand() {
		List<WebElement> ajiokurtas = driver.findElementsByXPath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div");
		System.out.println(ajiokurtas.size());
		for (WebElement kurta: ajiokurtas) {
			
			WebElement kurtaBrand = kurta.findElement(By.xpath("//div[text()='AJIO']"));
			if(!kurtaBrand.getText().equalsIgnoreCase("AJIO"))
			{
				System.out.println("Non_Ajio brand is selected");
			}
		}
	}

	@Given("Sort the results based on Discount")
	public void sortTheResultsBasedOnDiscount() {
		WebElement selectSortBy = driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		Select selSrt = new Select(selectSortBy);
		selSrt.selectByValue("discount-desc");
	}

	@Given("Select Blue Color and add the first kurta to bag")
	public void selectBlueColorAndAddTheFirstKurtaToBag() throws InterruptedException {
		driver.findElementByXPath("//span[text()='colors']").click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//i[@class='facet-checkbox-color-inner'])[1]")).click();
 		
		//Add the first kurta to bag
		driver.findElementByXPath("(//a[@class='rilrtl-products-list__link'])[4]").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listWH = new ArrayList<String>(windowHandles);
		Thread.sleep(3000);
		driver.switchTo().window(listWH.get(1));
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
	}

	@Given("Verify the error message for skipping size selection")
	public void verifyTheErrorMessageForSkippingSizeSelection() {
		System.out.println(driver.findElement(By.xpath("//span[@class='edd-pincode-msg-details']")).getText());
	}

	@Given("Select size and add the kurta to bag")
	public void selectSizeAndAddTheKurtaToBag() {
		driver.findElementByXPath("(//div[@class='circle size-variant-item size-instock '])[2]").click();
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
	}

	@Given("Enter pin-code for estimated delivery")
	public void enterPinCodeForEstimatedDelivery() throws InterruptedException {
		js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		WebElement pincodeMesg = driver.findElementByXPath("//span[contains(@class,'edd-pincode-msg-details edd-pincode-msg-details-pointer')]");
		js.executeScript("arguments[0].click()", pincodeMesg);

	}

	@Given("Enter pincode as {int} and click Confirm pincode")
	public void enterPincodeAsAndClickConfirmPincode(Integer int1) {
		driver.findElementByXPath("//input[@name='pincode']").sendKeys(String.valueOf(int1));
		driver.findElementByXPath("//button[@class='edd-pincode-modal-submit-btn']").click();
	}

	@Then("Print the message")
	public void printTheMessage() {
		List<WebElement> messageList = driver.findElementsByXPath("//ul[@class='edd-message-success-details']/li");
		for (WebElement message : messageList) {
			System.out.println(message.getText());
		}
	}

	@Then("Click Go to Bag")
	public void clickGoToBag() {
		WebElement goToBag = driver.findElementByXPath("//div[@class='pdp-addtocart-button']/a");
		js.executeScript("arguments[0].click()", goToBag);
		
	}

	@Then("Click on Proceed to Shipping")
	public void clickOnProceedToShipping() {
		WebDriverWait wait =  new WebDriverWait(driver, 20);
		WebElement pToShipping = driver.findElementByXPath("//button[text()='Proceed to shipping']");
		wait.until(ExpectedConditions.elementToBeClickable(pToShipping));
		pToShipping.click();
	}
	
	/*@Then("Close the browser")
	public void closeTheBrowser() {
		driver.close();
	}*/
}

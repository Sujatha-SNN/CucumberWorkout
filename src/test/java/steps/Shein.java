package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Given;

public class Shein {
	ChromeOptions options;
	ChromeDriver driver;
	Actions build;
	JavascriptExecutor js;

	@Given("Set the browser and other properties")
	public void setTheBrowserAndOtherProperties() {
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
	}

	@Given("Open chrome browser")
	public void openChromeBrowser() {
		driver = new ChromeDriver(options);	    
	}

	@Given("Maximize the brower")
	public void maximizeTheBrower() {
		driver.manage().window().maximize();
	}

	@Given("Apply ImplicitWait")
	public void applyImplicitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	@Given("Load {string}")
	public void load(String string) {
		driver.get(string);
	}

	@Given("Mouseover on Clothing and click Jeans")
	public void mouseoverOnClothingAndClickJeans() throws InterruptedException {
		Thread.sleep(2000);
		build = new Actions(driver);
		js = (JavascriptExecutor) driver;
		WebElement popup = driver.findElementByXPath("//div[@class='c-coupon-box']//i[1]");
		js.executeScript("arguments[0].click();", popup);
		// driver.switchTo().defaultContent();
		build.moveToElement(driver.findElementByXPath("//a[@title='CLOTHING'][1]")).build().perform();
		Thread.sleep(1000);
		build.moveToElement(driver.findElementByXPath("(//a[@title='Jeans'])[1]")).click().build().perform();
	}

	@Given("Choose Black under Jeans product count")
	public void chooseBlackUnderJeansProductCount() {
		driver.findElementByXPath("//div[@class='header-label ']/span/a[text()='Black']").click();
	}

	@Given("Check size as medium")
	public void checkSizeAsMedium() {
		driver.findElementByXPath("//span[text()='Size']/following-sibling::i").click();
		WebElement size = driver.findElementByXPath("(//span[@class='attr-check-box']/i)[9]");
		js.executeScript("arguments[0].click();", size);
	}

	@Given("Click + in color")
	public void clickInColor() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElementByXPath("//span[text()='Color']/following-sibling::i").click();

	}

	@Given("Check whether the color is black")
	public void checkWhetherTheColorIsBlack() throws InterruptedException {
		String color = driver.findElementByXPath("(//a[@class='j-auto-attrlink']/span)[5]").getText();

		System.out.println("color + " + color);
		if (color.equalsIgnoreCase("black"))
			System.out.println("black is chosen");
		Thread.sleep(1000);
	}

	@Given("Click first item to Add to Bag")
	public void clickFirstItemToAddToBag() throws InterruptedException {
		WebElement firstItem = driver
				.findElement(By.xpath("(//img[@class='c-goodsitem__secimg j-goodsitem__secimg'])[1]"));
		js.executeScript("arguments[0].click();", firstItem);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> whList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(whList.get(1));
		Thread.sleep(1000);

	}

	@Given("Click the size as M abd click Submit")
	public void clickTheSizeAsMAbdClickSubmit() {
		driver.findElementByXPath("//span[@class='inner' and text()[normalize-space()='M']]").click();
		driver.findElementByXPath("(//button[@class='she-btn-black she-btn-xl'])[1]").click();
		
	}

	@Given("Click view Bag")
	public void clickViewBag() throws InterruptedException {
		Thread.sleep(1000);
		build.moveToElement(driver.findElementByXPath("//i[@class='iconfont-critical icon-gouwudai']")).build().perform();
		build.click(driver.findElementByXPath("//a[text()='view bag']")).perform();
		Thread.sleep(1000);
	}

	@Given("Check the size is Medium or not.")
	public void checkTheSizeIsMediumOrNot() {
		String sizeInBag = driver.findElementByXPath("//span[@class='gd-size']/em").getText();
		if(sizeInBag.equalsIgnoreCase("M"))
			System.out.println("Same size is selected");


	}
}

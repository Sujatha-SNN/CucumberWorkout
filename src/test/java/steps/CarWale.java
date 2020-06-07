package steps;

import java.util.ArrayList;
import java.util.Collections;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CarWale {
	ChromeDriver driver;
	WebDriverWait wait;
	// Declare and initialise JS Executor
	JavascriptExecutor js ;

	@Given("Go to https:\\/\\/www.carwale.com\\/")
	public void goToHttpsWwwCarwaleCom() {
		//To disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		// To disable unwanted Alerts
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		driver = new ChromeDriver(options);
		js = (JavascriptExecutor) driver;	
		
		driver.get("https:\\/\\/www.carwale.com\\/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("Click on Used")
	public void clickOnUsed() {
		driver.findElementByXPath("//li[@data-tabs='usedCars']").click();
	}

	@Given("Select the City as Chennai")
	public void selectTheCityAsChennai() {
		driver.findElementByXPath("//div[@class='used-cars-search']/input").sendKeys("Chennai");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("ui-menu-item")));
		driver.findElementByClassName("ui-menu-item").click();
	}

		   
	@Given("Select budget min \\({int}L) and max \\({int}L) and Click Search")
	public void selectBudgetMinLAndMaxLAndClickSearch(Integer int1, Integer int2) {
		System.err.println(int1);
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//li[@data-min-price='"+int1.toString()+"']")));
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//li[@data-max-price='"+int2.toString()+"']")));
		driver.findElementById("btnFindCar").click();
	}

	@Given("Select Cars with Photos under Only Show Cars With")
	public void selectCarsWithPhotosUnderOnlyShowCarsWith() {
		driver.findElementByXPath("//ul/li[@name='CarsWithPhotos']").click();
	}

	@Given("Select Manufacturer as {string} --> Creta")
	public void selectManufacturerAsCreta(String string) {
		driver.findElementByXPath("//li[@data-manufacture-en='Hyundai']").click();
		driver.findElementByXPath("(//li[@class=\"us-sprite rootLi\"])[1]").click();
	}

	@Given("Select Fuel Type as Petrol")
	public void selectFuelTypeAsPetrol() {
		js.executeScript("arguments[0].click()", driver.findElementByXPath("//h3[contains(text(),'Fuel')]"));
		js.executeScript("arguments[0].click()", driver.findElementByXPath("//li[@name='Petrol']/span"));	}

	@Given("Select Best Match as {string}")
	public void selectBestMatchAs(String string) {
		// To use Select by index
		WebElement bestMatchDD = driver.findElementByXPath("(//div[@class='form-control-box']/select)[2]");
		Select lkmtohkm = new Select(bestMatchDD);
		lkmtohkm.selectByIndex(4);
	}

	@Given("Validate the Cars are listed with KMs Low to High")
	public void validateTheCarsAreListedWithKMsLowToHigh() {
		List<WebElement> kmsList = driver.findElementsByXPath("//span[@class=\"slkms vehicle-data__item\"]");
		List<Integer> kmsIntList = new ArrayList<Integer>();

		// Convert the Kms in String to Integer
		for (int i = 0; i < kmsList.size(); i++) {
			kmsIntList.add(i, Integer.parseInt(kmsList.get(i).getText().replaceAll("[\\,\\s[a-zA-Z]]", "")));
		}

		// Copy the unsorted list to a new List
		List<Integer> kmsUnSortedIntList = new ArrayList<Integer>();
		kmsUnSortedIntList.addAll(kmsIntList);
		System.out.println(kmsUnSortedIntList);

		// Call sort to sort the Kms
		Collections.sort(kmsIntList);
		System.out.println(kmsIntList);

		// Compare the values in both Lists
		boolean boolval = kmsUnSortedIntList.equals(kmsIntList);
		if(boolval)
		System.out.println("It is sorted based on Kms");

	}
	
	
	@Given("Add the least KM ran car to Wishlist")
	public void addTheLeastKMRanCarToWishlist() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElementByXPath("(//a[@class='nomoreTips cur-pointer'])[1]").click();
		Actions build = new Actions(driver);
		build.moveToElement(driver.findElementByXPath("//span[@class='shortlist-icon--inactive shortlist'][1]")).click()
				.build().perform();
	}


	@Given("Go to Wishlist and Click on More Details")
	public void goToWishlistAndClickOnMoreDetails() {
		driver.findElementByXPath("//li[@data-role='click-tracking']").click();
		driver.findElementByXPath("//a[text()='More details »']").click();

	}

	@Then("Print the first car name")
	public void printTheFirstCarName() {
		String text = driver.findElementByXPath("(//span[@data-carname-id='carname'])[1]").getText();
		System.out.println(text);
	}
	
	@Then("Print all the details under Overview in the Same way as displayed in application")
	public void printAllTheDetailsUnderOverviewInTheSameWayAsDisplayedInApplication() throws InterruptedException {
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listWH = new ArrayList<String>(windowHandles);
		Thread.sleep(3000);
		driver.switchTo().window(listWH.get(1));
		WebElement ulElement = driver.findElementByXPath("//div[@class='cw-tabs cw-tabs-flex cw-tabs-inner-margin0']/ul");
		List<WebElement> headerElements = ulElement.findElements(By.tagName("li"));

		for (WebElement header : headerElements) {
			System.out.print(header.getText() + "      ");

		}
		List<WebElement> liRows = driver.findElementsByXPath("//div[@class='overview-list padding-bottom10']//li");
		Thread.sleep(3000);
		int k = 0;
		System.out.println("\n");
		for (int i = 0; i < liRows.size(); i++) {
			k = k + 1;
			System.out.printf("%-20s %30s %n",liRows.get(i).findElement(By.xpath("(//div[@class='equal-width text-light-grey'])[" + k + "]")).getText()
					, liRows.get(i).findElement(By.xpath("(//div[@class='equal-width dark-text'])[" + k + "]")).getText());
			}
		Thread.sleep(3000);

		
	}
	
	
	

	@Then("Close the browser")
	public void closeTheBrowser() {
		driver.quit();
	}

	
	

}

package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class Trivago {
	ChromeDriver driver;
	WebDriverWait wait;
	Actions build;

	@Given("Type Agra in Destination and select Agra, Uttar Pradesh.")
	public void typeAgraInDestinationAndSelectAgraUttarPradesh() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		// Type in Agra in search destination

		// To disable unwanted Alerts
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		driver = new ChromeDriver(options);
		build = new Actions(driver);
		driver.get("https://www.trivago.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElementById("querytext").sendKeys("Agra, Uttar Pradesh");

		WebElement dest = driver.findElementByClassName("ssg-suggestions");
		List<WebElement> destList = dest.findElements(By.tagName("li"));
		for (WebElement place : destList) {
			System.out.println(place.getText());
			/*
			 * List<WebElement> divTitles = place.findElements(By.xpath(
			 * "//div[@class='ssg-suggestion__info']/span[@class='ssg-title']"));
			 * System.err.println("divtitles" + divTitles.size()); for (WebElement divTitle
			 * : divTitles) {
			 */
			if (place.getText().contains("Agra, Uttar Pradesh")) {
				build.sendKeys(place, Keys.TAB).perform();
				break;
			}

		}
		Thread.sleep(2000);
	}

	@And("Choose May {int} as check in and May {int} as check out")
	public void chooseMayAsCheckInAndMayAsCheckOut(Integer int1, Integer int2) throws InterruptedException {

		build.click(driver.findElement(By.xpath("//time[@datetime='2020-06-15']"))).build().perform();
		Thread.sleep(1000);
		build.click(driver.findElement(By.xpath("//time[@datetime='2020-06-20']"))).build().perform();
		Thread.sleep(1000);
	}

	@And("Select Room as Family Room")
	public void selectRoomAsFamilyRoom() throws InterruptedException {
		build.moveToElement(driver.findElementByXPath("//span[text()='Family rooms']")).click().perform();
		Thread.sleep(2000);

	}

	@And("Choose Number of Adults {int} , Childern {int} and set Child's Age as {int}")
	public void chooseNumberOfAdultsChildernAndSetChildSAgeAs(Integer int1, Integer int2, Integer int3)
			throws InterruptedException {

		WebElement adultsSel = driver.findElementById("select-num-adults-2");
		Select selAdult = new Select(adultsSel);
		selAdult.selectByValue("3");
		WebElement childSel = driver.findElementById("select-num-children-2");
		Select selChild = new Select(childSel);
		selChild.selectByValue("1");
		Thread.sleep(2000);
		WebElement childAgeSel = driver.findElementById("select-ages-children-2-3");
		Select selAgeChild = new Select(childAgeSel);
		selAgeChild.selectByValue("4");

	}

	@And("Click Confirm button and click Search")
	public void clickConfirmButtonAndClickSearch() {
		driver.findElementByXPath("//span[text()='Confirm']/parent::button").click();
		driver.findElementByXPath("//button[@data-qa='search-button']").click();
	}

	@And("Select Accommodation type as Hotels only and choose {int} stars")
	public void selectAccommodationTypeAsHotelsOnlyAndChooseStars(Integer int1) throws InterruptedException {
		Thread.sleep(3000);
		build.moveToElement(driver.findElementByXPath("//button[@title='All types']")).build().perform();
		// build.moveToElement(driver.findElementByXPath("//button[@title='All
		// types']")).click().build().perform();
		Thread.sleep(2000);
		driver.findElementByXPath("(//input[@name='accommodation-type'])[2]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//button[@title='4-star hotels']").click();
		driver.findElementById("filter-popover-done-button").click();
	}

	@And("Select Guest rating as Very Good")
	public void selectGuestRatingAsVeryGood() throws InterruptedException {
		WebElement guestrating = driver.findElementByXPath("//button[@type='button' ]/strong[text()='Guest rating']");
		build.moveToElement(guestrating).click().perform();
		Thread.sleep(2000);
		WebElement hotelRange = driver.findElementByXPath("//button/span[text()='Very good']");
		build.moveToElement(hotelRange).click();

	}

	@And("Set Hotel Location as Agra Fort and click Done")
	public void setHotelLocationAsAgraFortAndClickDone() {
		WebElement hotelLoc = driver.findElementByXPath("//button[@type='button' ]/strong[text()='Hotel location']");
		build.moveToElement(hotelLoc).click().perform();
		WebElement selLoc = driver.findElementById("pois");
		Select selHotel = new Select(selLoc);
		selHotel.selectByVisibleText("Agra Fort");
		driver.findElementById("filter-popover-done-button").click();

	}

	@And("In more Filters, select Air conditioning, Restaurant and WiFi and click Done")
	public void inMoreFiltersSelectAirConditioningRestaurantAndWiFiAndClickDone() throws InterruptedException {
		WebElement moreFilters = driver.findElementByXPath("//button/Strong[text()='More filters']");
		build.moveToElement(moreFilters).click().perform();

		Thread.sleep(1000);
		driver.findElementByXPath("(//label[text()='Air conditioning']/following-sibling::div/input)[1]").click();
		driver.findElementByXPath("(//label[text()='WiFi']/following-sibling::div/input)[1]").click();
		driver.findElementByXPath("(//label[text()='Restaurant']/following-sibling::div/input)[1]").click();
		driver.findElementById("filter-popover-done-button").click();

	}

	@And("Sort the result as Rating & Recommended")
	public void sortTheResultAsRatingRecommended() {
		WebElement sortbyRecom = driver.findElementById("mf-select-sortby");
		Select selSortbyRecom = new Select(sortbyRecom);
		selSortbyRecom.selectByValue("7");
	}

	@And("Print the Hotel name, Rating, Number of Reviews and Click View Deal")
	public void printTheHotelNameRatingNumberOfReviewsAndClickViewDeal() {
		String hotelName = driver.findElementByXPath("(//span[@class='item-link name__copytext'])[1]").getText();
		String stars = driver.findElementByXPath("(//meta[@itemprop='ratingValue'])[1]").getAttribute("content");
		String review = driver
				.findElement(By.xpath("(//span[@class='details-paragraph details-paragraph--rating'])[1]")).getText();
		System.out.println("hotelName : " + hotelName + "stars :" + stars + "reviews:" + review);
		driver.findElementByXPath("(//button[@data-qa='champion-deal'])[1]").click();
	}

	@And("Print the URL of the Page")
	public void printTheURLOfThePage() {
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);

	}

	@And("Print the Price of the Room and click Choose Your Room")
	public void printThePriceOfTheRoomAndClickChooseYourRoom() throws InterruptedException {

		Set<String> windowHandles = driver.getWindowHandles();

		List<String> listWH = new ArrayList<String>(windowHandles);
		Thread.sleep(2000);
		driver.switchTo().window(listWH.get(1));

		String price = driver.findElementByXPath(
				"//div[contains(@class,'bui-price-display__value prco-text-nowrap-helper prco-inline-block-maker-helper')]")
				.getText();
		System.out.println(price);

	}

	@And("Click Reserve and I'll Reserve")
	public void clickReserveAndILlReserve() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElementByXPath("(//span[text()[normalize-space()='Choose your room']]/parent::a)[1]").click();
		driver.findElementByXPath("(//span[text()[normalize-space()='Reserve']]/parent::a)[1]").click();
		Thread.sleep(1000);
		WebElement selroom = driver.findElementByXPath("(//select[@class='hprt-nos-select'])[1]");
		Select selRoom = new Select(selroom);
		selRoom.selectByValue("1");
		driver.findElementByXPath("//div[@class='hprt-reservation-cta']/button").click();

	}

}

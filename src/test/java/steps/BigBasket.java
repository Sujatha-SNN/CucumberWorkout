package steps;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class BigBasket {
	ChromeOptions options;
	ChromeDriver driver;
	Actions build;
	JavascriptExecutor js;
	List<WebElement> productnames;
	List<WebElement> brands;

	@Given("Set all browser and other properties")
	public void setAllBrowserAndOtherProperties() {
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	}

	@Given("open the chrome browser")
	public void openTheChromeBrowser() {
		driver = new ChromeDriver(options);
	}

	@Given("Load the bigbasket url")
	public void loadTheBigbasketUrl() {
		driver.get("https://www.bigbasket.com/");
	}

	@Given("maximize the browser")
	public void maximizeTheBrowser() {
		driver.manage().window().maximize();
	}

	@Given("apply wait")
	public void applyWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("Mouse over on  Shop by Category")
	public void mouseOverOnShopByCategory() {
		build = new Actions(driver);
		build.moveToElement(driver.findElementByXPath("//a[text()=' Shop by Category ']")).build().perform();

	}

	@Given("Go to FOODGRAINS, OIL & MASALA and RICE & RICE PRODUCTS")
	public void goToFOODGRAINSOILMASALAAndRICERICEPRODUCTS() throws InterruptedException {
		Thread.sleep(2000);
		build.moveToElement(driver.findElement(By.xpath("(//a[text()='Foodgrains, Oil & Masala'])[2]"))).build()
				.perform();
		Thread.sleep(1000);
		build.moveToElement(driver.findElement(By.xpath("(//a[text()='Rice & Rice Products'])[2]"))).build().perform();
		Thread.sleep(1000);

	}

	@Given("Click on BOILED & STEAM RICE")
	public void clickOnBOILEDSTEAMRICE() {
		build.click(driver.findElement(By.xpath("(//a[text()='Boiled & Steam Rice'])[2]"))).build().perform();
	}

	@Given("Get the URL of the page and check with site navigation link")
	public void getTheURLOfThePageAndCheckWithSiteNavigationLink() throws InterruptedException {
		System.out.println(driver.getCurrentUrl());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		List<WebElement> breadcrumbs = driver.findElementsByXPath("//div[@class='breadcrumb-item']");
		StringBuffer breadCrumbsStr = new StringBuffer();

		for (WebElement breadcrumb : breadcrumbs) {
			String text = breadcrumb.getText();
		}
		Thread.sleep(3000);
		String childCrumb = driver.findElementByXPath("(//span[text()='Boiled & Steam Rice'])[1]").getText();
		Thread.sleep(1000);
		if (breadCrumbsStr.append(childCrumb)
				.equals(new StringBuffer("HOME >FOODGRAINS, OIL & MASALA>RICE & RICE PRODUCTS>")))
			System.out.println("Same");

	}

	@Given("Choose the Brand as bb Royal")
	public void chooseTheBrandAsBbRoyal() throws InterruptedException {
		WebElement brand = driver.findElementByXPath("(//span[text()='bb Popular'])[2]/preceding-sibling::span/i");
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", brand);
		Thread.sleep(1000);

	}

	@Given("Go to Ponni Boiled Rice and select {int}kg bag from Dropdown")
	public void goToPonniBoiledRiceAndSelectKgBagFromDropdown(Integer int1) throws InterruptedException {
		WebElement dropdown = driver
				.findElementByXPath("(//button[@data-toggle='dropdown']/i[@class='fa fa-caret-down'])[1]");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", dropdown);

	}

	@Given("Click Add button")
	public void clickAddButton() {
		WebElement product = driver.findElementByXPath("(//a[@ng-click='vm.onProductChange(allProducts)'])[2]");
		js.executeScript("arguments[0].click();", product);
		WebElement addButton = driver.findElementByXPath("(//button[@qa='add'])[1]");
		js.executeScript("arguments[0].click();", addButton);
	}

	@Given("click Address")
	public void clickAddress() throws InterruptedException {
		WebElement changeLoc = driver.findElementByXPath("(//div[@class='col-md-6']/a)[1]");
		changeLoc.click();
		// js.executeScript("arguments[0].click();", changeLoc);
		Thread.sleep(1000);

	}

	@Given("Select CHennai as City, Alandur{int},Chennai as Area  and click Continue")
	public void selectCHennaiAsCityAlandurChennaiAsAreaAndClickContinue(Integer int1) {
		driver.findElementByXPath("(//input[@qa='areaInput'])[1]").sendKeys("Alandur", Keys.ENTER);
		driver.findElementByXPath("//li[@class='ng-scope active']/a").click();
		// js.execute
		driver.findElementByXPath("//button[text()='Continue']").click();
	}

	@Given("Go to search box and type Dal")
	public void goToSearchBoxAndTypeDal() throws InterruptedException {
		driver.findElementByXPath("//input[@ng-model='vm.searchPhrase']").sendKeys("Dal");
		brands = driver.findElementsByXPath("//p[@class='brand ng-binding']");
		productnames = driver.findElementsByXPath("//p[@class='name ng-binding']");
		System.out.println(brands.size());

		

	}

	@Given("Add Toor\\/Arhar Dal {int}kg and set Qty {int} from the list")
	public void addToorArharDalKgAndSetQtyFromTheList(Integer int1, Integer int2) throws InterruptedException {
		for (int i = 0; i < brands.size(); i++) {
			WebElement brandname = brands.get(i);
			String brandsearch = brandname.getText();
			WebElement pname = productnames.get(i);
			if (brandsearch.equalsIgnoreCase("bb popular"))
				if (pname.getText().contains("Toor/Arhar ")) {
					System.out.println("yes" + pname.getText());
					Thread.sleep(1000);
					int j = (i + 1);
					WebElement ele = driver.findElementByXPath("(//input[@ng-model='sdata.qty'])[" + j + "]");
					System.out.println(ele.getText());

					String text = driver.findElementByXPath("(//div[@ng-bind=\"sdata.weight\"])[" + j + "]").getText();
					System.out.println(text);
					if (text.contains("2 kg")) {
						System.out.println("inside If");
						driver.findElementByXPath("(//input[@ng-model='sdata.qty'])[" + j + "]").clear();
						driver.findElementByXPath("(//input[@ng-model='sdata.qty'])[" + j + "]").sendKeys("2");
						driver.findElementByXPath("(//button[@class='btn btn-default yellow-btn'])[" + j + "]").click();
					}
				}
		}
	}

	@Given("Mouse over on My Basket")
	public void mouseOverOnMyBasket() {
		WebElement basket = driver.findElementByXPath("//span[@class=\"basket-content\"]");
		build.moveToElement(basket).build().perform();
	}

	@Then("Take a screen shot")
	public void takeAScreenShot() throws InterruptedException, IOException {
		Thread.sleep(2000);
		FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE) , new File("./reports/images/basket.png"));
		Thread.sleep(2000);
	}

	@Then("Click View Basket and Checkout")
	public void clickViewBasketAndCheckout() {
		WebElement viewbasket = driver.findElementByXPath("//button[text()='View Basket & Checkout']");
		build.click(viewbasket).build().perform();
	}

	@Then("Click the close button")
	public void clickTheCloseButton() {
		driver.findElementByXPath("//button[@ng-click='dismiss()']").click(); 
	}

	@Then("close the browser")
	public void closeTheBrowser() {
		driver.quit();
	}

}
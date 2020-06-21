package steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class BigBasketWithExamples {
	ChromeOptions options;
	ChromeDriver driver;
	Actions build;
	JavascriptExecutor js;
	List<WebElement> addProducts;
	List<WebElement> notifyMeProducts;

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

	
	@Given("Mouse over Shop by Category")
	public void mouseOverShopByCategory() {
		System.out.println("driver" + driver );
		build = new Actions(driver);
		build.moveToElement(driver.findElementByXPath("//a[text()=' Shop by Category ']")).build().perform();
	}

	@Given("Go to Beverages and Fruit juices & Drinks")
	public void goToBeveragesAndFruitJuicesDrinks() throws InterruptedException {
		Thread.sleep(2000);
		build.moveToElement(driver.findElementByXPath("(//a[text()='Beverages'])[2]")).build().perform();
		build.moveToElement(driver.findElementByXPath("(//a[contains(text(),'Fruit Juices & Drinks')])[2]")).build()
				.perform();
		Thread.sleep(3000);
	}

	@Given("Click on Juices")
	public void clickOnJuices() throws InterruptedException {
		driver.findElementByXPath("(//a[text()='Juices'])[2]").click();
		Thread.sleep(2000);
	}

	@Given("Click (.*) and (.*) under Brand")
	public void clickTropicanaAndRealUnderBrand(String juice1 ,String juice2) throws InterruptedException {
		WebElement real = driver
				.findElementByXPath("(//span[text()='" +juice1 + "']//preceding-sibling::input[@type='checkbox'])[2]");
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", real);
		Thread.sleep(2000);
		WebElement tropicana = driver
				.findElementByXPath("(//span[text()='" +juice2 + "']//preceding-sibling::input[@type='checkbox'])[2]");
		js.executeScript("arguments[0].click();", tropicana);
		Thread.sleep(1000);
	}

	@Given("Check count of the products from each Brands and total count")
	public void checkCountOfTheProductsFromEachBrandsAndTotalCount() {
		List<WebElement> prodList = driver.findElementsByXPath("//div[@qa='product_name']");
		System.out.println("\nTotal number of Products listed: " + prodList.size());
		List<WebElement> tropicanaCount = driver.findElementsByXPath("//h6[text()='Tropicana']");
		List<WebElement> realCount = driver.findElementsByXPath("//h6[text()='Real']");
		System.out.println(tropicanaCount.size() + "" + realCount.size());
	}

	@Given("Check whether the products is availabe with Add button")
	public void checkWhetherTheProductsIsAvailabeWithAddButton() throws InterruptedException {
		notifyMeProducts = driver.findElementsByXPath("//button[text()='NOTIFY ME']");
		System.out.println("notifyMeCount" + notifyMeProducts.size());
		Thread.sleep(1000);
		addProducts = driver.findElementsByXPath("//button[@qa='add']");
		System.out.println("addCount" + addProducts.size());

	}

	@Given("Add the First listed available product")
	public void addTheFirstListedAvailableProduct() {
		if(addProducts.size()>0)
		{
		WebElement firstProduct = driver.findElementByXPath("(//button[@qa='add'])[1]");
		js.executeScript("arguments[0].click();", firstProduct);
		}

	}
	@Given("click Address")
	public void clickAddress() throws InterruptedException {
		WebElement changeLoc = driver.findElementByXPath("(//div[@class='col-md-6']/a)[1]");
		changeLoc.click();
		// js.executeScript("arguments[0].click();", changeLoc);
		Thread.sleep(1000);

	}

	@Given("Select (.*) as City, (.*) as Area  and click Continue")
	public void selectCHennaiAsCityAlandurChennaiAsAreaAndClickContinue(String city ,String area) {
		driver.findElementByXPath("(//input[@qa='areaInput'])[1]").sendKeys(area, Keys.ENTER);
		driver.findElementByXPath("//li[@class='ng-scope active']/a").click();
		// js.execute
		driver.findElementByXPath("//button[text()='Continue']").click();
	}


	
	@Given("Mouse over on My Basket print the product name. count and price.")
	public void mouseOverOnMyBasketPrintTheProductNameCountAndPrice() throws InterruptedException {
		WebElement basket = driver.findElementByXPath("//span[@class=\"basket-content\"]");
		build.moveToElement(basket).build().perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class=\"product-name\"]").getText();
		driver.findElementByXPath("//div[@ng-bind=\"cartItem.quantity_mrp\"]").getText();
	}

	@Then("Click View Basket and Checkout")
	public void clickViewBaskerAndCheckout() {
		WebElement viewbasket = driver.findElementByXPath("//button[text()='View Basket & Checkout']");
		build.click(viewbasket).build().perform();
		
	}

	@Then("Click the close button and close the browser")
	public void clickTheCloseButtonAndCloseTheBrowser() {
		driver.close();
	}
}

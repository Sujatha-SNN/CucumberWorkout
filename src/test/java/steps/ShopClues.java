package steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ShopClues {
	ChromeOptions options;
	ChromeDriver driver;
	Actions build;
	int k = 0;
	Map<Integer, String> shoeMap = new TreeMap<Integer, String>();
	Map<String, Integer> shoeMapIndexnProdName = new TreeMap<String, Integer>();
	Integer max;
	String pid;
	List<WebElement> productList;
	boolean flag = false;

	@Given("Set browser and other properties")
	public void setBrowserAndOtherProperties() {
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	}

	@Given("Open the chrome browser")
	public void openTheChromeBrowser() {
		driver = new ChromeDriver(options);
	}

	@Given("Load https:\\/\\/www.shopclues.com\\/")
	public void loadHttpsWwwShopcluesCom() {
		driver.get("https://www.shopclues.com/");
	}

	@Given("Maximize brower")
	public void maximizeBrower() {
		driver.manage().window().maximize();
	}

	@Given("Apply wait")
	public void applyWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Given("Mouseover on women and click Casual Shoes")
	public void mouseoverOnWomenAndClickCasualShoes() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElementByXPath("//button[@class='moe-chrome-style-notification-btn moe-btn-close moe-block-class']")
				.click();
		Actions build = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		build.moveToElement(driver.findElementByLinkText("WOMEN")).build().perform();
		Thread.sleep(2000);
		build.moveToElement(driver.findElementByXPath("(//a[text()='Casual Shoes'])[1]")).click().build().perform();

	}

	@Given("Select Color as Black")
	public void selectColorAsBlack() throws InterruptedException {
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowsList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowsList.get(1));
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(1000);
		WebElement black = driver.findElementByXPath("//label[@for='Black']");
		js.executeScript("arguments[0].click()", black);
		Thread.sleep(1000);
	}

	@Given("Check whether the product name contains the word black")
	public void checkWhetherTheProductNameContainsTheWordBlack() {
		productList = driver.findElementsByXPath("//span[@class='prod_name ']");
		System.out.println("products count" + productList.size());
		for (int i = 0; i < productList.size(); i++) {
			k = i + 1;
			WebElement product = driver.findElement(By.xpath(("(//span[@class='prod_name '])[" + k + "]")));
			if (product.getText().contains("black") || product.getText().contains("Black")) {
				flag = true;
				System.out.println("ALL the products are black in colour");
			} else
				flag = false;
		}

	}

	@Given("If so, add the product name and price in to Map")
	public void ifSoAddTheProductNameAndPriceInToMap() {
		if (flag) {
			for (int i = 0; i < productList.size(); i++) {
				k = i + 1;
				WebElement product = driver.findElement(By.xpath(("(//span[@class='prod_name '])[" + k + "]")));
				WebElement price = driver.findElementByXPath("(//span[@class='p_price'])[" + k + "]");
				String shoePrice = price.getText();
				shoeMap.put(new Integer(shoePrice.replaceAll("\\D", "")), product.getText());

			}
		}

	}

	@Given("Check Display the count of shoes which are more than {int} rupees")
	public void checkDisplayTheCountOfShoesWhichAreMoreThanRupees(Integer int1) throws InterruptedException {
		driver.findElementByXPath("//label[@for='Rs. 500 - Rs. 999']").click();
		String moreThan500Count = driver.findElementByXPath("//div[@class='product_found']/span").getText();
		System.out.println("moreThan500Count" + moreThan500Count.replaceAll("\\D", ""));
		Thread.sleep(1000);

	}

	@Given("Click the highest price of the 'shoe")
	public void clickTheHighestPriceOfTheShoe() {
		List<Integer> priceList = new ArrayList<Integer>();
		List<WebElement> above500ProductRows = driver
				.findElementsByXPath("//div[@id='product_list']/div[@class='row']");
		for (WebElement rows : above500ProductRows) {
			List<WebElement> products = rows.findElements(By.xpath("//div[@class='column col4']"));
			for (int i = 0; i < products.size(); i++) {
				k = i + 1;
				String price = driver.findElementByXPath("(//span[@class='p_price'])[" + k + "]").getText();
				String prodName = driver.findElementByXPath("(//span[@class='prod_name '])[" + k + "]").getText();
				shoeMap.put(Integer.parseInt(price.replaceAll("\\D", "")), prodName);
				shoeMapIndexnProdName.put(prodName, new Integer(k));
				priceList.add(Integer.parseInt(price.replaceAll("\\D", "")));
			}

		}
		Collections.sort(priceList);
		Integer max = Collections.max(priceList);
		System.out.println(shoeMap.get(max));
		int index = shoeMapIndexnProdName.get(shoeMap.get(max));
		System.out.println(index);
		driver.findElementByXPath("(//span[@class='prod_name '])[" + index + "]").click();
		pid = driver.findElementByXPath("(//div[@class='hover'])[" + index + "]").getAttribute("pid");
		System.out.println(pid);

	}

	@Given("Get the current page URL and check with the product ID")
	public void getTheCurrentPageURLAndCheckWithTheProductID() {

		Set<String> wHandlesforSelProd = driver.getWindowHandles();
		List<String> wHandlesforSelProdList = new ArrayList<String>(wHandlesforSelProd);
		driver.switchTo().window(wHandlesforSelProdList.get(2));
		String text = driver.findElementByXPath("//span[@class='pID']").getText();
		System.out.println("PID" + text.replaceAll("\\D", ""));
		if (text.replaceAll("\\D", "") == pid)
			System.out.println("Same Product is selected");
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
	}

	@Given("Copy the offercode")
	public void copyTheOffercode() {
		System.out.println("OFFER CODE" + driver.findElementById("first__B2G75"));
	}

	@Given("Select size, color and click ADD TO CART")
	public void selectSizeColorAndClickADDTOCART() throws InterruptedException {
		driver.findElementByXPath("(//span[@class='variant var '])[1]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//span[contains(@class,'variant var')])[2]").click();
		// driver.findElementById("add_cart").click();
		driver.findElement(By.id("add_cart")).click();
		Thread.sleep(2000);
	}

	@Given("Mouse over on Shopping cart and click View Cart")
	public void mouseOverOnShoppingCartAndClickViewCart() throws InterruptedException {
		build.moveToElement(driver.findElementByXPath("//a[@class='cart_ic']")).perform();
		Thread.sleep(1000);
		driver.findElementByXPath("//a[text()='View Cart']").click();
	}

	@Given("Type Pincode as {int} click Submit and Place Order")
	public void typePincodeAsClickSubmitAndPlaceOrder(Integer int1) {
		driver.findElementById("pin_code_popup").sendKeys("600016");
		driver.findElementById("get_pincode_popup").click();
	}

	@Then("Close the Browser")
	public void closeTheBrowser() {
		driver.close();
	}

}

package steps;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;

public class Honda extends BaseClass{
	Actions build;
	Map<String, String> activaDetails;
	Map<String, String> hondaDetails;
	WebDriverWait wait;
	@Given("Go to {string}")
	public void goTo(String string) {
		driver.get(string);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("Click on scooters and click dio")
	public void clickOnScootersAndClickDio() throws InterruptedException {
		driver.findElementByXPath("//button[@data-dismiss=\"modal\"]").click();
		driver.findElementById("link_Scooter").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//a[@href='/dio-BS-VI/']").click();
		

	}

	@Given("Click on Specifications and mouseover on Engine")
	public void clickOnSpecificationsAndMouseoverOnEngine() {
	driver.findElementByXPath("//a[text()='Specifications']").click();
	build = new Actions(driver);
	WebElement engine = driver.findElementByXPath("//a[text()='ENGINE']");
	wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.visibilityOf(engine));
	build.moveToElement(engine).build().perform();

	}

	@Given("Put all the details as key and value into Map")
	public void putAllTheDetailsAsKeyAndValueIntoMap() throws InterruptedException {
		 hondaDetails = new HashMap<String, String>();
		Thread.sleep(3000);
		for (int i = 11; i < 21; i++) {
			String key = driver.findElementByXPath("(//ul[@class='tab_content']/li)[" + i + "]/span[1]").getText();
			String value = driver.findElementByXPath("(//ul[@class='tab_content']/li)[" + i + "]/span[2]").getText();
			hondaDetails.put(key, value);
		}
		for (Entry<String, String> entry : hondaDetails.entrySet()) {
			System.out.println(entry.getKey() + "-- >" + entry.getValue());
		}

	}

	@Given("Go to Scooters and click Activa{int}")
	public void goToScootersAndClickActiva(Integer int1) throws InterruptedException {
		driver.findElementById("link_Scooter").click();
		Thread.sleep(1000);
		driver.findElementByXPath("// a[@href='/activa6g/']").click();
	}

	@Given("Put All its Engine Specification into another Map same as like dio")
	public void putAllItsEngineSpecificationIntoAnotherMapSameAsLikeDio() throws InterruptedException {driver.findElementByXPath("//a[text()='Specifications']").click();
	Thread.sleep(1000);
	WebElement activaEngine = driver.findElementByXPath("//a[text()='Engine']");
	wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.visibilityOf(activaEngine));
	build.moveToElement(activaEngine).build().perform();
	 activaDetails = new HashMap<String, String>();
	Thread.sleep(3000);
	for (int i = 11; i < 21; i++) {
		String key = driver.findElementByXPath("(//ul[@class='tab_content']/li)[" + i + "]/span[1]").getText();
		String value = driver.findElementByXPath("(//ul[@class='tab_content']/li)[" + i + "]/span[2]").getText();
		activaDetails.put(key, value);
	}
	for (Entry<String, String> entry : activaDetails.entrySet()) {
		System.out.println(entry.getKey() + "-- >" + entry.getValue());
	}
	Thread.sleep(2000);
	}

	@Given("Compare Dio and Activa Maps and print the different values of the samekeys.")
	public void compareDioAndActivaMapsAndPrintTheDifferentValuesOfTheSamekeys() {
		System.out.println(hondaDetails.values().equals(activaDetails.values()));
		System.out.println(hondaDetails.keySet().equals(activaDetails.keySet()));
		Set<String> activaKeySet = activaDetails.keySet();
		List<String> activaKeySetAsList = new ArrayList<String>(activaKeySet);
		for (int i = 0; i < hondaDetails.size(); i++) {

			if (hondaDetails.containsKey(activaKeySetAsList.get(i))) {
				if (!hondaDetails.get(activaKeySetAsList.get(i)).equals(activaDetails.get(activaKeySetAsList.get(i)))) {

					System.out.println(hondaDetails.get(activaKeySetAsList.get(i)));

					System.err.println(activaDetails.get(activaKeySetAsList.get(i)));
				}
			}
		}

	}

	@Given("Click FAQ from Menu and Click dio under Browse By Product")
	public void clickFAQFromMenuAndClickDioUnderBrowseByProduct() {
		driver.findElementByXPath("(//a[text()='FAQ'])[1]").click();
	}

	@Given("Click  Vehicle Price and Select scooter, Dio BS-VI from the dropdown and click submit")
	public void clickVehiclePriceAndSelectScooterDioBSVIFromTheDropdownAndClickSubmit() {
		driver.findElementByXPath("//a[text()='Dio BS-VI']").click();
		driver.findElementByXPath("//li[@id='li6']//i").click();
		driver.findElementById("submit6").click();
	}

	@Given("click the price link,  Go to the new Window and select the state, city")
	public void clickThePriceLinkGoToTheNewWindowAndSelectTheStateCity() {
		driver.findElementByXPath("//a[contains(text(),'Click here to know')]").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement stateDD = driver.findElementById("StateID");
		Select selSt = new Select(stateDD);
		selSt.selectByVisibleText("Tamil Nadu");

		WebElement cityDD = driver.findElementById("CityID");
		Select selCity = new Select(cityDD);
		selCity.selectByVisibleText("Chennai");
	}

	@Given("Print the price and model")
	public void printThePriceAndModel() {
			driver.findElementByXPath("//button[@onclick=\"ShowProductPrice()\"]").click();
			List<WebElement> elements = driver.findElementsByXPath("//table[@id='gvshow']/tbody/tr");
			for (int j = 0; j < elements.size(); j++) {
				WebElement element = elements.get(j);
				List<WebElement> cols = element.findElements(By.tagName("td"));
				for (int i = 0; i < cols.size(); i++) {
					System.out.println(cols.get(i).getText());
				}

			}


	}

	@Given("Click Product Enquiry")
	public void clickProductEnquiry() {
		driver.findElementByXPath("//a[text()='Enquiry']").click();
	}
	
	@Given("Fill fields except email")
	public void fillFieldsExceptEmail(io.cucumber.datatable.DataTable enquiryDataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	   //List<String> asList = enquiryDataTable..asList();
		//List<String> asList = enquiryDataTable.asList();
		List<String> asList = enquiryDataTable.asList();
		driver.findElementById("Name").sendKeys(asList.get(0));
		driver.findElementById("Email").sendKeys(asList.get(1));

		WebElement stateDD1 = driver.findElementById("StateID");
		Select selSt1 = new Select(stateDD1);
		selSt1.selectByVisibleText(asList.get(2));

		WebElement cityDD1 = driver.findElementById("CityID");
		Select selCity1 = new Select(cityDD1);
		selCity1.selectByVisibleText(asList.get(3));
		
		WebElement dealerDD = driver.findElementById("DealerID");
		Select selDealer = new Select(dealerDD);
		selDealer.selectByVisibleText(asList.get(4));
	   
	}

	@Given("click submit")
	public void clickSubmit() {
		driver.findElementById("submit").click();
		
	}

	@Given("Verify the error message under the mobile number field.")
	public void verifyTheErrorMessageUnderTheMobileNumberField() {
		String text = driver.findElementByXPath("//span[@data-valmsg-for=\"TitleID\"]").getText();
		System.out.println(text);
		assertEquals(text, "Please select title.");
	}

}

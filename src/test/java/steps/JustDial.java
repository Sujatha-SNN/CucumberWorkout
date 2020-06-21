package steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class JustDial extends BaseClass{
	String airport1,airport2,totalFare,fare;
	@Given("Cick on Air Tickets")
	public void cickOnAirTickets() {
		driver.findElementByXPath("//span[text()='Air Tickets']").click();
			
	}

	@Given("Type Chennai and choose (.*) as Leaving From")
	public void typeChennaiAndChooseFromAirPortAsLeavingFrom(String fromAirport) {
		driver.findElementByName("departurea").sendKeys("Chennai");
		driver.findElementByXPath("//li[text() = '"+ fromAirport + "']").click();
	}

	@Given("Type Toronto and select (.*) as Going To")
	public void typeTorontoAndSelectGoingTo(String toAirport) {
		driver.findElementByName("arrival").sendKeys("Delhi");
		driver.findElementByXPath("//li[text() ='"+ toAirport + "']").click();
	}

	@Given("Set Departure as {int}, July {int}")
	public void setDepartureAsJuly(Integer int1, Integer int2) throws InterruptedException {
		Thread.sleep(1000);
		WebElement date = driver.findElementByXPath("(//a[@class='ui-state-default'])[2]");
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", date);
	}

	@Given("Add Adult {int} , Children {int} click and Search")
	public void addAdultChildrenClickAndSearch(Integer int1, Integer int2) throws InterruptedException {
		WebElement adult = driver.findElementByXPath("//input[@id='_dAdultsCountSelectorVal']/following-sibling::span");
		adult.click();
		WebElement child = driver.findElementByXPath("(//span[@class='plus'])[2]");
		child.click();
		Thread.sleep(500);
		driver.findElementByXPath("//input[@value=\"SEARCH\"]").click();
		Thread.sleep(2000);
}

	@Given("Select Air Canada from multi-airline itineraries")
	public void selectAirCanadaFromMultiAirlineItineraries() {
		driver.findElementByXPath("(//input[@name='operating_airline'])[1]").click();
	}

	@Given("Click on Price to sort the result")
	public void clickOnPriceToSortTheResult() {
		driver.findElementById("priceSort").click();
		
}

	@Given("Click on +Details of first result under Price")
	public void clickOnDetailsOfFirstResultUnderPrice() {
		driver.findElementById("resTD1").click();
	}

	@Given("Capture the Flight Arrival times.")
	public void captureTheFlightArrivalTimes() {

		List<WebElement> rows = driver
				.findElementsByXPath("//div[@id='flightResults']/table[2]//tr[@class=\"resTextAlign1\"]");
		System.out.println(rows.size());
		for (int i = 0; i < rows.size(); i++) {
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			System.out.println(cols.size());
			for (int j = 0; j < cols.size(); j++) {

				System.out.println(cols.get(j).getText());

			}
		}
	}

	@Given("Capture the total price in a list and Click on Book")
	public void captureTheTotalPriceInAListAndClickOnBook() {
		fare = driver.findElementByXPath("(//ul[@class=\"detUL\"]/li)[4]/div/span").getText();
		System.out.println(fare);
		driver.findElementByXPath("(//a[@class='bookButton'])[1]").click();
	}

	@Given("Capture the Airport name base on the list of time")
	public void captureTheAirportNameBaseOnTheListOfTime() {
		 airport1 = driver.findElement(By.xpath("(//tr[@class='childText']//td)[2]")).getText();
		 airport2 = driver.findElementByXPath("(//tr[@class='childText']//td)[4]").getText();
		
	}

	@Then("Captur the total fare")
	public void capturTheTotalFare() {
		 totalFare = driver.findElementById("totalFare").getText();
		System.out.println("airport1" + airport1 + "airport2" + airport2 + "totalfare" + fare);
	}

	@Then("Print the difference amount from previous total price")
	public void printTheDifferenceAmountFromPreviousTotalPrice() {
		int diffiInFare = Integer.parseInt(totalFare) - Integer.parseInt(fare);
		System.out.println("Diff in Fare"+ diffiInFare);
	}

	
	
}

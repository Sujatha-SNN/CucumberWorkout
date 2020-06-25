package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class BikeWale {
	ChromeDriver driver;
	
	Map<String,String> bikeRating ;
	@Given("Go to https:\\/\\/www.bikewale.com\\/")
	public void goToHttpsWwwBikewaleCom() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		dc.merge(ops);
		 driver = new ChromeDriver(ops);
		driver.get("https://www.bikewale.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Given("Go to menu and click new bikes")
	public void goToMenuAndClickNewBikes() throws InterruptedException {
		driver.findElementByXPath("//span[@class='navbarBtn nav-icon']").click();
		Thread.sleep(500);
		
	}

	@Given("Click New Bikes Then compare bikes")
	public void clickNewBikesThenCompareBikes() {
		driver.findElementByXPath("//span[text()='New Bikes']/following-sibling::span").click();
		driver.findElementByXPath("//a[text()='Compare Bikes']").click();
	}

	@Given("Add first bike as (.*) and model as (.*)")
	public void addFirstBikeAsBikeAndModelAsModel(String bikeone, String modelone) {
		driver.findElementByXPath("(//span[@class='add-icon'])[1]").click();
		driver.findElementByXPath("//div[@class=\"chosen-container chosen-container-single\"]").click();
		driver.findElementByXPath("(//input[@type=\"text\"])[2]").sendKeys(bikeone, Keys.ENTER);
		driver.findElementByXPath("(//div[@class='chosen-container chosen-container-single'])" + "").click();
		driver.findElementByXPath("(//input[@type=\"text\"])[3]").sendKeys(modelone, Keys.ENTER);
		driver.findElementByXPath("(//div[contains(@class,'chosen-container chosen-container-single')])[3]").click();
		driver.findElementByXPath("(//input[@type=\"text\"])[4]").sendKeys("Dual Channel ABS - BS IV", Keys.ENTER);

	}

	@Given("Add second bike (.*) model as (.*) and version Dual Channel ABS - BS VI")
	public void addSecondBikeBikeModelAsModelAndVersionDualChannelABSBSVI(String biketwo, String modeltwo) {
		driver.findElementByXPath("(//span[@class='add-icon'])[2]").click();
		driver.findElementByXPath("(//div[@class='chosen-container chosen-container-single'])[4]").click();
		driver.findElementByXPath("(//input[@type=\"text\"])[5]").sendKeys(biketwo, Keys.ENTER);
		driver.findElementByXPath("(//div[contains(@class,'chosen-container chosen-container-single')])[5]").click();
		driver.findElementByXPath("(//input[@type=\"text\"])[6]").sendKeys(modeltwo, Keys.ENTER);
		
		}

	@Given("Add bike brand (.*) model as (.*)")
	public void addBikeBrandBikeModelAsModel(String bikethree, String modelthree) throws InterruptedException {
		driver.findElementByXPath("(//span[@class='add-icon'])[3]").click();
		driver.findElementByXPath("(//div[@class='chosen-container chosen-container-single'])[7]").click();
		driver.findElementByXPath("(//input[@type=\"text\"])[8]").sendKeys(bikethree, Keys.ENTER);
		driver.findElementByXPath("(//div[contains(@class,'chosen-container chosen-container-single')])[8]").click();
		driver.findElementByXPath("(//input[@type=\"text\"])[9]").sendKeys(modelthree, Keys.ENTER);
		Thread.sleep(1000);
		

	}

	@Given("click compare")
	public void clickCompare() {
		driver.findElementById("btnCompare").click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Then("Print the max rating")
	public void printTheMaxRating() {
		/*done on the assumption,rating will be unique
		 * System.out.println(bikeRating);
		List<String> keyList = new ArrayList<String>(bikeRating.keySet());
		System.out.println(bikeRating.get(keyList.get(keyList.size()-1)));*/
		
		System.out.println(bikeRating);//before sorting //bikerating is map -use urs
		Map<String, String> sortByValues = sortByValues(bikeRating);
		Collection<String> values = sortByValues.values();
		List<String> list= new ArrayList<String>(values);
		String max = Collections.max(list);
		System.out.println(max);
		System.out.println(sortByValues + "sortByValues" + values);
		for (Entry<String, String> entry : sortByValues.entrySet()) {
			if(entry.getValue().equals(max))
				System.out.println(entry);
		}
	}
	
	public static <K extends Comparable,V extends Comparable> Map<K,V> sortByValues(Map<K,V> map){
        List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {

            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
     
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
        for(Map.Entry<K,V> entry: entries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
     
        return sortedMap;
    }

}

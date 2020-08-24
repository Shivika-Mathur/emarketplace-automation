package thoughtworks.interview.steps;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefination {
	
	private WebDriver driver;
	
	public static Logger log = Logger.getLogger(StepDefination.class);
	
	String baseUrl = "http://automationpractice.com/index.php";
	
	@Given("^I am on the login page of the application$")
	public void i_am_on_the_login_page_of_the_application() throws Throwable {
	    
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		log.info("Website is launched");
		driver.findElement(By.className("login")).click();
		
	    
	}

	@When("^I enter valid credentials$")
	public void i_enter_valid_credentials() throws Throwable {
		
		log.info("User is on the login page");
		driver.findElement(By.id("email")).sendKeys("testautomation@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("test123");
		driver.findElement(By.xpath("//p[@class='submit']//span[1]")).click();
	    
	}

	@Then("^I should be taken to the website homepage$")
	public void i_should_be_taken_to_the_website_homepage() throws Throwable {
	    
		String result = driver.findElement(By.xpath("//a[@class='logout']")).getText();
		
		if(result.equals("Sign out")) {
			
			log.info("Login Successful");
		} else {
			
			log.info("Login failed");
		}
	    
	}

	@Then("^I am able to select the items$")
	public void i_am_able_to_select_the_items() throws Throwable {
	    
	    WebDriverWait wait = new WebDriverWait(driver,20);
	    
	    Actions act = new Actions(driver);
	    
	    log.info("User is on the home page");
	    
	    driver.findElement(By.id("search_query_top")).sendKeys("dresses");
	    	    
	    driver.findElement(By.name("submit_search")).click();
	    
	    log.info("Item is searched");
	    
	    Thread.sleep(5000);
	    
	    WebElement searchProduct = driver.findElement(By.xpath("//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line first-item-of-tablet-line first-item-of-mobile-line']//span[contains(text(),'Add to cart')]"));
	    
	    Boolean search = searchProduct.isDisplayed();
	    
	    if(search) {
	    	
	    	searchProduct.click();
	    } else {
	    	
	    	WebElement image = driver.findElement(By.xpath("//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line first-item-of-tablet-line first-item-of-mobile-line']//img[@class='replace-2x img-responsive']"));
	    	act.moveToElement(image).build().perform();
	    	searchProduct.click();
	    }
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[2]/ul[1]/li[1]/div[1]/div[2]/div[2]/a[1]/span[1]"))).click();
	    log.info("User clicked on Add to cart");
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[4]/div[1]/div[2]/div[4]/a[1]/span[1]"))).click();
	    log.info("Proceed to checkout");
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]"))).click();
	    log.info("User is on the Address selection page");	
	}

	@Then("^close the browser$")
	public void close_the_browser() throws Throwable {
		log.info("Browser closed");
	    driver.quit();
	}
	
}

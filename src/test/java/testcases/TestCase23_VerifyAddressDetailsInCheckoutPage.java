package testcases;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCase23_VerifyAddressDetailsInCheckoutPage {
	private WebDriver driver;

	@DataProvider(name = "dpCreateUser")
	public Object[][] createUserGetData() {
		return new Object[][] { { "Penny Jones", "penny.jones@example.com", "Mrs", "Pass@123", "21", "March", "1986",
				"Yes", "Yes", "Penny", "Jones", "The PennyJones company", "29901 Elmore Shores", "Apt. 923",
				"United States", "Missouri", "Weissnatville", "205055", "19453439976" }, };
	}

	@Test(dataProvider = "dpCreateUser")
	public void testToVerifyAddressDetailsInCheckoutPage(String str_Name, String str_Email, String str_Title,
			String str_Password, String str_Days, String str_Months, String str_Years, String str_Newsletter,
			String str_Special, String str_FirstName, String str_LastName, String str_Company, String str_Address1,
			String str_Address2, String str_Country, String str_State, String str_City, String str_Zipcode,
			String str_MobileNumber)  {
		
		
		driver = new ChromeDriver();
		System.out.println("1. Launch browser");
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://automationexercise.com/");
		System.out.println("2. Navigate to url 'http://automationexercise.com'");
		
		// ***** HOME PAGE *****
		Assert.assertEquals(driver.getTitle(), "Automation Exercise");
		System.out.println("3. Verified that home page is visible successfully");
		driver.findElement(By.linkText("Signup / Login")).click();
		System.out.println("4. Click on 'Signup / Login' button");
		
		// ***** SIGN UP / LOGIN  PAGE *****
		Assert.assertEquals(driver.findElement(By.xpath("//section[@id='form']/div/div/div[3]/div/h2")).getText(), "New User Signup!");
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys(str_Name);
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.xpath("//div[@class='signup-form']//input[@type='email']")).sendKeys(str_Email);
		driver.findElement(By.xpath("//button[text()='Signup']")).click();
		
		// ***** SIGN UP PAGE *****
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='login-form']//h2/b")).getText(), "ENTER ACCOUNT INFORMATION");
		if (str_Title.equalsIgnoreCase("Mr")) {
			driver.findElement(By.id("id_gender1")).click();
		} else if (str_Title.equalsIgnoreCase("Mrs")) {
			driver.findElement(By.id("id_gender2")).click();
		}
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(str_Password);
		new Select(driver.findElement(By.id("days"))).selectByVisibleText(str_Days);
		new Select(driver.findElement(By.id("months"))).selectByVisibleText(str_Months);
		new Select(driver.findElement(By.id("years"))).selectByVisibleText(str_Years);
		if (str_Newsletter.equalsIgnoreCase("Yes")) {
			driver.findElement(By.id("newsletter")).click();
		}
		if (str_Special.equalsIgnoreCase("Yes")) {
			driver.findElement(By.id("optin")).click();
		}
		driver.findElement(By.id("first_name")).clear();
		driver.findElement(By.id("first_name")).sendKeys(str_FirstName);
		driver.findElement(By.id("last_name")).clear();
		driver.findElement(By.id("last_name")).sendKeys(str_LastName);
		driver.findElement(By.id("company")).clear();
		driver.findElement(By.id("company")).sendKeys(str_Company);
		driver.findElement(By.id("address1")).clear();
		driver.findElement(By.id("address1")).sendKeys(str_Address1);
		driver.findElement(By.id("address2")).clear();
		driver.findElement(By.id("address2")).sendKeys(str_Address2);
		new Select(driver.findElement(By.id("country"))).selectByVisibleText(str_Country);
		driver.findElement(By.id("state")).clear();
		driver.findElement(By.id("state")).sendKeys(str_State);
		driver.findElement(By.id("city")).clear();
		driver.findElement(By.id("city")).sendKeys(str_City);
		driver.findElement(By.id("zipcode")).clear();
		driver.findElement(By.id("zipcode")).sendKeys(str_Zipcode);
		driver.findElement(By.id("mobile_number")).clear();
		driver.findElement(By.id("mobile_number")).sendKeys(str_MobileNumber);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("5. Fill all details in Signup and create account");
		Assert.assertEquals(driver.findElement(By.xpath("//section[@id='form']//h2/b")).getText(), "ACCOUNT CREATED!");
		Assert.assertTrue(driver.findElement(By.xpath("//section[@id='form']//h2/b[text()='Account Created!']")).isDisplayed());
		driver.findElement(By.linkText("Continue")).click();
		System.out.println("6. Verify 'ACCOUNT CREATED!' and click 'Continue' button");

		// ***** HOME PAGE *****
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='header']//a/b")).getText(), str_Name);
		System.out.println("7. Verify that 'Logged in as username' is visible");
		driver.findElement(By.partialLinkText("Products")).click();
		
		// ***** ALL PRODUCTS PAGE *****
		assertEquals(driver.getTitle(), "Automation Exercise - All Products");
		assertEquals(driver.findElement(By.xpath("//div[@class='features_items']//h2[@class='title text-center']")).getText(), "ALL PRODUCTS");
				
		new Actions(driver).moveToElement(driver.findElement(By.xpath("//div[contains(@class,'productinfo')]/p[text()='Blue Top']/following-sibling::a[contains(@class,'add-to-cart')]"))).build().perform();
		driver.findElement(By.xpath("//div[@class='overlay-content']/p[text()='Blue Top']//following-sibling::a[contains(@class,'add-to-cart')]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Continue Shopping')]")))).click();
		new Actions(driver).moveToElement(driver.findElement(By.xpath("//div[contains(@class,'productinfo')]/p[text()='Men Tshirt']/following-sibling::a[contains(@class,'add-to-cart')]"))).build().perform();
		driver.findElement(By.xpath("//div[@class='overlay-content']/p[text()='Men Tshirt']//following-sibling::a[contains(@class,'add-to-cart')]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(text(),'Continue Shopping')]")))).click();
		System.out.println("8. Add products to cart");
		driver.findElement(By.partialLinkText("Cart")).click();
		System.out.println("9. Click 'Cart' button");
		
		// ***** SHOPPING CART PAGE *****
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='cart_items']//li[text()='Shopping Cart']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='cart_items']//li[text()='Shopping Cart']")).getText(), "Shopping Cart");
		System.out.println("10. Verify that cart page is displayed");
		driver.findElement(By.linkText("Proceed To Checkout")).click();
		System.out.println("11. Click Proceed To Checkout");
		
		// ***** CHECKOUT PAGE *****
		assertEquals(driver.findElement(By.xpath("//li[@class='address_firstname address_lastname']")).getText(),str_Title + ". " + str_Name);
		assertEquals(driver.findElement(By.xpath("//*[@id='address_delivery']/li[3]")).getText(), str_Company);
		assertEquals(driver.findElement(By.xpath("//*[@id='address_delivery']/li[4]")).getText(), str_Address1);
		assertEquals(driver.findElement(By.xpath("//*[@id='address_delivery']/li[5]")).getText(), str_Address2);
		assertEquals(driver.findElement(By.xpath("//*[@id='address_delivery']/li[6]")).getText(), str_City + " " + str_State + " " + str_Zipcode);
		assertEquals(driver.findElement(By.xpath("//*[@id='address_delivery']/li[7]")).getText(), str_Country);
		assertEquals(driver.findElement(By.xpath("//*[@id='address_delivery']/li[8]")).getText(), str_MobileNumber);
		System.out.println("12. Verify that the delivery address is same address filled at the time registration of account");
		assertEquals(driver .findElement(By.xpath("//ul[@id='address_invoice']/li[@class='address_firstname address_lastname']")).getText(), str_Title + ". " + str_Name);
		assertEquals(driver.findElement(By.xpath("//ul[@id='address_invoice']/li[3]")).getText(), str_Company);
		assertEquals(driver.findElement(By.xpath("//ul[@id='address_invoice']/li[4]")).getText(), str_Address1);
		assertEquals(driver.findElement(By.xpath("//ul[@id='address_invoice']/li[5]")).getText(), str_Address2);
		assertEquals(driver.findElement(By.xpath("//ul[@id='address_invoice']/li[6]")).getText(), str_City + " " + str_State + " " + str_Zipcode);
		assertEquals(driver.findElement(By.xpath("//ul[@id='address_invoice']/li[7]")).getText(), str_Country);
		assertEquals(driver.findElement(By.xpath("//ul[@id='address_invoice']/li[8]")).getText(), str_MobileNumber);
		System.out.println("13. Verify that the billing address is same address filled at the time registration of account");

		
		// ***** HOME PAGE *****
		driver.findElement(By.linkText("Delete Account")).click();
		System.out.println("14. Click 'Delete Account' button");
		
		// ***** ACCOUNT DELETED PAGE *****
		Assert.assertEquals(driver.findElement(By.xpath("//section[@id='form']//h2/b")).getText(), "ACCOUNT DELETED!");
		Assert.assertTrue(driver.findElement(By.xpath("//section[@id='form']//h2/b[text()='Account Deleted!']")).isDisplayed());
		driver.findElement(By.linkText("Continue")).click();
		System.out.println("15. Verify 'ACCOUNT DELETED!' and click 'Continue' button");
		// ***** HOME PAGE *****
		Assert.assertEquals(driver.getTitle(), "Automation Exercise");
		
		driver.quit();

	}
	
}

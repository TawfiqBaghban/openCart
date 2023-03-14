package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage 
{
//		We have to create another constructor in here too, but the super keyword will invoke the parent class constructor.
	public HomePage (WebDriver driver)
	{
		super(driver);
	}
	
//		Captured locators and assign them to variables.
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement linkMy_Account;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement link_Registration;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement login_Link;
	
	
//		Actions method should be created now.
	
	public void clickMyAccount()
	{
		linkMy_Account.click();
	}
	
	public void clickRegister()
	{
		link_Registration.click();
	}
	
	public void clickLogin()
	{
		login_Link.click();
	}
	
	
	
	
}

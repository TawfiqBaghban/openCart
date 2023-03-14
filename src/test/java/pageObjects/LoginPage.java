package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage (WebDriver driver)
	{
		super (driver);
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement email_Link;
	
	@FindBy(css = "#input-password")
	WebElement password_Link;
	
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement login_btn;
	
	
	public void setEmail(String email)
	{
		email_Link.sendKeys(email);
	}
	
	public void setPassword (String password)
	{
		password_Link.sendKeys(password);
	}
	
	public void clickLogin()
	{
		login_btn.click();
	}
}

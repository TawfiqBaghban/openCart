package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterAccountPage extends BasePage
{
//	We have to create another constructor in here too, but the super keyword will invoke the parent class constructor.
	public RegisterAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
//		WebElement --> locators and names
	
	@FindBy(name = "firstname")
	WebElement txt_firstname;
	
	@FindBy(name = "lastname")
	WebElement txt_lastname;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(css = "#input-password")
	WebElement txt_password;
	
	@FindBy(name = "agree")
	WebElement check_policy;
	
	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement btn_continue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement txt_confirmation;
	
//		Actions methods	
	public void setFirstName(String firstname)
	{
		txt_firstname.sendKeys(firstname);
	}
	
	public void setLastName(String lastname)
	{
		txt_lastname.sendKeys(lastname);
	}
	
	public void setEmail(String email)
	{
		txt_email.sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		txt_password.sendKeys(password);
	}
	
	public void clickPolicy()
	{
		check_policy.click();
	}
	
	public void clickContniue()
	{
		btn_continue.click();
	}
	
	
	public String getConfrimationMsg() {
		try {
		return(txt_confirmation.getText());
	} catch (Exception e) {
		return(e.getMessage());
	}


	}
}

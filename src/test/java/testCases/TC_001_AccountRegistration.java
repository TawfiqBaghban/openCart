package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterAccountPage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass
{
//		TC_001_AccountRegistration extends the parent class (BaseClass), to invoke @BeforeClass, @AfterClass and other methods.
	
	@Test(groups = {"Regression", "Master"})
	void test_Account_Registration() throws InterruptedException
//	To use the page object classes, we create objects of them and then call their methods.
	{ 
		try
		{
			
		logger.info("test_Account_Registration started .......");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("**** Clicked on my account ****");
		hp.clickRegister();
		logger.info("**** Clicked on account registration ****");
		
//		Creating object of RegisterAccountPage class (Page Object Class).
		RegisterAccountPage regPage = new RegisterAccountPage(driver);
		logger.info("**** Providing registration information ****");
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLastName(randomString().toUpperCase());
		regPage.setEmail(randomString()+"@gmail.com");
		
//		If there is a confirm password criteria, so we have to use randomAlpha_Numeric() method to generate random passwords
//		then we assign it to a variable and then pass the variable to both password input boxes.
		String password = randomAlpha_Numeric(); 
		regPage.setPassword(password);
//		regPage.confPassword(password);
		Thread.sleep(5000);
		regPage.clickPolicy();
		logger.info("**** Clicked on check policy ****");
		regPage.clickContniue();
		logger.info("**** Clicked on continue button ****");
		
		String Conf_Status = regPage.getConfrimationMsg();
		Assert.assertEquals(Conf_Status, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test failed ........");
			Assert.fail();
		}
		logger.info("test_Account_Registration test is finished");
	}
}

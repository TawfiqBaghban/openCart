package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{

//		The following test is using valid credentials.
	
	@Test(groups = {"Sanity", "Master"})
	public void test_Login()
	{
		try
		{
		logger.info("***** Starting TC_002_LoginTest *****");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		logger.info("***** Providing Login details *****");
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(rb.getString("email2"));
		lp.setPassword(rb.getString("password2"));
		lp.clickLogin();
		
		logger.info("***** Validation *****");
		MyAccountPage maP = new MyAccountPage(driver);
		boolean tagetPage = maP.isMyAccountPageExists();
		Assert.assertEquals(tagetPage, true);
		}
		catch (Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finished TC_002_LoginTest ******");
	}
}

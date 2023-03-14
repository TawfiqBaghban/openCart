package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass{

/*		If the DataProvider is in the same class, then this part --> @Test(dataProvider="LoginData") is enough, but 
 		if the DataProvider is in another class, we have to put another parameter as follow.
 		The DataProvider is reading three parameters from excel sheet and also passing three parameters, so 
 		we have to capture them in three different variables.
 */
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void test_LoginDDT(String email, String password, String exp)
//		The DataProvider is passing two parameters, so we capture them in String variables.
	{
		logger.info("***** Starting TC_003_LoginDDT test *****");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetpage = macc.isMyAccountPageExists();// this method is created MyAccountPage

		if (exp.equals("Valid")) {
			if (targetpage == true) {	
				macc.clickLogout();		
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}

		if (exp.equals("Invalid")) {
			if (targetpage == true) {
				MyAccountPage myaccpage = new MyAccountPage(driver);
				myaccpage.clickLogout();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}

		} catch (Exception e) {
				Assert.fail();
		}

		logger.info("***** Finished TC_003_LoginDataDrivenTest *****");
	}
}

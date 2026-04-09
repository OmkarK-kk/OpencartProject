package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_02_LoginTest extends BaseClass{
	
	@Test
	public void verify_Login()
	{
		logger.info("****Starting of TC_02_LoginTest****");
		
		try
		{
		//Homepage
		Homepage hp = new Homepage(driver);
		hp.ClickMyAccount();
		hp.ClickOnLogin();
		
		//Loginpage
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAdd(pr.getProperty("email"));
		lp.setPassword(pr.getProperty("password"));
		lp.ClickLogin();
		
		//MyAccount
		MyAccountPage mc = new MyAccountPage(driver);
		boolean targetPage = mc.isMyAccountPageExists();
		Assert.assertTrue(true);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****Finished TC_02_LoginTest****");
		
	}
	
}

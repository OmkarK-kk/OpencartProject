package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_02_LoginTest extends BaseClass{
	
	@Test(groups= {"Regression", "Master"})
	public void verify_login()
	{
		logger.info("******Starting of TC_02_LoginTest ******");
		
		try
		{
		Homepage hp = new Homepage(driver);
		hp.ClickMyAccount();
		hp.ClickOnLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAdd(pr.getProperty("email"));
		lp.setPassword(pr.getProperty("password"));
		lp.ClickLogin();
		
		Thread.sleep(4000);
		
		MyAccountPage mp = new MyAccountPage(driver);
		boolean targetpg = mp.isMyAccountPageExists();
		
		//Assert.assertEquals(target, true, "Test Failed");
		Assert.assertTrue(targetpg);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("******Finished of TC_02_LoginTest ******");
	}

}

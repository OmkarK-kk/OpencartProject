package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_04_LoginDataDrivenTest extends BaseClass{
	
	@Test(dataProvider="Logindata", dataProviderClass = DataProviders.class)
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("****Staring of TC_04_LoginDataDrivenTest****");
		
		try
		{
		//Homepage
		Homepage hp = new Homepage(driver);
		hp.ClickMyAccount();
		hp.ClickOnLogin();
			
		//Loginpage
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAdd(email);
		lp.setPassword(pwd);
		lp.ClickLogin();
			
		//MyAccount
		MyAccountPage mc = new MyAccountPage(driver);
		boolean targetPage = mc.isMyAccountPageExists();
			
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				mc.ClickOnLogOut();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
	
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				mc.ClickOnLogOut();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
			
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****Finished of TC_04_LoginDataDrivenTest****");
	}
	
}
	

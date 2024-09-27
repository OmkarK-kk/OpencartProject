package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_03_LoginTestDataDriven extends BaseClass {
	
	@Test(dataProvider="Logindata", dataProviderClass = DataProviders.class)  //getting data providers from different class
	public void verify_LoginDDT(String email, String pass, String exp)
	{
		logger.info("******Starting of TC_03_LoginTest ******");
		
		try {
		//Home Page
		Homepage hp = new Homepage(driver);
		hp.ClickMyAccount();
		hp.ClickOnLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmailAdd(pr.getProperty("email"));
		lp.setPassword(pr.getProperty("password"));
		lp.ClickLogin();
		
		
		MyAccountPage mp = new MyAccountPage(driver);
		boolean targetpg = mp.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetpg==true)
				{
					mp.ClickOnLogOut();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetpg==true)
			{
				mp.ClickOnLogOut();
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
		
		logger.info("****** Finished of TC_03_LoginTest ******");
		
	}

}

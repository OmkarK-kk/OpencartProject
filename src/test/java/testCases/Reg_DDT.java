package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class Reg_DDT extends BaseClass{
	
	@Test(dataProvider="Regdata", dataProviderClass = DataProviders.class)
	public void verify_DDTReg(String fname, String lname, String email)
	{
	
	logger.info("****Starting of TC_01_Registration****");
	
	try
	{
	Homepage hp = new Homepage(driver);
	hp.ClickMyAccount();
	logger.info("Clicked on My Account link");
	
	hp.ClickOnRegister();
	logger.info("Clicked on Register link");
	
	RegistrationPage reg = new RegistrationPage(driver);
	Thread.sleep(5000);
	
	logger.info("Providing customer details");
	
	reg.setFirstName(fname);
	Thread.sleep(5000);
	reg.setLastName(lname);
	Thread.sleep(5000);
	reg.setEmail(email);
	
	Thread.sleep(5000);
	reg.setTelephone("761899199");
	reg.setPassword("Okok@819");
	reg.setConfPass("Okok@819");
	reg.ClickRadioSubscribe();
	reg.ClickOnAgree();
	reg.ClickOnContinue();
	
	logger.info("Validating expected message!!");
	String cfmMsg = reg.getSuccMessage();
	
	if(cfmMsg.equals("Your Account Has Been Created!"))
	{
		Assert.assertTrue(true);
	}
	else
	{
		logger.error("Test failed..");
		logger.debug("Debug logs..");
		Assert.assertTrue(false);
	}
	}
	catch(Exception e)
	{
		Assert.fail();
	}
	
	logger.info("****Finished TC_01_Registration****");

}
}

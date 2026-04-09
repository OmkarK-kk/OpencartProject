package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC_01_Registration extends BaseClass {

	@Test
	public void verify_AccountRegistration()
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
		
		logger.info("Providing customer details");
		
		reg.setFirstName("omkar");
		reg.setLastName("OKOK");
		reg.setEmail("okok9160@gmail.com");
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

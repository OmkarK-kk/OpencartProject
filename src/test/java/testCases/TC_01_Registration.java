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

	@Test(groups={"Sanity","Master"})
	public void Verify_registration() throws InterruptedException
	{
		logger.info("******* Starting TC_01_Registration ********");
		
		try
		{
		Homepage hm = new Homepage(driver);
		hm.ClickMyAccount();
		logger.info("******** Click on My Account ********");
		
		hm.ClickOnRegister();
		logger.info("******** Click on Register ********");
		
		RegistrationPage rp = new RegistrationPage(driver);
		
		logger.info("******** Entering Customer details ********");
		
		rp.setFirstName(RandomInString());
		rp.setLastName(RandomInString());
		rp.setEmail(RandomInString() + "@gmail.com");
		rp.setTelephone(RandomNumber());
		
		String password = RandomAlphanumeric(); 
		rp.setPassword(password);
		rp.setConfPass(password);
		
		Thread.sleep(5000);
		
		rp.ClickRadioSubscribe();
		rp.ClickOnAgree();
		rp.ClickOnContinue();
		
		logger.info("******** Validating expected message ********");
		String cfmsg = rp.getSuccMessage();
		
		if(cfmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed");
			logger.debug("Debug logs....");
			Assert.assertTrue(false);
		}
		
	}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("******** Finished TC_01_Registration **********");
	}
	
	
	
}

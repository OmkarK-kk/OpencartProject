package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {
	
	//Constructor
	public Homepage(WebDriver driver)
	{
		super(driver);
	}
	
	
	//Locators
	@FindBy(xpath="(//span[@class='hidden-xs hidden-sm hidden-md'])[3]")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//a[@href='https://tutorialsninja.com/demo/index.php?route=account/register']")
	WebElement lnkRegister;
	
	@FindBy(xpath= "//a[@href='https://tutorialsninja.com/demo/index.php?route=account/login']")
	WebElement lnkLogin;
	
	
	public void ClickMyAccount()
	{
		lnkMyAccount.click();
	}
	
	public void ClickOnRegister()
	{
		lnkRegister.click();
	}
	
	public void ClickOnLogin()
	{
		lnkLogin.click();
	}
	
}

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	//Constructor
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	//xpaths
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath= "//input[@id='input-password']")
	WebElement txtPass;
	
	@FindBy(xpath ="//input[@value='Login']")
	WebElement btnLogin;
	
	//Methods
	public void setEmailAdd(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String pass)
	{
		txtPass.sendKeys(pass);
	}
	
	public void ClickLogin()
	{
		btnLogin.click();
	}

}

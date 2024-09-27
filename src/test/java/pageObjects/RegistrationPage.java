package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
	
	
	//Constructor
	public RegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators
	@FindBy(xpath= "//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath= "//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath= "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath= "//input[@id='input-telephone']")
	WebElement txtTelePhone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPass;
	
	@FindBy(xpath= "//input[@id='input-confirm']")
	WebElement txtConfPass;
	
	@FindBy(xpath= "(//input[@name='newsletter'])[1]")
	WebElement radioSubscribe;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkAgree;
	
	@FindBy(xpath= "//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//div[@id=\"content\"]/h1")
	WebElement successMsg;
	
	//Methods
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tel)
	{
		txtTelePhone.sendKeys(tel);
	}
	
	public void setPassword(String pass)
	{
		txtPass.sendKeys(pass);
	}
	
	public void setConfPass(String confPass)
	{
		txtConfPass.sendKeys(confPass);
	}
	
	public void ClickRadioSubscribe()
	{
		radioSubscribe.click();
	}
	
	public void ClickOnAgree()
	{
		chkAgree.click();
	}
	
	public void ClickOnContinue()
	{
		btnContinue.click();
	}

	public String getSuccMessage()
	{
		try
		{
			return(successMsg.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage()) ;
		}
	
	}
}

package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;  //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;   //Log4j
	public Properties pr;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup( String os, String br) throws IOException
	{
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		pr = new Properties();
		pr.load(file);
		
		logger = LogManager.getLogger(this.getClass());  //Log4j
		
		if(pr.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome" : capabilities.setBrowserName("chrome");
			break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge");
			break;
			default : System.out.println("No matching browser");
			return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		
		if(pr.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			 case "chrome" : driver = new ChromeDriver();
			 break; 
			 case "edge" : driver = new EdgeDriver();
			 break; 
			 case "firefox" : driver = new FirefoxDriver(); 
			 break; 
			 default : System.out.println("Invalid browser selection..."); 
			 return; 
			 }
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(pr.getProperty("appURL"));
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.close();
	}
	
	public String RandomInString()
	{
		String getRandomString = RandomStringUtils.randomAlphabetic(5);
		return getRandomString;
	}
	
	public String RandomNumber()
	{
		String getRandomNum = RandomStringUtils.randomNumeric(5);
		return getRandomNum;
	}
	
	public String RandomAlphanumeric()
	{
		String getRandomString = RandomStringUtils.randomAlphabetic(8);
		String getRandomNum = RandomStringUtils.randomNumeric(7);
		return(getRandomString + "@"+ getRandomNum);
	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timestamp = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+ "\\screenshots\\" + tname + "_"+ timestamp;
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}

	
}

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties pr;
	
	@BeforeClass
	@Parameters({"os","browser"})
	public void setUp(String os, String br) throws IOException
	{
		logger = LogManager.getLogger(this.getClass());
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		pr = new Properties();
		pr.load(file);
		
		switch(br.toLowerCase())
		{
		case "chrome": driver= new ChromeDriver();
		break;
		case "edge": driver = new EdgeDriver();
		break;
		case "firefox": driver = new FirefoxDriver();
		break;
		default : System.out.println("Invalid browser selection..");
		return;
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("https://tutorialsninja.com/demo/index.php");
		driver.get(pr.getProperty("appURL")); //Reading value from properties file
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	@AfterMethod
	public String captureScreen(String tname) throws IOException
	{
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot Screenshot = (TakesScreenshot) driver;
		File sourceFile = Screenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"/screenshots/"+ tname + "_" + timestamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	
}
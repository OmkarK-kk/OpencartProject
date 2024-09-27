package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss");  // time stamp
		Date dt = new Date();
		String currentdatetimestamp = df.format(dt);
		
	//	String timestamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());  // time stamp
		
		repName = "Test-Report-" + currentdatetimestamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); //specify location of the report 
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");  //Title of report
		sparkReporter.config().setReportName("Opencart Functional Report");  //name of the report
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
		public void onTestSuccess(ITestResult result)
		{
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups()); //to display groups in report
			test.log(Status.PASS, result.getName()+ " get successfully executed");
		}
		
		public void onTestFailure(ITestResult result)
		{
			test= extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups()); //to display groups in report
			
			test.log(Status.FAIL, result.getName()+ " got failed");
			test.log(Status.INFO, result.getThrowable().getMessage());
			
			try {
				String imgPath = new BaseClass().captureScreen(result.getName());
				test.addScreenCaptureFromPath(imgPath);
				
			}
			catch(IOException e1)
			{
				e1.printStackTrace();
			}
		}
		
		public void onTestSkipped(ITestResult result)
		{
			test= extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups()); //to display groups in report
			
			test.log(Status.SKIP, result.getName() + " got Skipped");
			test.log(Status.INFO, result.getThrowable().getMessage());
		}
		
		
		public void onFinish(ITestResult testContext)
		{
			extent.flush();
			
			String pathOfExtentReport = System.getProperty("user.dir")+ "\\reports" +repName;
			File extentReport = new File(pathOfExtentReport);
			
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		/*	
			try {
				URL url = new URL("file:///"+ System.getProperty("user.dir")+ "\\reports\\"+ repName);
				
				//Create email message
				ImageHtmlEmail email = new ImageHtmlEmail();
				email.setDataSourceResolver(new DataSourceUrlResolver(url));
				email.setHostName("smtp.ggoglemail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("omkarkadam7214@gmail.com", "password"));
				email.setSSLOnConnect(true);
				email.setFrom("omkarkadam7214@gmail.com");  //sender
				email.setSubject("Test Result");
				email.setMsg("Please find attached report");
				email.addTo("omkarkadam7298@gmail.com"); //Receiver
				email.attach(url, "extent Report", "Please check report....");
				email.send();  //send the email
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		*/	
		}
		
		
		
		
	}



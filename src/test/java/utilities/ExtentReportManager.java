package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;

    public void onStart(ITestContext testContext) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        String currentDateTimestamp = df.format(new Date());

        repName = "Test-Report-" + currentDateTimestamp + ".html";
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/" + repName);

        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("OpenCart Functional Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Registration");
        extent.setSystemInfo("Sub Module", "Customer");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
 
        String os = testContext.getCurrentXmlTest().getParameter("os");
        String browser = testContext.getCurrentXmlTest().getParameter("browser");

        if (os != null) extent.setSystemInfo("Operating System", os);
        if (browser != null) extent.setSystemInfo("Browser", browser);

        List<String> groups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!groups.isEmpty()) extent.setSystemInfo("Groups", groups.toString());
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully executed.");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " got failed.");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try 
        {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } 
        catch (IOException e1) 
        {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped.");
        if (result.getThrowable() != null) {
            test.log(Status.SKIP, result.getThrowable());
        }
    }

    public void onFinish(ITestContext context) {
        extent.flush();

        String reportPath = System.getProperty("user.dir") + "/Reports/" + repName;
        File extentReport = new File(reportPath);

        try {
                Desktop.getDesktop().browse(extentReport.toURI());
            }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
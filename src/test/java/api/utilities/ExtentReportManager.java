package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {
		// Initialize the report name based on the current timestamp
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "TestReport_" + timestamp + ".html";

		// Initialize the ExtentSparkReporter object with the report name and set the
		// output path
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

		// Set some configuration options for the report
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
		sparkReporter.config().setReportName("Pet Store Users API");
		sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

		// Initialize the ExtentReports object and attach the ExtentSparkReporter object
		// to it
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Add some system information to the report
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User ", "Manasa");
	}

	public void onTestStart(ITestResult result) {
		// Start a new test with the name of the current test method
		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// Log the test as passed and add any additional information

		test.pass(MarkupHelper.createLabel(result.getMethod().getMethodName() + " Test Case Passed",
				com.aventstack.extentreports.markuputils.ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		// Log the test as failed and add any additional information
		test.fail(MarkupHelper.createLabel(result.getMethod().getMethodName() + " Test Case Failed",
				com.aventstack.extentreports.markuputils.ExtentColor.RED));

		// Log the exception stack trace as well
		test.fail(result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		// Log the test as failed and add any additional information
		test.skip(MarkupHelper.createLabel(result.getMethod().getMethodName() + " Test Case Skipped",
				com.aventstack.extentreports.markuputils.ExtentColor.RED));

		// Log the exception stack trace as well
		test.fail(result.getThrowable());
	}

	public void onFinish(ITestContext testContext) {
		// Flush the report to write all logs to the output file
		extent.flush();
	}
}
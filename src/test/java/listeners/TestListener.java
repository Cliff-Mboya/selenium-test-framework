package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

import java.io.File;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());

        // Create folder if missing
        String reportDir = "target/extent-reports";
        new File(reportDir).mkdirs();

        // Create reporter
        ExtentSparkReporter spark = new ExtentSparkReporter(reportDir + "/ExtentReport.html");
        spark.config().setReportName("UI Automation Report");
        spark.config().setDocumentTitle("Selenium Framework Report");

        // Attach to ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Optional system info
        extent.setSystemInfo("Suite", context.getSuite().getName());
        extent.setSystemInfo("Thread Count", String.valueOf(context.getSuite().getXmlSuite().getThreadCount()));
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        test.get().fail(result.getThrowable());

        try {
            String screenshotPath = ScreenshotUtils.takeScreenshot(testName);
            System.out.println("Screenshot saved: " + screenshotPath);

            // Attach screenshot to Extent report
            String fixedPath = new File(screenshotPath).getAbsolutePath().replace("\\", "/");
            test.get().addScreenCaptureFromPath(fixedPath);

        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
            test.get().warning("Screenshot capture failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());

        if (extent != null) {
            extent.flush(); //THIS is what actually writes the HTML file
        }
    }
}

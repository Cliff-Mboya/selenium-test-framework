package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();

        try {
            String path = ScreenshotUtils.takeScreenshot(testName);
            System.out.println("Screenshot saved: " + path);
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
    }
}

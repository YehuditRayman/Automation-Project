
import com.aventstack.extentreports.MediaEntityBuilder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class FailureReporter implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Object testInstance = context.getRequiredTestInstance();

        if (testInstance instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testInstance;

            String screenshotPath = baseTest.captureScreen("FAILURE_" + context.getDisplayName());

            if (baseTest.getTest() != null) {
                if (screenshotPath != null) {
                    baseTest.getTest().fail(throwable,
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else {
                    baseTest.getTest().fail(throwable);
                }
            }
        }

        throw throwable;
    }
}
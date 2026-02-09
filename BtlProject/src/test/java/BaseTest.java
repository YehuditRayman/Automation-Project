
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@ExtendWith(FailureReporter.class)
public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    protected static ExtentReports extent;
    protected static ExtentSparkReporter reporter;
    protected ExtentTest test;
    public ExtentTest getTest() {
        return test;
    }
    @BeforeAll
    public static void setupReport() {
        if (extent == null) {
            WebDriverManager.chromedriver().setup();

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            reporter = new ExtentSparkReporter("./test-output/ExtentReport_" + timestamp + ".html");
            reporter.config().setReportName("BTL Automation Report");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Yehudit & Chani");
            extent.setSystemInfo("Environment", "Production");
        }
    }

    @BeforeEach
    public void setupTest(TestInfo testInfo) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.btl.gov.il/Pages/default.aspx");

        homePage = new HomePage(driver);

        test = extent.createTest(testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    public static void endSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    public String captureScreen(String screenshotName) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = screenshotName + "_" + timestamp + ".png";

            File destinationPath = new File("./test-output/screenshots/" + fileName);
            destinationPath.getParentFile().mkdirs();

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, destinationPath);

            return "screenshots/" + fileName;

        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchTest extends BaseTest {

    @Test
    @DisplayName("בדיקת חיפוש באתר")
    public void testSearchFunctionality() {
        homePage.performSearch("חישוב סכום דמי לידה ליום");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='results']/h2"))).getText();

        String expectedTitle = "תוצאות חיפוש עבור חישוב סכום דמי לידה ליום";

        Assertions.assertEquals(expectedTitle, actualTitle, "הכותרת בדף התוצאות אינה תואמת");
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.UnemploymentCalculatorPage;

public class UnemploymentTest extends BaseTest {

    @Test
    @DisplayName("בדיקת תהליך חישוב דמי אבטלה")
    public void testUnemploymentCalculation() {
        UnemploymentCalculatorPage calculator = homePage.goToUnemploymentCalculator();

        Assertions.assertTrue(calculator.isPageTitleCorrect(), "הכותרת H1 לא תואמת - לא הגענו לדף חישוב דמי אבטלה");

        calculator.fillPersonalDetails("01/01/2026");

        calculator.fillSalaries("10000");

        boolean result = calculator.isResultDisplayed();
        Assertions.assertTrue(result, "טבלת תוצאות דמי האבטלה לא הוצגה!");
    }
}
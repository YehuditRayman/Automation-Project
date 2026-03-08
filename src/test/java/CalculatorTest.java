
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BtlCalculatorPage;
import pages.InsurancePage;

public class CalculatorTest extends BaseTest {

    @Test
    @DisplayName("חישוב דמי ביטוח לתלמיד ישיבה")
    public void testYeshivaStudentCalculation() {
        InsurancePage insurancePage = homePage.goToInsuranceInfo();
        BtlCalculatorPage calculator = insurancePage.clickOnCalculator();

        calculator.fillStepOne("תלמיד ישיבה", "01/01/2003");

        calculator.fillStepTwo();

        Assertions.assertTrue(calculator.isFinishPageDisplayed(), "המילה 'סיום' לא מופיעה בדף התוצאות");

        String total = calculator.getTotalAmount();
        System.out.println("הסכום שהתקבל באתר: " + total);

        Assertions.assertTrue(total.contains("171"), "הסכום הסופי אינו תואם ל-171 שח. התקבל: " + total);
    }
}
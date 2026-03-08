
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.BtlBasePage;

public class BreadcrumbsTest extends BaseTest {

    @ParameterizedTest(name = "בדיקת פירורי לחם עבור: {0}")
    @CsvSource({
            "אבטלה, אבטלה",
            "ילד נכה, ילד נכה",
            "ייעוץ לאזרח הוותיק, שירות הייעוץ לאזרח הוותיק ומשפחתו",
            "נכות כללית, נכות כללית",
            "מילואים, מילואים"
    })
    public void testPageNavigationWithBreadcrumbs(String subMenuName, String expectedText) {
        homePage.clickMainMenu(BtlBasePage.MainMenu.KITZBAOT);

        homePage.clickSubMenu(subMenuName);

        String actualBreadcrumb = homePage.getBreadcrumbText();
        System.out.println("נתיב שהתקבל עבור " + subMenuName + ": " + actualBreadcrumb);

        Assertions.assertTrue(actualBreadcrumb.contains(expectedText),
                "הנתיב (Breadcrumb) לא הכיל את המילה הצפויה. ציפינו ל: " + expectedText);
    }
}
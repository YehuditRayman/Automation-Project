
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BranchInfoPage;
import pages.BranchesPage;

public class BranchesTest extends BaseTest {

    @Test
    @DisplayName("בדיקת מעבר לדף סניפים ואימות כותרת")
    public void testNavigateToBranches() {
        BranchesPage branchesPage = homePage.goToBranches();

        String expectedTitle = "סניפים וערוצי שירות";
        String actualTitle = branchesPage.getPageTitle();

        Assertions.assertEquals(expectedTitle, actualTitle, "הכותרת בדף הסניפים אינה תואמת לציפיות");
    }

    @Test
    @DisplayName("בדיקת תצוגת פרטי סניף (כתובת, קבלת קהל, מענה טלפוני)")
    public void testBranchDetails() {
        // Arrange & Act
        BranchesPage branchesPage = homePage.goToBranches();
        BranchInfoPage infoPage = branchesPage.clickOnFirstBranch();

        Assertions.assertTrue(infoPage.isAddressDisplayed(), "שדה 'כתובת' לא נמצא בדף הסניף");
        Assertions.assertTrue(infoPage.isReceptionDisplayed(), "שדה 'קבלת קהל' לא נמצא בדף הסניף");
        Assertions.assertTrue(infoPage.isPhoneResponseDisplayed(), "שדה 'מענה טלפוני' לא נמצא בדף הסניף");
    }
}
package pages;
import infrastructure.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BtlBasePage extends BasePage {

    private By searchInputLocator = By.id("TopQuestions");
    private By searchButtonLocator = By.id("ctl00_SiteHeader_reserve_btnSearch");
    private By branchesButtonLocator = By.id("ctl00_Topmneu_BranchesHyperLink");

    public BtlBasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void initElement() {
    }

    public enum MainMenu {
        ZCHUIOT("מיצוי זכויות"),
        KITZBAOT("קצבאות והטבות"),
        DMEI_BITUACH("דמי ביטוח"),
        KESHER("יצירת קשר");

        private final String menuName;
        MainMenu(String menuName) {
            this.menuName = menuName;
        }
        public String getMenuName() {
            return menuName;
        }
    }

    public void clickMainMenu(MainMenu menu) {
        String xpathExpression = "//a[text()='" + menu.getMenuName() + "']";
        WebElement menuElement = driver.findElement(By.xpath(xpathExpression));
        menuElement.click();
    }

    public void clickSubMenu(String subMenuName) {
        WebElement subMenuElement = driver.findElement(By.partialLinkText(subMenuName));
        subMenuElement.click();
    }

    public void performSearch(String searchText) {
        WebElement searchInput = driver.findElement(searchInputLocator);
        searchInput.clear();
        searchInput.sendKeys(searchText);

        WebElement searchBtn = driver.findElement(searchButtonLocator);
        searchBtn.click();
    }

    public BranchesPage goToBranches() {
        driver.findElement(branchesButtonLocator).click();
        return new BranchesPage(driver);
    }
    public InsurancePage goToInsuranceInfo() {
        clickMainMenu(MainMenu.DMEI_BITUACH);

        clickSubMenu("דמי ביטוח לאומי");

        return new InsurancePage(driver);
    }


    public UnemploymentCalculatorPage goToUnemploymentCalculator() {
        clickMainMenu(MainMenu.KITZBAOT);

        clickSubMenu("אבטלה");

        driver.findElement(By.partialLinkText("למחשבוני דמי אבטלה")).click();

        driver.findElement(By.xpath("//a[contains(text(), 'חישוב דמי אבטלה')]")).click();

        return new UnemploymentCalculatorPage(driver);
    }
    // בתוך BtlBasePage.java

    public String getBreadcrumbText() {
        try {
            return driver.findElement(By.xpath("//ol[contains(@class, 'breadcrumbs')]")).getText();
        } catch (Exception e) {
            System.out.println("לא נמצאו פירורי לחם בדף זה");
            return "";
        }
    }
}

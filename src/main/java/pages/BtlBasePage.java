package pages;
import infrastructure.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BtlBasePage extends BasePage {

    @FindBy(id = "TopQuestions")
    private WebElement searchInput;

    @FindBy(id = "ctl00_SiteHeader_reserve_btnSearch")
    private WebElement searchButton;

    @FindBy(id = "ctl00_Topmneu_BranchesHyperLink")
    private WebElement branchesButton;

    @FindBy(xpath = "//ol[contains(@class, 'breadcrumbs')]")
    private WebElement breadcrumbs;

    public BtlBasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
        searchInput.clear();
        searchInput.sendKeys(searchText);
        searchButton.click();
    }

    public BranchesPage goToBranches() {
        branchesButton.click();
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

    public String getBreadcrumbText() {
        try {
            return breadcrumbs.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BranchesPage extends BtlBasePage {

    @FindBy(xpath = "//div[@class='opener-bg ']/h1")
    private WebElement pageTitle;

    @FindBy(xpath = "//a[@class='SnifName']")
    private WebElement firstBranchLink;

    public BranchesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void initElement() {
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public BranchInfoPage clickOnFirstBranch() {
        firstBranchLink.click();
        return new BranchInfoPage(driver);
    }
}
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InsurancePage extends BtlBasePage {

    @FindBy(partialLinkText = "מחשבון לחישוב דמי הביטוח")
    private WebElement calculatorLink;

    public InsurancePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void initElement() {
    }

    public BtlCalculatorPage clickOnCalculator() {
        calculatorLink.click();
        return new BtlCalculatorPage(driver);
    }
}
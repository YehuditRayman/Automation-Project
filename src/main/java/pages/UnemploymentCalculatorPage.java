package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class UnemploymentCalculatorPage extends BtlBasePage {

    @FindBy(xpath = "//label[contains(text(), 'תאריך')]/following::input[1]")
    private WebElement dateInput;

    @FindBy(xpath = "//label[contains(text(), 'מעל 28')]")
    private WebElement ageOption;

    @FindBy(xpath = "//input[@value='המשך'] | //input[@value='חשב'] | //button[contains(text(), 'המשך')]")
    private WebElement continueBtn;

    @FindBy(tagName = "h1")
    private WebElement pageTitle;

    @FindBy(xpath = "//*[contains(text(), 'דמי אבטלה ליום')]")
    private WebElement resultIndicator;

    @FindBy(xpath = "//table//input[@type='text']")
    private List<WebElement> salaryInputs;

    public UnemploymentCalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void initElement() {
    }

    public void fillPersonalDetails(String workStopDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(dateInput)).click();
        dateInput.clear();
        dateInput.sendKeys(workStopDate);

        wait.until(ExpectedConditions.elementToBeClickable(ageOption)).click();

        clickContinue();
    }

    public void fillSalaries(String salaryAmount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(salaryInputs));

        for (WebElement input : salaryInputs) {
            if (input.isDisplayed() && input.isEnabled()) {
                input.clear();
                input.sendKeys(salaryAmount);
            }
        }
        clickContinue();
    }

    private void clickContinue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public boolean isPageTitleCorrect() {
        try {
            return pageTitle.getText().contains("חישוב סכום דמי אבטלה");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isResultDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(resultIndicator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
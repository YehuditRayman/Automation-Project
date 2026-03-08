package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BtlCalculatorPage extends BtlBasePage {

    @FindBy(xpath = "//label[contains(text(), 'זכר')]")
    private WebElement maleGenderLabel;

    @FindBy(xpath = "//label[contains(text(), 'תאריך לידה')]/following::input[1]")
    private WebElement dobInput;

    @FindBy(xpath = "//label[normalize-space()='לא']")
    private WebElement noButton;

    @FindBy(xpath = "//*[contains(text(), 'סיום')]")
    private WebElement finishIndicator;

    @FindBy(xpath = "//input[@value='המשך'] | //input[@value='חשב']")
    private WebElement continueBtn;

    @FindBy(xpath = "//li[contains(text(), 'סך הכל דמי ביטוח לחודש')]/strong")
    private WebElement totalAmountLabel;

    public BtlCalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void initElement() {
    }

    public void fillStepOne(String category, String dateOfBirth) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement categoryElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), '" + category + "')]")));
        categoryElement.click();

        wait.until(ExpectedConditions.elementToBeClickable(maleGenderLabel)).click();

        wait.until(ExpectedConditions.visibilityOf(dobInput));
        dobInput.click();
        dobInput.clear();
        dobInput.sendKeys(dateOfBirth);

        clickContinue();
    }

    public void fillStepTwo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(noButton)).click();
        clickContinue();
    }

    public boolean isFinishPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(finishIndicator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void clickContinue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public String getTotalAmount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(totalAmountLabel)).getText();
    }
}
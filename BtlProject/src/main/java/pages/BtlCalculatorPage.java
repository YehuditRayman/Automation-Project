package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BtlCalculatorPage extends BtlBasePage {

    public BtlCalculatorPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void initElement() {
    }

    public void fillStepOne(String category, String dateOfBirth) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement categoryElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), '" + category + "')]")));
        categoryElement.click();

        WebElement genderElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), 'זכר')]")));
        genderElement.click();

        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'תאריך לידה')]/following::input[1]")));
        dateInput.click();
        dateInput.clear();
        dateInput.sendKeys(dateOfBirth);

        clickContinue();
    }

    public void fillStepTwo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement noButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='לא']")));
        noButton.click();

        clickContinue();
    }

    public boolean isFinishPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'סיום')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void clickContinue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='המשך'] | //input[@value='חשב']")));
        continueBtn.click();
    }

    public String getTotalAmount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(), 'סך הכל דמי ביטוח לחודש')]/strong"))).getText();
    }
}
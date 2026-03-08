package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class UnemploymentCalculatorPage extends BtlBasePage {

    public UnemploymentCalculatorPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void initElement() {
    }

    public void fillPersonalDetails(String workStopDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), 'תאריך')]/following::input[1]")));
        dateInput.click();
        dateInput.clear();
        dateInput.sendKeys(workStopDate);

        WebElement ageOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), 'מעל 28')]")));
        ageOption.click();

        clickContinue();
    }

    public void fillSalaries(String salaryAmount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//input[@type='text']")));
        List<WebElement> salaryInputs = driver.findElements(By.xpath("//table//input[@type='text']"));

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
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='המשך'] | //input[@value='חשב'] | //button[contains(text(), 'המשך')]")));
        continueBtn.click();
    }

    public boolean isPageTitleCorrect() {
        try {
            String title = driver.findElement(By.tagName("h1")).getText();
            return title.contains("חישוב סכום דמי אבטלה");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isResultDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'דמי אבטלה ליום')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
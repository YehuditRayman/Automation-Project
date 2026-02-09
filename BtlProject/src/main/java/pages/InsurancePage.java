package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InsurancePage extends BtlBasePage {

    public InsurancePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void initElement() {
    }

    public BtlCalculatorPage clickOnCalculator() {
        driver.findElement(By.partialLinkText("מחשבון לחישוב דמי הביטוח")).click();

        return new BtlCalculatorPage(driver);
    }
}
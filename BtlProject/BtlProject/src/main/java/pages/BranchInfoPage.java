package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BranchInfoPage extends BtlBasePage {

    public BranchInfoPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void initElement() {
    }

    public boolean isAddressDisplayed() {
        try {
            return driver.findElement(By.xpath("//ul[@class='SnifDetails']//label[contains(text(), 'כתובת')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isReceptionDisplayed() {
        try {
            return driver.findElement(By.xpath("//ul[@class='SnifDetails']//label[contains(text(), 'קבלת קהל')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPhoneResponseDisplayed() {
        try {
            return driver.findElement(By.xpath("//ul[@class='SnifDetails']//label[contains(text(), 'מענה טלפוני')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
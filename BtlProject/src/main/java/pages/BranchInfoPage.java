package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BranchInfoPage extends BtlBasePage {

    @FindBy(xpath = "//ul[@class='SnifDetails']//label[contains(text(), 'כתובת')]")
    private WebElement addressLabel;

    @FindBy(xpath = "//ul[@class='SnifDetails']//label[contains(text(), 'קבלת קהל')]")
    private WebElement receptionLabel;

    @FindBy(xpath = "//ul[@class='SnifDetails']//label[contains(text(), 'מענה טלפוני')]")
    private WebElement phoneResponseLabel;

    public BranchInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void initElement() {
    }

    public boolean isAddressDisplayed() {
        try {
            return addressLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isReceptionDisplayed() {
        try {
            return receptionLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPhoneResponseDisplayed() {
        try {
            return phoneResponseLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
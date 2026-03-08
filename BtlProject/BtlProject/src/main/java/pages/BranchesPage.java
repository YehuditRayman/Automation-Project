package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class BranchesPage extends BtlBasePage {

    public BranchesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void initElement() {
    }

    public String getPageTitle() {
        return driver.findElement(By.xpath("//div[@class='opener-bg ']/h1")).getText();
    }

    public BranchInfoPage clickOnFirstBranch() {
        driver.findElement(By.xpath("//a[@class='SnifName']")).click();
        return new BranchInfoPage(driver);
    }
}
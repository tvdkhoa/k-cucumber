package khoatester.com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static khoatester.com.keywords.WebUI.*;
public class CommonPage {
    private String menuItemsXpath = "//span[normalize-space()='%s']";

    private By messageNotify = By.xpath("//span[@data-notify='message']");

    private By buttonEdit = By.xpath("(//a[@title='Edit'])[1]");
    private By inputSearch = By.xpath("//input[@id='search']");
    public By avatarProfile = By.xpath("//span[contains(@class,'avatar avatar-sm')]");

    public void clickMenuItem(String menuItem) {
        clickToElement(By.xpath(getDynamicLocator(menuItemsXpath,menuItem)));
    }
    public void searchDataTable(String dataName) {
        sendKeyToElement(inputSearch, dataName);
        sendKeyToElement(inputSearch, Keys.ENTER);
    }

    public void clickEditButton() {
        clickToElement(buttonEdit);
    }

    public String getMessageNotify() {
        return getElementText(messageNotify);
    }


}

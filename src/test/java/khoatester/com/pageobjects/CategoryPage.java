package khoatester.com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import static khoatester.com.keywords.WebUI.*;
public class CategoryPage {
    private By buttonAddNewCategory = By.xpath("//span[normalize-space()='Add New category']");
    private By inputCategoryName = By.xpath("//input[@id='name']");
    private By inputOrderingNumber = By.xpath("//input[@id='order_level']");
    private By inputMetaTitle = By.xpath("//input[@placeholder='Meta Title']");
    private By inputMetaDescription = By.xpath("//textarea[@name='meta_description']");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");
    private By messageAlert = By.xpath("//span[@data-notify='message']");

    private By inputSearch = By.xpath("//input[@id='search']");
    private By categoryNameFirstRow = By.xpath("//tbody/tr[1]/td[2]");


    public void clickAddNewButton() {
        clickToElement(buttonAddNewCategory);
    }

    public void searchCategory(String categoryName) {
        waitForPageLoaded();
        sendKeyToElement(inputSearch, categoryName);
        sendKeyToElement(inputSearch, Keys.ENTER);
    }

    public void checkCategoryDisplayed(String categoryName) {
        searchCategory(categoryName);
        Assert.assertTrue(isElementVisible(categoryNameFirstRow));
        Assert.assertEquals(getElementText(categoryNameFirstRow), categoryName, "The new category name not match.");
    }

    public void inputDataCategory(String categoryName, String orderNumber, String metaTitle, String description) {
        waitForPageLoaded();
        sendKeyToElement(inputCategoryName, categoryName);
        sendKeyToElement(inputOrderingNumber, orderNumber);
        sendKeyToElement(inputMetaTitle, metaTitle);
        sendKeyToElement(inputMetaDescription, description);
    }

    public void clickSaveButton() {
        clickToElement(buttonSave);
    }

    public void verifyAlertSuccessDisplayed() {
        Assert.assertTrue(isElementVisible(messageAlert));
    }
}

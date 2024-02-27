package khoatester.com.keywords;

import khoatester.com.constant.ConstantGlobal;
import khoatester.com.drivers.DriverManager;
import khoatester.com.utils.LogUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.Color;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class WebUI {
    private static final int defaultTimeout = ConstantGlobal.DEFAULT_TIME_OUT;

    public static void implicitWait() {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultTimeout));
    }

    public static void implicitWait(int timeout) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    public static void maximumWindowSize() {
        DriverManager.getDriver().manage().window().maximize();
    }

    public static void openPageUrl(String url) {
        DriverManager.getDriver().get(url);
    }

    public static String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public static String getPageUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public static String getPageSourceCode() {
        return DriverManager.getDriver().getPageSource();
    }

    public static void backToPage() {
        DriverManager.getDriver().navigate().back();
    }

    public static void forwardToPage() {
        DriverManager.getDriver().navigate().forward();
    }

    public static void refreshCurrentPage() {
        DriverManager.getDriver().navigate().refresh();
    }

    /*******************************************************************************
     *                    HANDLE ALERT                                             *
     * ****************************************************************************/
    public static Alert waitForAlertPresence() {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(defaultTimeout));
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public static void acceptAlert() {
        waitForAlertPresence().accept();
    }

    public static void cancelAlert() {
        waitForAlertPresence().dismiss();
    }

    public static String getAlertText() {
        return waitForAlertPresence().getText();
    }

    public static void sendKeyToAlert(String value) {
        waitForAlertPresence().sendKeys(value);
    }

    /*******************************************************************************
     *                    HANDLE WINDOW TAB                                        *
     * ****************************************************************************/
    public static void switchToWindowByID(String windowId) {
        Set<String> allWindowID = DriverManager.getDriver().getWindowHandles();
        for (String id : allWindowID) {
            if (!id.equals(windowId)) {
                DriverManager.getDriver().switchTo().window(id);
                break;
            }
        }
    }

    public static void switchToWindowByTitle(String windowTitle) {
        Set<String> allWindowID = DriverManager.getDriver().getWindowHandles();
        for (String id : allWindowID) {
            DriverManager.getDriver().switchTo().window(id);
            String actualTitle = DriverManager.getDriver().getTitle();
            if (actualTitle.equals(windowTitle)) {
                break;
            }
        }
    }

    public static void closeAllTabWithoutParent(String parentID) {
        Set<String> allWindowID = DriverManager.getDriver().getWindowHandles();
        for (String id : allWindowID) {
            if (!id.equals(parentID)) {
                DriverManager.getDriver().switchTo().window(id);
                DriverManager.getDriver().close();
            }
        }
        DriverManager.getDriver().switchTo().window(parentID);
    }

    /*******************************************************************************
     *                    HANDLE WEB ELEMENT                                       *
     * ****************************************************************************/
//    public static By getByXpath(String xpathLocator) {
//        return new By.ByXPath(xpathLocator);
//    }
//    public static By getByXpath(String xpathLocator, String...params) {
//        return new By.ByXPath(getDynamicLocator(xpathLocator, params));
//    }
    public static String getDynamicLocator(String xpathLocator, String... params) {
        return String.format(xpathLocator, (Object[]) params);
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    public static void clickToElement(By by) {
        waitForElementClickable(by);
        getWebElement(by).click();
    }

    public static void sendKeyToElement(By by, String value) {
        waitForElementVisible(by);
        WebElement element = getWebElement(by);
        element.clear();
        element.sendKeys(value);
    }

    public static void sendKeyToElement(By by, Keys keys) {
        waitForElementVisible(by);
        WebElement element = getWebElement(by);
        element.clear();
        element.sendKeys(keys);
    }

    public static void uploadFile(By by, String filePath) {
        waitForElementPresent(by);
        WebElement element = getWebElement(by);
        element.sendKeys(filePath);
    }

    public static void selectItemInDefaultDropDown(By by, String textItem) {
        waitForElementVisible(by);
        Select select = new Select(getWebElement(by));
        select.selectByValue(textItem);
    }

    public static String getSelectItemInDefaultDropDown(By by) {
        waitForElementVisible(by);
        Select select = new Select(getWebElement(by));
        return select.getFirstSelectedOption().getText();
    }

    public static void isDropdownMultiple(By by) {
        waitForElementVisible(by);
        Select select = new Select(getWebElement(by));
        select.isMultiple();
    }

    public static void selectItemInCustomDropDown(By parentBy, By childBy,
                                           String expectedItemText) {
        getWebElement(parentBy).click();
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(defaultTimeout));
        List<WebElement> allItems = explicitWait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItemText)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                item.click();
                break;
            }
        }
    }

    public static String getElementAttribute(By by, String attributeName) {
        waitForElementVisible(by);
        return getWebElement(by).getAttribute(attributeName);
    }

    public static String getElementText(By by) {
        waitForElementVisible(by);
        return getWebElement(by).getText();
    }

    public static String getCssValue(By by, String propertyName) {
        waitForElementVisible(by);
        return getWebElement(by).getCssValue(propertyName);
    }

    public static String getHexColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public static int getElementSize(By by, int timeout) {
        implicitWait(timeout);
        int elementSize = getWebElements(by).size();
        implicitWait();
        return elementSize;
    }

    public static void checkToDefaultCheckBoxRadio(By by) {
        waitForElementVisible(by);
        WebElement element = getWebElement(by);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public static void unCheckToDefaultCheckBoxRadio(By by) {
        waitForElementVisible(by);
        WebElement element = getWebElement(by);
        if (element.isSelected()) {
            element.click();
        }
    }

    public static Boolean isElementVisible(By by, int timeout) {
        try {
            waitForElementVisible(by, timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean isElementVisible(By by) {
        try {
            waitForElementVisible(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean isElementNotVisible(By by, int timeout) {
        implicitWait(timeout);
        List<WebElement> elements = getWebElements(by);
        implicitWait();
        return (elements.size() > 0 && !! elements.get(0).isDisplayed());
    }

    public static Boolean isElementPresent(By by, int timeout) {
        try {
            waitForElementPresent(by, timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean isElementPresent(By by) {
        try {
            waitForElementPresent(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

        public static Boolean isElementNotPresent(By by,int timeout) {
        implicitWait(timeout);
        List<WebElement> elements = getWebElements(by);
        implicitWait();
        return elements.size() == 0;
    }

    public static Boolean isElementEnabled(By by) {
        waitForElementVisible(by);
        return getWebElement(by).isEnabled();
    }

    public static Boolean isElementSelected(By by) {
        waitForElementVisible(by);
        return getWebElement(by).isSelected();
    }

    public static void switchToFrameIframe(By by) {
        DriverManager.getDriver().switchTo().frame(getWebElement(by));
    }

    public static void switchToDefaultContent() {
        DriverManager.getDriver().switchTo().defaultContent();
    }

    /*******************************************************************************
     *                    HANDLE ACTIONS AND JS EXECUTION                          *
     * ****************************************************************************/
    public static void hoverMouseToElement(By by) {
        waitForElementVisible(by);
        Actions action = new Actions(DriverManager.getDriver());
        action.moveToElement(getWebElement(by)).perform();
    }

    public static void scrollToBottomPage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public static void highlightElement(By by) {
        waitForElementPresent(by);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
        WebElement element = getWebElement(by);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
                "border: 2px solid red; border-style: dashed;");
        staticWait(1);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public static void clickToElementByJS(By by) {
        waitForElementPresent(by);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
        jsExecutor.executeScript("arguments[0].click();", getWebElement(by));
    }

    public static void scrollToElementOnTop(By by) {
        waitForElementPresent(by);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
    }

    public static void scrollToElementOnDown(By by) {
        waitForElementPresent(by);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
    }

    public static void removeAttributeInDOM(By by, String attributeRemove) {
        waitForElementPresent(by);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
                getWebElement(by));
    }

    public static String getElementValidationMessage(By by) {
        waitForElementPresent(by);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
                getWebElement(by));
    }

    public static boolean isImageLoaded(By by) {
        waitForElementPresent(by);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(by));
        if(status) {
            return true;
        }
        return false;
    }

    /*******************************************************************************
     *                    HANDLE WAIT FOR ELEMENT                                  *
     * ****************************************************************************/
    public static void waitForElementVisible(By by) {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(defaultTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementVisible(By by, int timeout) {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForAllElementVisible(By by) {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(defaultTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public static void waitForAllElementVisible(By by, int timeout) {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public static void waitForElementInvisible(By by) {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(defaultTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void waitForElementInvisible(By by, int timeout) {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void waitForAllElementInvisible(By by) {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(defaultTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElements(by)));
    }

    public static void waitForAllElementInvisible(By by, int timeout) {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElements(by)));
    }

    public static void waitForElementClickable(By by) {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(defaultTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementClickable(By by,int timeout) {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementPresent(By by) {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(defaultTimeout));
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementPresent(By by, int timeout) {
        WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void staticWait(long timeoutSecond) {
        try {
            Thread.sleep(timeoutSecond * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConstantGlobal.PAGE_LOAD_TIME_OUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState").toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogUtils.info("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load (Javascript). (" + ConstantGlobal.PAGE_LOAD_TIME_OUT + "s)");
            }
        }
    }

}
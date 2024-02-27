package khoatester.com.hooks;

import khoatester.com.drivers.DriverFactory;
import khoatester.com.drivers.DriverManager;
import khoatester.com.pageobjects.CommonPage;
import khoatester.com.pageobjects.LoginCMSPage;
import khoatester.com.pageobjects.CategoryPage;
import khoatester.com.utils.LogUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
public class TestContext {
    private WebDriver driver;
    private CommonPage commonPage;
    private LoginCMSPage loginCMSPage;
    private CategoryPage categoryPage;

    public TestContext() {
        ThreadGuard.protect(new DriverFactory().createDriver());
        LogUtils.info("Driver in TestContext: " + getDriver());
    }

    public CommonPage getCommonPage() {
        if (commonPage == null) {
            commonPage = new CommonPage();
        }
        return commonPage;
    }

    public LoginCMSPage getLoginCMSPage() {
        if (loginCMSPage == null) {
            loginCMSPage = new LoginCMSPage();
        }
        return loginCMSPage;
    }

    public CategoryPage getCategoryPage() {
        if (categoryPage == null) {
            categoryPage = new CategoryPage();
        }
        return categoryPage;
    }

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}

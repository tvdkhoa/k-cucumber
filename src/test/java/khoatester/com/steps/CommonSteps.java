package khoatester.com.steps;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import khoatester.com.drivers.DriverManager;
import khoatester.com.hooks.TestContext;
import khoatester.com.pageobjects.CategoryPage;
import khoatester.com.pageobjects.CommonPage;
import khoatester.com.pageobjects.LoginCMSPage;
import khoatester.com.reports.AllureManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

public class CommonSteps {
    TestContext testContext;
    LoginCMSPage loginCMSPage;
    CategoryPage categoryPage;
    CommonPage commonPage;
    public CommonSteps(TestContext testContext) {
        this.testContext = testContext;
        commonPage = testContext.getCommonPage();
        loginCMSPage = testContext.getLoginCMSPage();
        categoryPage = testContext.getCategoryPage();
    }

    @Given("user logged in as an admin")
    public void userLoggedInAsAnAdmin() {
        loginCMSPage.loginToCMSAsAdminRole();
    }

    @Given("user logged in the CMS system with {string} role")
    public void userLoggedInTheCMSSystemWithRole(String roleName) {
        loginCMSPage.loginToCMSAsAdminRole();
    }

    @Then("the message {string} displays")
    public void theMessageDisplays(String message) {
        Assert.assertEquals(commonPage.getMessageNotify(), message, "Message not match.");
    }

    @And("user should see the notification displays")
    public void userShouldSeeTheNotificationDisplays() {

    }

    @When("I takes screenshot")
    public void takesScreenshot() {
        AllureManager.saveScreenshotPNG();
    }
}

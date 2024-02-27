package khoatester.com.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import khoatester.com.common.BaseTest;
import khoatester.com.hooks.TestContext;
import khoatester.com.keywords.WebUI;
import khoatester.com.pageobjects.LoginCMSPage;
import org.testng.Assert;

public class LoginCMSSteps{

    LoginCMSPage loginCMSPage;
    public LoginCMSSteps(TestContext testContext) {
        loginCMSPage = testContext.getLoginCMSPage();
    }
    @Given("User navigate to Login Page for Admin {string}")
    public void userNavigateToLoginPageForAdmin(String url) {
        WebUI.openPageUrl(url);
    }

    @When("user enter email {string} password {string}")
    public void userEnterEmailPassword(String email, String password) {
        loginCMSPage.enterValueForEmailTextBox(email);
        loginCMSPage.enterValueForPasswordTextBox(password);
    }

    @And("click Login button")
    public void clickLoginButton() {
        loginCMSPage.clickToLoginButton();
    }

    @Then("user is redirected to the Dashboard page")
    public void userIsRedirectedToTheDashboardPage() {
        WebUI.staticWait(5);
        Assert.assertTrue(false);
    }
}

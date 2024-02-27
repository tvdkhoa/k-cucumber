package khoatester.com.pageobjects;
import static khoatester.com.keywords.WebUI.*;

import khoatester.com.helpers.PropertiesHelper;
import org.openqa.selenium.By;
public class LoginCMSPage {
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");

    public void enterValueForEmailTextBox(String email)
    {
        sendKeyToElement(inputEmail,email);
    }
    public void enterValueForPasswordTextBox(String password)
    {
        sendKeyToElement(inputPassword,password);
    }
    public void clickToLoginButton()
    {
        clickToElement(buttonLogin);
    }

    public void loginToCMSAsAdminRole()
    {
        openPageUrl(PropertiesHelper.getValue("url"));
        enterValueForEmailTextBox(PropertiesHelper.getValue("email"));
        enterValueForPasswordTextBox(PropertiesHelper.getValue("password"));
    }
}

package khoatester.com.drivers;

import khoatester.com.constant.ConstantGlobal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import khoatester.com.helpers.PropertiesHelper;
public class DriverFactory {
    public WebDriver createDriver() {
        WebDriver driver = setupBrowser(PropertiesHelper.getValue("borwser"));
        DriverManager.setDriver(driver);
        return driver;
    }

    public WebDriver createDriver(String browserName) {
        WebDriver driver = setupBrowser(browserName);
        DriverManager.setDriver(driver);
        return driver;
    }

    private WebDriver setupBrowser(String browserName) {
        WebDriver driver;
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome browser default...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if(ConstantGlobal.HEADLESS == true){
            options.addArguments("--headless=new");
            options.addArguments("window-size=1920,1080");
        }

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initEdgeDriver() {
        System.out.println("Launching Edge browser...");
        //WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();
        if(ConstantGlobal.HEADLESS == true){
            options.addArguments("--headless=new");
            options.addArguments("window-size=1920,1080");
        }

        WebDriver driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        System.out.println("Launching Firefox browser...");
        //WebDriverManager.firefoxdriver().setup();

        WebDriver driver = new FirefoxDriver();

        FirefoxOptions options = new FirefoxOptions();
        if(ConstantGlobal.HEADLESS == true){
            options.addArguments("--headless");
            options.addArguments("window-size=1920,1080");
        }
        driver.manage().window().maximize();
        return driver;
    }

    public void closeDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }
}

package khoatester.com.hooks;

import io.cucumber.java.*;
import khoatester.com.common.BaseTest;
import khoatester.com.drivers.DriverManager;
import khoatester.com.helpers.CaptureHelper;
import khoatester.com.helpers.PropertiesHelper;
import khoatester.com.helpers.SystemsHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CucumberHooks {
    @BeforeAll
    public static void beforeAll()
    {
        System.out.println("============== beforeAll =============");
        PropertiesHelper.loadAllFiles();
        SystemsHelper.CleanTargetDirectory();
    }

    @AfterAll
    public static void afterAll()
    {
        System.out.println("============== afterAll =============");
    }

    @Before
    public void beforeScenario() {
        System.out.println("============== beforeScenario =============");

    }

    @After
    public void afterScenario() {
        System.out.println("============== afterScenario =============");
        DriverManager.quit();
    }

    @BeforeStep
    public void beforeStep(Scenario scenario)
    {
        System.out.println("============== beforeStep =============");
    }

    @AfterStep
    public void afterStep(Scenario scenario)
    {
        System.out.println("============== afterStep =============");
        if(scenario.isFailed())
        {
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot Failed");
        }
    }
}

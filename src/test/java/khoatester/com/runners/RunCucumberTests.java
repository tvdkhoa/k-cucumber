package khoatester.com.runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Categories/Categories.feature",
        glue = {"khoatester.com.steps","khoatester.com.hooks"},
        plugin = {"pretty", "html:target/cucumber-html-report.html","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm","khoatester.com.hooks.CucumberListener"}
)
@Test
public class RunCucumberTests extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

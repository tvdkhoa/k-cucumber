package khoatester.com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Login/Login.feature",
        glue = {"khoatester.com.steps","khoatester.com.hooks"},
        plugin = {"pretty", "html:target/cucumber-html-report.html","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
@Test
public class RunLoginTests extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

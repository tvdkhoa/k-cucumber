package khoatester.com.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import khoatester.com.common.BaseTest;
import khoatester.com.helpers.ExcelHelper;
import khoatester.com.helpers.ExtractDatTableHelper;
import khoatester.com.hooks.TestContext;
import khoatester.com.keywords.WebUI;
import khoatester.com.pageobjects.CategoryPage;
import khoatester.com.pageobjects.CommonPage;
import khoatester.com.pageobjects.LoginCMSPage;
import khoatester.com.utils.LogUtils;

import java.util.List;
import java.util.Map;

public class CategorySteps {
    TestContext testContext;
    LoginCMSPage loginCMSPage;
    CategoryPage categoryPage;
    CommonPage commonPage;
    ExcelHelper excelHelpers;

    public CategorySteps(TestContext testContext) {
        this.testContext = testContext;
        commonPage = testContext.getCommonPage();
        loginCMSPage = testContext.getLoginCMSPage();
    }

    @Given("user has access to the Category page")
    public void userHasAccessToTheCategoryPage() {
        commonPage.clickMenuItem("Products");
        commonPage.clickMenuItem("Category");
        categoryPage = testContext.getCategoryPage();
    }

    @When("user has finished entering the category information")
    public void userHasFinishedEnteringTheCategoryInformation() {
        categoryPage.clickAddNewButton();
        excelHelpers = new ExcelHelper();
        excelHelpers.setExcelFile("src/test/resources/datatest/CMS.xlsx", "Category");
        categoryPage.inputDataCategory(
                excelHelpers.getCellData("category_name", 1),
                excelHelpers.getCellData("order_number", 1),
                excelHelpers.getCellData("meta_title", 1),
                excelHelpers.getCellData("description", 1)
        );
    }

    @And("click the Save button")
    public void clickTheSaveButton() {
        categoryPage.clickSaveButton();
    }

    @When("user search a category existing {string}")
    public void userSearchACategoryExisting(String categoryName) {
        categoryPage.searchCategory(categoryName);
    }

    @And("user edit the category information")
    public void userEditTheCategoryInformation() {
        commonPage.clickEditButton();
        excelHelpers = new ExcelHelper();
        excelHelpers.setExcelFile("src/test/resources/datatest/CMS.xlsx", "Category");
        categoryPage.inputDataCategory(
                excelHelpers.getCellData("category_name", 1),
                excelHelpers.getCellData("order_number", 1),
                excelHelpers.getCellData("meta_title", 1),
                excelHelpers.getCellData("description", 1)
        );
    }

    @When("the user adds some categories below")
    public void theUserAddsSomeCategoriesBelow(DataTable dataTable) {
        List<Map< String, String >> items = ExtractDatTableHelper.extractDataTableToArrayListMap(dataTable);
        for(Map<String,String> item:items )
        {
            LogUtils.info(item.get("category_name")+"|"+item.get("order_number")+"|"+item.get("meta_title")+"|"+item.get("description"));
        }
    }
}

package com.trycloud.step_definitions;

import com.github.javafaker.Faker;
import com.trycloud.pages.FilesPageFavorite;
import com.trycloud.pages.LoginPage;
import com.trycloud.utilities.ConfigurationReader;
import com.trycloud.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US5_FileToFavorites {

    FilesPageFavorite filesPageFavorite = new FilesPageFavorite();
    LoginPage loginPage = new LoginPage();
    Faker faker = new Faker();
    int num = faker.number().numberBetween(1, filesPageFavorite.uploadedFilesSelectors.size());
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
    static String chosenFileName = "";

    @Given("user on the dashboard page")
    public void userOnTheDashboardPage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
    }


    @When("the user clicks the {string} module")
    public void the_user_clicks_the_module(String module) {
        new FilesPageFavorite().clickModule(module);
    }


    @When("the user clicks action-icon  from any file on the page")
    public void the_user_clicks_action_icon_from_any_file_on_the_page() {
        chosenFileName = filesPageFavorite.fileNames.get(num).getText();
        filesPageFavorite.actionBtns.get(num).click();
    }

    @When("user choose the {string} option")
    public void user_choose_the_option(String actionIconOption) {
        for (WebElement eachOption : filesPageFavorite.actionIconOptions) {
            if (eachOption.getText().contains(actionIconOption)) {
                eachOption.click();
                break;
            }
        }
    }

    @When("user click the {string} sub-module on the left side")
    public void user_click_the_sub_module_on_the_left_side(String subModule) {
        for (WebElement eachModule : filesPageFavorite.subModules) {
            if (eachModule.getText().equals(subModule)) {
                eachModule.click();
                break;
            }
        }

        if (subModule.equals("Favorites") || subModule.equals("Deleted files")) {
            Driver.getDriver().navigate().refresh();

        }
    }

        @Then("Verify the chosen file is listed on the table")
        public void verify_the_chosen_file_is_listed_on_the_table () {
            for (WebElement eachFile : filesPageFavorite.fileNames) {
                if(eachFile.getText().equals(chosenFileName)){
                    System.out.println("eachFile = " + eachFile.getText());
                    System.out.println("chosenFileName = " + chosenFileName);
                    Assert.assertEquals(chosenFileName, eachFile.getText());
                }
                }

            }



}



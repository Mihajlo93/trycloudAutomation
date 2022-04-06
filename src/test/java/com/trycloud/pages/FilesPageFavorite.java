package com.trycloud.pages;

import com.trycloud.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FilesPageFavorite {
    public FilesPageFavorite() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[contains(@id,'select-files')]")
    public List<WebElement> uploadedFilesSelectors;

    @FindBy(xpath = "//a[contains(@class,'action-menu')]//span[@class='icon icon-more']")
    public List<WebElement> actionBtns;

    @FindBy(xpath = "//div[contains(@class,'fileActionsMenu')]//li[3]")
    public WebElement addToFavBtn;

    @FindBy(xpath = "//span[@class='innernametext']")
    public List<WebElement> fileNames;

    @FindBy(xpath = "//div[contains(@class,'fileActionsMenu')]//a/span[2]")
    public List<WebElement> actionIconOptions;

    @FindBy(xpath = "//ul[@class='with-icon']/li/a")
    public List<WebElement> subModules;

    public void clickModule(String moduleName) {
        String locator = "//ul[@id='appmenu']//span[normalize-space(.)='" + moduleName + "']/..";
        Driver.getDriver().findElement(By.xpath(locator)).click();

    }
}

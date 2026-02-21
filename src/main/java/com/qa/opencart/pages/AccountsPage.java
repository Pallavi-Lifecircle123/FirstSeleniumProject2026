package com.qa.opencart.pages;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    private final By header = By.cssSelector("div#content h2");
    private final By logoutLink = By.xpath("//h2[text()='My Orders']");
    private final By search = By.name("search");
    private final By searchIcon = By.cssSelector("div#search button");
    private final By editAccount = By.xpath("//a[text()='Edit Account']");;

    //Checking new webhook working or not
    public List<String> getAccPageHeaders() {
        List<WebElement> headersList = eleUtil.waitForElementsPresence(header, AppConstant.DEFAULT_SHORT_WAIT);
        System.out.println("Total No of header list size" + headersList.size());
        List<String> headerValList = new ArrayList<String>();
        for (WebElement e: headersList) {
            String text = e.getText();
            headerValList.add(text);
            System.out.println(text);
        }
        return headerValList;
    }
    public Boolean isLogoutLinkExists() {
        WebElement logouEle = eleUtil.waitForElementVisbile(logoutLink, AppConstant.DEFAULT_MEDIUM_WAIT);
        return eleUtil.isElementDisplayed(logouEle);
    }
    public Boolean isEditAccountExists() {
        WebElement editAccounttext = eleUtil.waitForElementVisbile(editAccount, AppConstant.DEFAULT_MEDIUM_WAIT);
        return eleUtil.isElementDisplayed(editAccounttext);
    }

    public SearchReultsPage doSearch(String searchKey) {
        System.out.println("Searching the product: " + searchKey);

        WebElement searchEle= eleUtil.waitForElementVisbile(search,AppConstant.DEFAULT_MEDIUM_WAIT);
        searchEle.clear();
        searchEle.sendKeys(searchKey);
        eleUtil.doClick(searchIcon);
        return new SearchReultsPage(driver);
    }
}
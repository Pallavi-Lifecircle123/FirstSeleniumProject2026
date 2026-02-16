package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.constant.AppConstant;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchReultsPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    public SearchReultsPage(WebDriver driver) {
        this.driver = driver;
        this.eleUtil = new ElementUtil(driver);
    }
    private final By searchResults = By.cssSelector("div.product-thumb");
    private final By  resultsHeader = By.cssSelector("div#content h1");

    public int getSearchResultsCount() {
        int resultCount= eleUtil.waitForElementsPresence(searchResults ,AppConstant.DEFAULT_MEDIUM_WAIT).size();
        System.out.println("Total search results found:" +resultCount);
        return resultCount;
    }
    public String getResultsHeaderValue() {
        String header = eleUtil.doElementGetText(resultsHeader);
        System.out.println("Search results page header text: " + header);
        return header;
    }
    public ProductInfoPage selectProduct(String productName){
        // Implementation for selecting a product from search results
       System.out.println("Selecting product: " + productName);
       eleUtil.doClick(By.linkText(productName));
       return new ProductInfoPage(driver);
    }


}

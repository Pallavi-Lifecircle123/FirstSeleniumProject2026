package com.qa.opencart.pages;
import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ShoppingCartPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    private final By header = By.tagName("h1");
    private final By productImages = By.cssSelector("ul.thumbnails img");

    public String getProductHeader() {
        String headerVal = eleUtil.waitForElementVisbile(header, AppConstant.DEFAULT_SHORT_WAIT).getText();
        System.out.println("Product header value is: " + headerVal);
        return headerVal;
    }


}
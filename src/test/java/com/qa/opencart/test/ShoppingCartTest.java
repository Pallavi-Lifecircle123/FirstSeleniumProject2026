package com.qa.opencart.test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.qa.opencart.base.BaseTest;

public class ShoppingCartTest extends BaseTest {

    @BeforeClass
    public void shoppingCartSetUp() {
            accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test
    public void EnterCartValueTest() {
        searchResultsPage = accPage.doSearch("macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
        String actHeader = productInfoPage.getProductHeader();
        Assert.assertEquals(actHeader, "MacBook Pro");
        shoppingCartPage = productInfoPage.doSearchEnterQuantity("2");
    }

}

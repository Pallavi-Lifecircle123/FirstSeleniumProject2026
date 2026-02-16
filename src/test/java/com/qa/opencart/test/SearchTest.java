package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
@BeforeClass
    public void searchSetUp() {
    accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));

}

    @DataProvider
    public Object[][] getSearchProduct(){
        return new Object[][]{
                {"macbook","MacBook Pro","MacBook Pro"}

        };
    }

@Test(dataProvider = "getSearchProduct")
    public void searchTest(String searchKey,String productName,String exPectedValue) {
     searchResultsPage=accPage.doSearch(searchKey);
     productInfoPage=searchResultsPage.selectProduct(productName);
     String actHeader=productInfoPage.getProductHeader();
     Assert.assertEquals(actHeader,exPectedValue);
    }

}

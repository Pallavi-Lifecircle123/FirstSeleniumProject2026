package com.qa.opencart.test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.qa.opencart.base.BaseTest;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class ProductInfoTest extends BaseTest {

    @BeforeClass
    public void productInfoSetUp() {
        accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }
    @DataProvider
    public Object[][] getProducts(){
        return new Object[][]{
                {"macbook","MacBook Pro"},
                {"samsung","Samsung SyncMaster 941BW"},
                {"imac","iMac"}
        };
     }


    @Test(dataProvider = "getProducts")
    public void productHeaderTest(String searchKey,String productName) {
        searchResultsPage = accPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        String actHeader = productInfoPage.getProductHeader();
        Assert.assertEquals(actHeader, productName);

    }

    @DataProvider
    public Object[][] getProductImages(){
        return new Object[][]{
                {"macbook","MacBook Pro",4},
                {"samsung","Samsung SyncMaster 941BW",1},

        };
    }

    @Test(dataProvider = "getProductImages")
    public void productImageCountTest(String searchKey,String productName,int imgCount) {
        searchResultsPage = accPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        int actImgCount = productInfoPage.getProductImages();
        Assert.assertEquals(actImgCount, imgCount);
    }

    @Test
    public void productClickOnQuantity() {
        searchResultsPage = accPage.doSearch("macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
        String actHeader = productInfoPage.getProductHeader();
        Assert.assertEquals(actHeader, "MacBook Pro");


    }
    @Test
    public void productInfoTest(){
        searchResultsPage = accPage.doSearch("macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
        Map<String,String> productDataMap = productInfoPage.getProductData();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(productDataMap.get("Brand"), "Apple");
        softAssert.assertEquals(productDataMap.get("Availability"), "Out Of Stock");
        softAssert.assertEquals(productDataMap.get("Reward Points"), "800");
        softAssert.assertEquals(productDataMap.get("Product Code"), "Product 18");
        softAssert.assertEquals(productDataMap.get("productprice"), "$2,000.00");
        softAssert.assertEquals(productDataMap.get("extaxprice"), "$2,000.00");
        softAssert.assertAll();
    }

}

package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CommonPageTest extends BaseTest {

    @Test
    public void checkCommonElementsOnLoginPageTest() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(commonsPage.isLogoExist());
        softAssert.assertTrue(commonsPage.isSearchFieldExist());
        List<String> footerList = commonsPage.footerLinksExist();
        softAssert.assertEquals(footerList.size(), AppConstant.DEFAULT_FOOTER_LINKS_COUNT);
        softAssert.assertAll();
    }

    @Test
    public void checkCommonElementsOnAccountsPageTest() {
        loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(commonsPage.isLogoExist());
        softAssert.assertTrue(commonsPage.isSearchFieldExist());
        List<String> footerList = commonsPage.footerLinksExist();
        softAssert.assertEquals(footerList.size(), AppConstant.DEFAULT_FOOTER_LINKS_COUNT);
        softAssert.assertAll();
    }
}

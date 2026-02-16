package com.qa.opencart.test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import com.qa.opencart.pages.AccountsPage;
import org.testng.annotations.Test;

import java.util.List;

public class AccountPageTest extends BaseTest {
    @BeforeClass
    public void accPageSetUp() {
        accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
        System.out.println("successfully logged in....");

    }
    @Test
    public void isLogoutLinkExistTest() {
        Assert.assertTrue(accPage.isLogoutLinkExists());
    }

    @Test
    public void accPageHeadersTest() {
        List <String> actAccHeadersList = accPage.getAccPageHeaders();
        Assert.assertEquals(actAccHeadersList.size(), AppConstant.ACC_PAGE_HEADER_COUNT);
        Assert.assertEquals(actAccHeadersList, AppConstant.expectedAccPageHeaderList);
    }

    @Test
    public void accPagePKEditAccount() {
        Assert.assertTrue(accPage.isEditAccountExists());
        System.out.println("Edit Account is present..."+accPage.isEditAccountExists());
    }

}

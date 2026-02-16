package com.qa.opencart.test;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.pages.AccountsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("EP-100: Design the Open Cart App Login Page")
@Feature("F-101: design open cart login feature")
@Story("US-50: develop login core features: title, url, user is able to login")
public class LoginPageTest extends BaseTest {


    @Description("login page title test....")
    @Owner("Naveen Automation Labs")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void loginPageTitleTest()
    {
        String actTitle=loginPage.getLoginPageTitle();
        ChainTestListener.log("login page tite: "+actTitle);
        Assert.assertEquals(actTitle, AppConstant.LOGIN_PAGE_TITLE);
    }

   /* @Test
    public void loginPageURLTest()
    {
        String actUrl=loginPage.gaetLoginPageUrl();
        Assert.assertTrue(actUrl.contains(AppConstant.LOGIN_PAGE_TITLE));
    }
    *
    */


    @Test
    public void aforgotPwdLinkExistTest()
    {
        Assert.assertTrue(loginPage.isForgorPasswordExist());
    }

    @Test
    public void isHeaderExistTest()
    {
        Assert.assertTrue(loginPage.isHeaderExist());

    }

    @Test
    public void loginTest()
    {
        accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        accPage.isLogoutLinkExists();
    }


}
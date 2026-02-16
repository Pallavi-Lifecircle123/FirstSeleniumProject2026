package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {
//comment added
    @DataProvider
    public Object[][] getNegativeLoginData() {
        return new Object[][]{
                {"testselelettttt@gmail.com", "test@123"},
                {"march2024@open.com", "test@123"},
                {"march2024@@open.com", "test@@123"},
                {"", "test@123"},
                {"", ""}
        };
    }

    @Test(dataProvider = "getNegativeLoginData")
    public void negativeLoginTest(String invalidUN, String invalidPWD) {
        Assert.assertTrue(loginPage.doLoginWithInvalidCredentails(invalidUN, invalidPWD));
    }
}

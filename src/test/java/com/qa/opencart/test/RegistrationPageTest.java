package com.qa.opencart.test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

public class RegistrationPageTest extends BaseTest {
    //Go to Base best Before class-And launch the browser with the URL and open the login page
    @BeforeClass
    public void goToRegistrationPage() {
        registerPage = loginPage.navigateToRegisterPage();
    }

    @DataProvider
    public Object[][] getRegData(){
        return ExcelUtil.getTestData("registration");
    }

    @DataProvider
    public Object[][] getRegSheetData(){
        return ExcelUtil.getTestData("register");
    }

    @DataProvider
    public Object[][] getRegCSVData(){
        return CSVUtil.csvData("register");
    }


    @Test(dataProvider = "getRegCSVData")
    public void registerTest(String firstName, String lastName,String telephone,
                             String password, String confirmPassword, String subscribe) {
        Boolean isRegistered = registerPage.userRegisterMain(firstName, lastName, StringUtils.getRandomEmail(), telephone,
                                                             password, confirmPassword, subscribe);
        Assert.assertTrue(isRegistered, "Registration should be successful");
    }



}

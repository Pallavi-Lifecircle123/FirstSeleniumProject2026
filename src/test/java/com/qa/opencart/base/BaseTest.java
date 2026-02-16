package com.qa.opencart.base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.*;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

//@Listeners(ChainTestListener.class)
//@Listeners({ChainTestListener.class, TestAllureListener.class})
public class BaseTest {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;
    DriverFactory df;
    protected Properties prop;
    protected LoginPage loginPage;
    protected AccountsPage accPage;
    protected SearchReultsPage searchResultsPage;
    protected ProductInfoPage productInfoPage;
    protected ShoppingCartPage shoppingCartPage;
    protected RegisterPage registerPage;
    protected CommonsPage commonsPage;

    @Description("Launch the Browser: {0} and Url")
    @Parameters({"browser"})
    @BeforeTest
    public void setUp(@Optional("chrome") String browserName) {
        df = new DriverFactory();
        prop = df.initProp();
        if(browserName!=null) {
            prop.setProperty("browser", browserName);
        }
        driver = df.initDriver(prop);
        loginPage = new LoginPage(driver);
        commonsPage = new CommonsPage(driver);
    }

    @AfterMethod // will be running after each @test method
    public void attachScreenshot (ITestResult result) {
        if (!result.isSuccess()) {// only for failure test cases -- true
            log.info("--screenshot is taken---");
            ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
        }

    }

    @Description("Closing the Browser")
    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
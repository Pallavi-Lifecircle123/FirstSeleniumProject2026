package com.qa.opencart.pages;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private WebDriver driver;
    private ElementUtil eleUtil;
    //Private By Locator
    private final By emailID = By.id("input-email");
    private final By password = By.id("input-password");
    private final By loginBtn = By.xpath("//input[@value='Login']");
    private final By forgottenPassword = By.xpath("//a[text()='Forgotten Password']");
    private final By logout = By.xpath("//h2[text()='My Orders']");
    private final By header = By.tagName("h2");
    private final By registrationLink = By.tagName("h2");
    private final By loginErrorMessg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    //Public Page Method
    @Step("getting Login page title")
    public String getLoginPageTitle() {
        String title= eleUtil.waitForTitleIs(AppConstant.LOGIN_PAGE_TITLE, AppConstant.DEFAULT_SHORT_WAIT);
        //System.out.println(title);
        log.info("Login page title is: " + title);
        return title;
    }

    public String gaetLoginPageUrl() {
        String url = eleUtil.waitForUrl(AppConstant.LOGIN_PAGE_FRACTION_URL, AppConstant.DEFAULT_SHORT_WAIT);
        //System.out.println(url);
        log.info("Login page url is: " + url);
        return url;
    }

    public boolean isHeaderExist() {

        return eleUtil.isElementDisplayed(header);
    }

    public boolean isForgorPasswordExist() {
       return eleUtil.isElementDisplayed(forgottenPassword);
    }

    public String doLoginMain(String appUsername1, String appPassword1) {
       // System.out.println("Login with " + appUsername1 + " and " + appPassword1);
        log.info("Login with " + appUsername1 + " and " + appPassword1);
        driver.findElement(emailID).sendKeys(appUsername1);
        driver.findElement(password).sendKeys(appPassword1);
        driver.findElement(loginBtn).click();
        return driver.getTitle();
    }

    public AccountsPage doLogin(String appUsername, String appPassword) {
        //System.out.println("Login with " + appUsername + " and " + appPassword);
        log.info("Login with " + appUsername + " and " + appPassword);
        // Ensure we're on the login page
        if (!driver.getCurrentUrl().contains("route=account/login")) {
            driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        }
        // Wait for login page to be ready
        eleUtil.waitForTitleIs(AppConstant.LOGIN_PAGE_TITLE, AppConstant.DEFAULT_SHORT_WAIT);
        eleUtil.waitForElementVisbile(emailID, AppConstant.DEFAULT_MEDIUM_WAIT).sendKeys(appUsername);
        eleUtil.doSendKeys(password, appPassword);
        eleUtil.doClick(loginBtn);
        eleUtil.waitForElementVisbile(logout, 30);
        return new AccountsPage(driver);
    }

    public RegisterPage navigateToRegisterPage() {
        log.info("Navigating to Register page");
        eleUtil.waitForElementsVisible(registrationLink, AppConstant.DEFAULT_MEDIUM_WAIT);
        eleUtil.doClick(By.linkText("Register"));
        return new RegisterPage(driver);
    }

    @Step("login with in-correct username: {0} and password: {1}")
    public boolean doLoginWithInvalidCredentails(String invalidUN, String invalidPWD) {
        eleUtil.waitForElementsVisible(emailID, AppConstant.DEFAULT_MEDIUM_WAIT).clear();
        log.info("Invalid application credentials: " + invalidUN + " : " + invalidPWD);
        WebElement emailEle = eleUtil.waitForElementVisbile(emailID, AppConstant.DEFAULT_MEDIUM_WAIT);
        emailEle.clear();
        emailEle.sendKeys(invalidUN);
        eleUtil.doClick(loginBtn);
        String errorMessg = eleUtil.doElementGetText(loginErrorMessg);
        log.info("invalid creds error messg: " + errorMessg);
        if (errorMessg.contains(AppConstant.LOGIN_BLANK_CREDS_MESSG)) {
            return true;
        } else if (errorMessg.contains(AppConstant.LOGIN_INVALID_CREDS_MESSG)) {
            return true;
        }
        return false;
    }

}
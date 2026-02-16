package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.constant.AppConstant;



public class RegisterPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.eleUtil = new ElementUtil(driver);
    }

    private final By firstName = By.id("input-firstname");
    private final By lastName = By.id("input-lastname");
    private final By email = By.id("input-email");
    private final By telephone = By.id("input-telephone");
    private final By password = By.id("input-password");
    private final By confirmPassword = By.id("input-confirm");
    private final By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
    private final By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
    private final By agreeCheckBox = By.name("agree");
    private final By continueBtn = By.xpath("//input[@value='Continue' and @type='submit']");
    private final By successMessage = By.cssSelector("div#content h1");
    private final By logoutLink = By.linkText("Logout");
    private final By registerLink = By.linkText("Register");

    public Boolean userRegisterMain(String firstName, String lastName, String email,String telephone,
                                  String password, String confirmPassword, String subscribe) {
        eleUtil.waitForElementVisbile(this.firstName, AppConstant.DEFAULT_MEDIUM_WAIT).sendKeys(firstName);
        eleUtil.doSendKeys(this.lastName, lastName);
        eleUtil.doSendKeys(this.email, email);
        eleUtil.doSendKeys(this.telephone, telephone);
        eleUtil.doSendKeys(this.password, password);
        eleUtil.doSendKeys(this.confirmPassword, confirmPassword);

        if (subscribe.equalsIgnoreCase("yes")) {
            eleUtil.doClick(subscribeYes);
        } else {
            eleUtil.doClick(subscribeNo);
        }

        eleUtil.doClick(agreeCheckBox);
        eleUtil.doClick(continueBtn);
        String successMsg = eleUtil.waitForElementVisbile(successMessage, AppConstant.DEFAULT_MEDIUM_WAIT).getText();
        System.out.println("Account registration success message: " + successMsg);

        if(successMsg.contains(AppConstant.USER_REGISTER_SUCCESS_MESSAGE)) {
            eleUtil.doClick(logoutLink);
            eleUtil.doClick(registerLink);
            return true;
        } else {
            return false;
        }
    }

}




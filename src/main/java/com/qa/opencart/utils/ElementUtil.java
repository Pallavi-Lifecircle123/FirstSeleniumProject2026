package com.qa.opencart.utils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.qa.opencart.exceptions.ElementException;
import com.qa.opencart.factory.DriverFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ElementUtil {

    private static final Logger log = LoggerFactory.getLogger(ElementUtil.class);
    private WebDriver driver;
    private Actions act;
    private JavaScriptUtil jsUtil;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        act = new Actions(driver);
        jsUtil = new JavaScriptUtil(driver);
    }

    public int getElementsCount(By locator) {
        return driver.findElements(locator).size();
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public List<String> getElementsTextList(By locator) {
        List<WebElement> eleList = getElements(locator);
        List<String> eleTextList = new java.util.ArrayList<String>();
        for (WebElement e : eleList) {
            String text = e.getText();
            if (!text.isEmpty()) {
                eleTextList.add(text);
            }
        }
        return eleTextList;
    }

    public boolean isElementExist(By locator) {

        if (getElementsCount(locator) == 1) {
            System.out.println("the element" + locator + " is present on the page one time");
            return true;
        } else {
            System.out.println("Element is NOT present on the page: " + locator);
            return false;
        }
    }

    public boolean isElementExist(By locator, int expectedEleCount) {

        if (getElementsCount(locator) == expectedEleCount) {
            System.out.println("the element" + locator + " is present on the page one time" + expectedEleCount);
            return true;
        } else {
            System.out.println("Element " + locator + "is not prestnt on the page with expected count: " + expectedEleCount);
            return false;
        }
    }


    public void clickElement(By locator, String eleText) {
        List<WebElement> eleList = getElements(locator);
        System.out.println("Total elements found with locator: " + locator + " is: " + eleList.size());
        for (WebElement e : eleList) {
            String text = e.getText();
            System.out.println(text);
            if (text.equals(eleText)) {
                e.click();
                break;
            }
        }
    }

    public void doSearch(By searchLocator, String searchKey, By suggestionsLocator, String suggestionValue) throws InterruptedException {
        doSendKeys(searchLocator, searchKey);
        Thread.sleep(4000);
        List<WebElement> eleList = getElements(suggestionsLocator);
        System.out.println("Total suggestions: " + eleList.size());
        for (WebElement e : eleList) {
            String text = e.getText();
            System.out.println(text);
            if (text.equals(suggestionValue)) {
                e.click();
                break;
            }
        }
    }

    //Select dROP dOWN Utils***
    public void doSelectByIndex(By locator, int index) {
        Select select = new Select(getElement(locator));
        select.selectByIndex(index);
    }

    public void doSelectByVisibleText(By locator, String eleText) {
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(eleText);
    }


    public void doSelectByValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }

    public int getDropDownOptionsCount(By locator) {
        Select select = new Select(getElement(locator));
        return select.getOptions().size();
    }

    public List<String> getDropDownOptionValueList(By locator) {
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();
        System.out.println("Total number of options: " + optionsList.size());
        List<String> optionsTextList = new ArrayList<String>();
        for (WebElement e : optionsList) {
            String text = e.getText();
            optionsTextList.add(text);
        }
        return optionsTextList;
    }

    public void selectDropDownValue(By locator, String value) {
        List<WebElement> optionsList = getElements(locator);
        System.out.println(optionsList.size());
        for (WebElement e : optionsList) {
            String text = e.getText();
            if (text.contains(value)) {
                e.click();
                break;
            }
        }
    }

    public void doSendKeys(By locator, String value) {
        log.info("==entering locator"+locator+"entering value"+value);
        if (value == null) {
            log.error("==value is null==");
            throw new ElementException("==value can not be null==");
        }
        WebElement ele =  getElement(locator);
            ele.clear();
            ele.sendKeys(value);
    }

    public void doMultipleSendKeys(By locator, CharSequence... value) {
        getElement(locator).sendKeys(value);
    }

    public void doClick(By locator) {
        getElement(locator).click();
    }

    public String doElementGetText(By locator) {
        return getElement(locator).getText();
    }

    public boolean isElementCheck(By locator) {
        try {
            return getElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            throw new ElementException("==Element not found==");
        }
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return getElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("==Element not dispalyed on this section==");
            return false;
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("==Element not dispalyed on this section==");
            return false;
        }
    }

    public boolean isElementEnabled(By locator) {
        try {
            return getElement(locator).isEnabled();
        } catch (NoSuchElementException e) {
            System.out.println("Element is not dispalyed on the page" + locator);
            return false;
        }
    }

    public String getElementDOMAttributeValue(By locator, String attrName) {
        return getElement(locator).getDomAttribute(attrName);
    }

    public String getElementDOMPropertyValue(By locator, String propName) {
        return getElement(locator).getDomProperty(propName);
    }

    public WebElement getElement(By locator, int timeOut) {
        return driver.findElement(locator);
    }

    public WebElement getElement(By locator) {
        WebElement element = driver.findElement(locator);
        if (Boolean.parseBoolean(DriverFactory.highlightEle)) {
            jsUtil.flash(element);
        }
        return element;
    }

    public int getElementSCount(By locator) {
        return getElements(locator).size();
    }

    @Step("wait for Page title {0}")
    public String waitForTitleIs(String expectedTitleValue, int timeOut) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            wait.until(ExpectedConditions.titleIs(expectedTitleValue));
        } catch (TimeoutException e) {
            System.out.println("Title is not found within the given time: " + timeOut + " seconds");
        }
        return driver.getTitle();
    }

    public String waitForUrlContans(String fractionURLValue, int timeOut) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            wait.until(ExpectedConditions.urlContains(fractionURLValue));
        } catch (TimeoutException e) {
            System.out.println("Title is not found within the given time: " + timeOut + " seconds");
        }
        return driver.getCurrentUrl();
    }

    public String waitForUrl(String expUrlValue, int timeOut) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            wait.until(ExpectedConditions.urlToBe(expUrlValue));
        } catch (TimeoutException e) {
            System.out.println("Title is not found within the given time: " + timeOut + " seconds");
        }
        return driver.getCurrentUrl();
    }



//**** Action utils *****

    private void moveToElement(By locator) {
        act.moveToElement(getElement(locator)).perform();
    }

    public void menuSubMenuHandLingLevel2(By parentMenu, By childMenu) throws InterruptedException {
        moveToElement(parentMenu);
        Thread.sleep(2000);
        getElement(childMenu).click();
    }

    public void menuSubMenuHandLingLevel3(By menuLevel1, By menuLevel2,By menuLevel3) throws InterruptedException {
        doClick(menuLevel1);
        Thread.sleep(2000);
        moveToElement(menuLevel2);
        Thread.sleep(2000);
        doClick(menuLevel3);
    }

    public void menuSubMenuHandLingLevel3(By menuLevel1, By menuLevel2,By menuLevel3,By menulevel4,String actionType) throws InterruptedException {
        if (actionType.equalsIgnoreCase("click")) {
            doClick(menuLevel1);
        }
        else if (actionType.equalsIgnoreCase("mousehover")){
            moveToElement(menuLevel1);
    }
        Thread.sleep(1000);
        moveToElement(menuLevel2);
        Thread.sleep(1000);
        moveToElement(menuLevel3);
        Thread.sleep(1000);
        doClick(menuLevel3);
    }

    public void doActionSendKeys(By locator, String value) {
        act.sendKeys(getElement(locator), value).perform();
    }
    public void doActionClick(By locator) {
        act.click(getElement(locator)).perform();
    }

    public void doSendKeysWithPause(By Locator, String value,long pauseTime) {
        if (value == null) {
            throw new RuntimeException("===value cannot be null===");
        }
        char val[] = value.toCharArray();
        for(char ch :val)
        {
         act.sendKeys(getElement(Locator), String.valueOf(ch)).pause(pauseTime).perform();
        }
        }

    public void doSendKeysWithPause(By Locator, String value) {
        if (value == null) {
            throw new RuntimeException("===value cannot be null===");
        }
        char val[] = value.toCharArray();
        for (char ch : val) {
            act.sendKeys(getElement(Locator), String.valueOf(ch)).pause(200).perform();
        }

    }
    //**** Wait Utils *****

    public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public WebElement waitForElementPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        if(Boolean.parseBoolean(DriverFactory.highlightEle)) {
            jsUtil.flash(element);
        }
        return element;
    }
    public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
        log.info("Waiting for elements to be visible: " + locator + " with timeout: " + timeOut + " seconds");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public WebElement waitForElementVisbile(By locator, int timeOut) {
        log.info("Waiting for element to be visible: " + locator + " with timeout: " + timeOut + " seconds");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        if(Boolean.parseBoolean(DriverFactory.highlightEle)) {
            jsUtil.flash(element);
        }
        return element;
    }


}
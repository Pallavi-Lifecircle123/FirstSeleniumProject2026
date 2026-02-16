package com.qa.opencart.utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    private WebDriver driver;
    private JavascriptExecutor js;

    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public void flash(WebElement element) {
        String bgColor = element.getCssValue("backgroundColor"); //grey
        for (int i = 0; i < 7; i++) {
            changeColor("rgb(0,200,0)", element); //green
            changeColor(bgColor, element); //grey
        }

    }

    private void changeColor(String color, WebElement element) {

        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getTitleByJS() {
        return js.executeScript("return document.title;").toString();
    }


    public String getURLByJS() {
        return js.executeScript("return document.URL;").toString();
    }
}

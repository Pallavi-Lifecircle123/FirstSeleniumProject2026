package com.qa.opencart.pages;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    
    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    private final By header = By.tagName("h1");
    private final By productImages = By.cssSelector("ul.thumbnails img");
    private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
    private final By quantityInput = By.id("input-quantity");
    private final By addToCart = By.cssSelector("button#button-cart, input#button-cart");
    
    private Map<String, String> productMap;

    public String getProductHeader() {
        String headerVal = eleUtil.waitForElementVisbile(header, AppConstant.DEFAULT_SHORT_WAIT).getText();
        System.out.println("product header is --->" + headerVal);
        return headerVal;
    }

    public int getProductImages() {
        int imagesCount = eleUtil.waitForElementsVisible(productImages, AppConstant.DEFAULT_MEDIUM_WAIT).size();
        System.out.println("Total number of images: " + imagesCount);
        return imagesCount;
    }

    public Map<String,String> getProductData() {
        productMap = new LinkedHashMap<String, String>();
        productMap.put("productname", getProductHeader());
        productMap.put("productimages", String.valueOf(getProductImages()));
        getProductMetaData();
        getProductPriceData();
        System.out.println("===product data:=== \n" + productMap);
        return productMap;
    }

    public void getProductMetaData() {
        List<WebElement> metaList = eleUtil.waitForElementsVisible(productMetaData, AppConstant.DEFAULT_SHORT_WAIT);
        System.out.println("total meta data: " + metaList.size());
        for (WebElement e : metaList) {
            String metaData = e.getText();
            String meta[] = metaData.split(":");
            String metaKey = meta[0].trim();
            String metaValue = meta[1].trim();
            productMap.put(metaKey, metaValue);
        }
    }

    // $2,000.00
    // Ex Tax: $2,000.00

    public void getProductPriceData() {
        List<WebElement> priceList = eleUtil.waitForElementsVisible(productPriceData, AppConstant.DEFAULT_SHORT_WAIT);
        System.out.println("total price data: " + priceList.size()); // 2
        String priceValue = priceList.get(0).getText().trim();
        String exTaxValue = priceList.get(1).getText().split(":")[1].trim();
        productMap.put("productprice", priceValue);
        productMap.put("extaxprice", exTaxValue);
    }

    public ShoppingCartPage doSearchEnterQuantity(String quantity) {
        System.out.println("Entering quantity: " + quantity);
        eleUtil.waitForElementVisbile(quantityInput, AppConstant.DEFAULT_MEDIUM_WAIT).clear();
        eleUtil.doSendKeys(quantityInput, quantity);
        eleUtil.doClick(addToCart);
        return new ShoppingCartPage(driver);
    }

    // Brand: Apple
    // Product Code: Product 18
    // Reward Points: 800
    // Availability: Out Of Stock

}

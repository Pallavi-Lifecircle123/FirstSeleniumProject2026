package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {
    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;
    private EdgeOptions ed;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            co.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            co.addArguments("--incognito");
        }
        // Note: browserName is automatically set by ChromeOptions, no need to set it manually
        return co;
    }

    public FirefoxOptions getFirefoxOptions() {
        fo = new FirefoxOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))) {
            fo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            fo.addArguments("--incognito");
        }
        // Note: browserName is automatically set by FirefoxOptions, no need to set it manually
        return fo;
    }
    public EdgeOptions getEdgeOptions() {
        ed = new EdgeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))) {
            ed.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("edge"))){
            ed.addArguments("--edge");
        }
        // Note: browserName is automatically set by EdgeOptions, no need to set it manually
        return ed;
    }


}

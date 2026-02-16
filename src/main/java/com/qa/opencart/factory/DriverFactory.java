package com.qa.opencart.factory;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameWorkException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DriverFactory {
    public WebDriver driver;
    public OptionsManager optionsManager;
    public Properties prop;
    public static String highlightEle;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    /**
     *  This method initializes the WebDriver based on the provided browser name. It supports Chrome, Firefox, Edge, and Safari browsers. If an invalid browser name is provided, it throws a FrameWorkException with an appropriate error message.
     * @param prop Properties object containing configuration settings, including the browser name.
     * @return it returns WebDriver instance
     */
//new comment
    public WebDriver initDriver(Properties prop) {
        /**
         * This method initializes the WebDriver based on the browser name
         * specified in the provided Properties object. It supports Chrome,
         * Firefox, Edge, and Safari browsers. If an invalid browser name is
         * provided, it throws a FrameWorkException with an appropriate error message.
         *
         * @param prop Properties object containing configuration settings,
         *             including the browser name.
         * @return WebDriver instance initialized for the specified browser.
         */
        String browserName = prop.getProperty("browser");
        //System.out.println("Initializing WebDriver" +browserName);
        log.info("Initializing WebDriver for browser: " + browserName);
        ChainTestListener.log("browser is: "+browserName);

        highlightEle = prop.getProperty("highlight");
        optionsManager= new OptionsManager(prop);

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                //driver = new ChromeDriver();
                //for headless mode
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                break;
            case "edge":
                //driver = new EdgeDriver();
                tlDriver.set(new EdgeDriver());
                break;
            case "safari":
                //driver = new SafariDriver();
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setUseTechnologyPreview(false);
                tlDriver.set(new SafariDriver(safariOptions));
                break;
            default:
                //System.out.println(AppError.INVALID_BROWSER_MESSAGE);
                log.error(AppError.INVALID_BROWSER_MESSAGE);
                throw new FrameWorkException("====INVALID  MESSAGE ====");
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));
        return getDriver();

    }

    /**
     * This method initializes the Properties object
     * and loads the configuration properties from a
     * specified file path. It handles potential exceptions
     * such as FileNotFoundException and IOException, printing the
     * stack trace for debugging purposes. Finally,
     * it returns the loaded Properties object.
     * @return
     */


    /**
     * This method initializes the Properties object and loads the configuration properties from a specified file path. It handles potential exceptions such as FileNotFoundException and IOException, printing the stack trace for debugging purposes. Finally, it returns the loaded Properties object.
     *
     * @return Properties object containing the loaded configuration properties.
     */

    public static WebDriver getDriver() {
        return tlDriver.get();
    }



    public Properties initProp() {
        prop = new Properties();
        FileInputStream ip = null;
        String envName = System.getProperty("env");
        if (envName != null) {
            envName = envName.trim().toLowerCase();
        }
        log.info("Env name: " + envName);

        try {
            if (envName == null) {
                log.warn("no env.. is passed, hence running tcs on QA environment...");
                ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
            } else {
                switch (envName) {
                    case "qa":
                        ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
                        break;
                    case "stage":
                        ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
                        break;
                    case "uat":
                        ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
                        break;
                    case "dev":
                        ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
                        break;
                    case "prod":
                        ip = new FileInputStream("./src/test/resources/config/config.properties");
                        break;
                    default:
                        log.error("Env value is invalid...plz pass the right env value..");
                        throw new FrameWorkException("====INVALID ENVIRONMENT====");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } try{
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static File getScreenshotFile() {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE); // temp dir
        return srcFile;
    }

    public static byte[] getScreenshotByte() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES); // temp dir
    }

    public static String getScreenshotBase64() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64); // temp dir
    }

}


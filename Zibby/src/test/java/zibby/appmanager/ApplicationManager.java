package zibby.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private final String browser;
    WebDriver wd;

    private RetailerHelper retailerHelper;
    private CustomerHelper customerHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
        
    }


    public void init() throws IOException {
        String target = System.getProperty("target","local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if (browser.equals(BrowserType.FIREFOX)){
            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        }   else if (browser.equals(BrowserType.CHROME)){
            wd = new ChromeDriver();
        }   else if (browser.equals(BrowserType.IE)){
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseURL"));


        retailerHelper = new RetailerHelper(wd);
        customerHelper = new CustomerHelper(wd);

        }

    public HttpSessionHelper phoneInfo() {
        return new HttpSessionHelper(this);
    }

    public void stop() {
        wd.quit();
    }

    public RetailerHelper retailer() {
        return (retailerHelper);
    }


    public CustomerHelper customer() {   return (customerHelper); }

    public String getProperty1(String key) {
        return properties.getProperty(key);
    }
}

package training.selenium.appmanagaer;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import training.selenium.tests.TestBase;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public EventFiringWebDriver driver;
    public WebDriverWait wait;
    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by +  " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
        }
    }


    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private  HelperBase helperBase;
    private  AdminHelper adminHelper;
    private LiteCartHelper liteCartHelper;

    public void init() {
        driver = new EventFiringWebDriver(new ChromeDriver());
        //driver.register(new MyListener());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

        liteCartHelper = new LiteCartHelper(driver,wait);
        adminHelper = new AdminHelper(driver,wait);
        helperBase = new HelperBase(driver,wait);
    }

    public void stop() {
        driver.quit();
        driver = null;
    }

    public LiteCartHelper getLiteCartHelper() {
        return liteCartHelper;
    }

    public AdminHelper getAdminHelper() {
        return adminHelper;
    }

    public HelperBase getHelperBase() {
        return helperBase;
    }
}

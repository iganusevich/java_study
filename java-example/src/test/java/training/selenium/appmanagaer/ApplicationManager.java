package training.selenium.appmanagaer;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public WebDriver driver;
    public WebDriverWait wait;


    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private  HelperBase helperBase;
    private  AdminHelper adminHelper;
    private LiteCartHelper liteCartHelper;

    public void init() {
        driver = new ChromeDriver();
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

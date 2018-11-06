package training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class TestBase {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;

    public boolean areElementsPresent(By locator ){
        return driver.findElements(locator).size()>0;
    }

    public boolean areElementsPresentInWebElem(WebElement element, By locator){
      return element.findElements(locator).size()>0;
    }

    public List<String> getTextFromListObjects(By locator){
        return driver.findElements(locator)
                .stream()
                .map((e)-> e.getText())
                .collect(Collectors.toList());
    }

    public boolean isAlphabetic(List<String> original){
        List<String> ordered = new ArrayList<>(original);
        return ordered.equals(original);

    }

    public void loginAdmin(){
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@value='Login']")).click();
        wait.until(titleIs("My Store"));
    }

    public List<String> collectPoductInfo(By locator1, By locator2, By locator3, WebElement we){
        List<String> list = new ArrayList<>();
        list.add(we.findElement(locator1).getText());
        list.add(we.findElement(locator2).getText());
        list.add(we.findElement(locator3).getText());
        return list;
    }

    @Before
    public void start() {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            return;
        }
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, false);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        tlDriver.set(driver);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
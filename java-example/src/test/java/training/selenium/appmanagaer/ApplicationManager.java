package training.selenium.appmanagaer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ApplicationManager {
    public WebDriver driver;
    public WebDriverWait wait;


    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private  AdminHelper adminHelper;
    private LiteCartHelper liteCartHelper;

    public boolean areElementsPresent(By locator ){
        return driver.findElements(locator).size()>0;
    }

    public void openPage(String s) {
        driver.navigate().to(s);
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

    public void login(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@value='Login']")).click();
        wait.until(titleIs("My Store"));
    }

    public void loginAdmin(){
        driver.navigate().to("http://localhost/litecart/admin/");
        login("admin", "admin");
    }

    public void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

        liteCartHelper = new LiteCartHelper(driver);
        adminHelper = new AdminHelper(driver, wait);
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
}

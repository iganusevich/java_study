package training.selenium.appmanagaer;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class HelperBase {

    public WebDriver driver;
    public WebDriverWait wait;

    public HelperBase(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

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

    public List<String> getValueFromListObjects(By locator){
        return driver.findElements(locator)
                .stream()
                .map((e)-> e.getCssValue("value"))
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

    public void openLiteCart(){
        openPage("http://localhost/litecart/");
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (InvalidSelectorException ex) {
            throw ex;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}

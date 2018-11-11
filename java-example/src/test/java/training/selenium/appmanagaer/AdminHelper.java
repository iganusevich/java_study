package training.selenium.appmanagaer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AdminHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    public AdminHelper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openMenuItem(String item) {
        driver.findElement(By.xpath("//span[. = '" + item + "']")).click();
    }

    public void openCategoryHomePage(String category) {
        driver.findElement(By.xpath("//a[. = '" + category + "']")).click();
    }


}

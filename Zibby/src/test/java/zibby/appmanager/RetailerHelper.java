package zibby.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class RetailerHelper extends HelperBase{

    public RetailerHelper(WebDriver wd) {
        super(wd);
    }

    public void retailerLogin(String username, String password) {
        wd.switchTo().frame(wd.findElement(By.cssSelector("iframe[ng-src*='login/select']")));
        waitUntilElementPresent(By.linkText("Log in"));
        click(By.cssSelector("a[href*='/retailer']"));
        waitUntilElementPresent(By.name("username"));
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.xpath("//button[@button-text='Continue']"));
        //wd.switchTo().defaultContent();
        waitUntilElementPresent(By.xpath("//*[@class='td-string status-current']"));

    }

    public void openApplication(String applicationNumber) {
        click(By.cssSelector("a[href*='" + applicationNumber + "']"));
        waitUntilElementPresent(By.xpath("//*[@class='customer-id ng-binding']"));
    }
}

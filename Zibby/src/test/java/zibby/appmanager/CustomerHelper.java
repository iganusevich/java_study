package zibby.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CustomerHelper extends HelperBase {


    public CustomerHelper(WebDriver wd) {  super(wd);      }


    public void initiatePreApproval(String phone) {
        boolean isPhoneValid = false;
        while (!isPhoneValid) {
            waitUntilElementPresent(By.xpath("//a[@class ='btn-zibby-preapprove']"));
            click(By.xpath("//a[@class ='btn-zibby-preapprove']"));
            wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            wd.switchTo().frame(wd.findElement
                    (By.cssSelector("iframe[src*='https://www.zibby.com/ng/plugin']")));
            type(By.xpath("//input[@name='phone']"), phone);
            click(By.cssSelector("input[ng-model*='phone.verification.privacyPolicy']"));
            click(By.cssSelector("input[ng-model*='phone.verification.creditCheck']"));
            click(By.cssSelector("button[button-text*='Continue']"));
            wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            isPhoneValid = isPhoneValid();
        }
        }

    public void enterCode(String code) {
        //wd.switchTo().frame(wd.findElement
                //(By.cssSelector("iframe[src*='https://www.zibby.com/ng/plugin']")));
        type(By.xpath("//input[@type='tel']"),code);
        click(By.cssSelector("span[ng-bind*='vm.buttonText']"));
        }

    public boolean isPhoneValid() {
        wd.switchTo().frame(wd.findElement
                (By.cssSelector("iframe[src*='https://www.zibby.com/ng/plugin']")));
        return isElementPresent(By.xpath("//input[@type='tel']"));
    }
}

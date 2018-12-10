package appmanager;

import models.Advisor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Set;

public class AdvisorHelper extends HelperBase {
    public AdvisorHelper(WebDriver wd, WebDriverWait wait) {
        super(wd, wait);
    }

    public void loginAsAdvisor(Advisor advisor) {
        //wd.navigate().to("http://ww3.smgny.org/login.html");
        wd.navigate().to("https://www.stockmarketgame.org/login.html");
        wd.findElement(By.cssSelector("input[name=ACCOUNTNO]")).sendKeys(advisor.getLogin());
        wd.findElement(By.cssSelector("input[name=USER_PIN]")).sendKeys(advisor.getPassword());
        wd.findElement(By.cssSelector("input[value=Login]")).click();
        //wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("a#take-tour"))));
    }

    public void closeNewsPopUp() {
        String mainWindow = wd.getWindowHandle();
        Set<String> oldWindows = wd.getWindowHandles();
        for (String window : oldWindows){
            if(!window.equals(mainWindow)){
                wd.switchTo().window(window);
                Assert.assertTrue(isElementPresent
                        (By.xpath("//td[text() = 'Page 1 News, From your Local Coordinator']")));
                wd.close();
                wd.switchTo().window(mainWindow);
            }
        }
    }

    public void viewRankings() {
        String mainWindow = wd.getWindowHandle();
        wd.findElement(By.cssSelector("a[href*=viewrankings]")).click();
        Set<String> oldWindows = wd.getWindowHandles();

    }
}

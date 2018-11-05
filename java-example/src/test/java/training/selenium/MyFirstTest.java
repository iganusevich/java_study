package training.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest extends TestBase {



    @Test
    public void myFirstTest() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@value='Login']")).click();
        wait.until(titleIs("My Store"));
        List<String> menu_names = driver.findElements(By.xpath("//span[@class = 'name']"))
                .stream()
                .map((we)-> we.getText())
                .collect(Collectors.toList());
        for (String name : menu_names){
            driver.findElement(By.xpath("//span[.='" + name + "']")).click();
            assertTrue(areElementsPresent(By.cssSelector("h1")));
            List<String> sub_menu_names = driver.findElements(By.cssSelector("ul#box-apps-menu ul span"))
                    .stream()
                    .map((we)-> we.getText())
                    .collect(Collectors.toList());
            for (String sub_name : sub_menu_names){
                driver.findElement(By.xpath("//span[.='" + sub_name + "']")).click();
                assertTrue(areElementsPresent(By.cssSelector("h1")));
            }
        }
    }

    @Test
    public void mySecondTest(){
        driver.navigate().to("http://localhost/litecart/");
        driver.findElement(By.xpath("//a[. = 'Popular Products']")).click();
        List<WebElement> items = driver.findElements(By.cssSelector("div#popular-products div[data-name]"));
        for (WebElement item : items){
            areElementsPresentInWebElem(item, By.cssSelector("div.sticker"));
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}

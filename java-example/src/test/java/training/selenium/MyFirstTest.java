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

import java.util.ArrayList;
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

    @Test
    public void myThirdTest(){
        loginAdmin();
        driver.findElement(By.xpath("//span[. = 'Countries']")).click();
        List<String> countries = getTextFromListObjects(By.xpath("//tbody//td/a[not(@title)]"));
        assertTrue(isAlphabetic(countries));


        List<String> indexes = getTextFromListObjects(By.xpath("//tbody/tr/td[4]"));
        for (String index : indexes){
             WebElement raw = driver.findElement(By.xpath("//td[. = '" + index + "']/.."));
            int num_zones = Integer.parseInt(raw.findElement(By.cssSelector("td.text-center")).getText());
            if (num_zones > 0){
                raw.findElement(By.xpath(".//a[not(@title)]")).click();
                List<String> zones = driver.findElements(By.cssSelector("input[name$='[name]']"))
                        .stream()
                        .map((e)-> e.getAttribute("value"))
                        .collect(Collectors.toList());
                assertTrue(isAlphabetic(zones));
                driver.findElement(By.xpath("//span[. = 'Countries']")).click();
            }

        }
        
        driver.findElement(By.xpath("//span[. = 'Geo Zones']")).click();
        List<String> geo_zones = getTextFromListObjects(By.xpath("//tbody//a[not(@title)]"));
        for (String zone : geo_zones){
            driver.findElement(By.xpath("//a[. = '" + zone + "']")).click();
            List<String> zones = getTextFromListObjects(By.xpath("//input[contains(@name, 'country_code')]/.."));
            assertTrue(isAlphabetic(zones));
            
        }
    }

    @Test
    public void myFourthTest(){
        int product_num = 0;
        driver.navigate().to("http://localhost/litecart/");
        List<WebElement> camp_prod = driver.findElements(By.cssSelector("div#box-campaign-products a.link"));
//        List<String> item_att_home = collectPoductInfo(By.cssSelector("div.name"),
//                By.cssSelector("s.regular-price"), By.cssSelector("strong.campaign-price"),camp_prod.get(product_num));
//
//        camp_prod.get(product_num).click();
//        List<String> item_att_product = collectPoductInfo(By.cssSelector("h1.title"),
//                By.cssSelector("del.regular-price"),
//                By.cssSelector("strong.campaign-price"),
//                driver.findElement(By.cssSelector("div#box-product")));
//        assertTrue(item_att_home.equals(item_att_product));

        String color = camp_prod.get(product_num).findElement(By.cssSelector("s.regular-price")).getCssValue("color");

        int i = 0;
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}

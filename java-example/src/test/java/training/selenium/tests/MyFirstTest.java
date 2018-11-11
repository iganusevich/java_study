package training.selenium.tests;


import org.junit.Test;
import org.openqa.selenium.*;
import training.selenium.models.Product;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class MyFirstTest extends TestBase {



    @Test
    public void myFirstTest() {
        app.openPage("http://localhost/litecart/admin/");
        app.login("admin", "admin");
        clickAllMenuItems();
    }

    private void clickAllMenuItems() {
        List<String> menu_names =  app.getTextFromListObjects(By.xpath("//span[@class = 'name']"));
        for (String name : menu_names){
            app.driver.findElement(By.xpath("//span[.='" + name + "']")).click();
            assertTrue(app.areElementsPresent(By.cssSelector("h1")));
            List<String> sub_menu_names = app.getTextFromListObjects(By.cssSelector("ul#box-apps-menu ul span"));
            for (String sub_name : sub_menu_names){
                app.driver.findElement(By.xpath("//span[.='" + sub_name + "']")).click();
                assertTrue(app.areElementsPresent(By.cssSelector("h1")));
            }
        }
    }





    @Test
    public void mySeondTest(){
        app.openPage("http://localhost/litecart/");
        app.getAdminHelper().openCategoryHomePage("Popular Products");
        checkStickers();
    }

    private void checkStickers() {
        List<WebElement> items = app.driver.findElements(By.cssSelector("div#popular-products div[data-name]"));
        for (WebElement item : items){
            app.areElementsPresentInWebElem(item, By.cssSelector("div.sticker"));
        }
    }



    @Test
    public void myThirdTest(){
        app.loginAdmin();
        app.getAdminHelper().openMenuItem("Countries");
        List<String> countries = app.getTextFromListObjects(By.xpath("//tbody//td/a[not(@title)]"));
        assertTrue(app.isAlphabetic(countries));
        checkCountrieZonesAlphabetical();
        app.getAdminHelper().openMenuItem("Geo Zones");
        checkGeoZonesAlphabetical();
    }

    private void checkGeoZonesAlphabetical() {
        List<String> geo_zones = app.getTextFromListObjects(By.xpath("//tbody//a[not(@title)]"));
        for (String zone : geo_zones){
            app.driver.findElement(By.xpath("//a[. = '" + zone + "']")).click();
            List<String> zones = app.getTextFromListObjects(By.xpath("//input[contains(@name, 'country_code')]/.."));
            assertTrue(app.isAlphabetic(zones));

        }
    }

    private void checkCountrieZonesAlphabetical() {
        List<String> indexes = app.getTextFromListObjects(By.xpath("//tbody/tr/td[4]"));
        for (String index : indexes){
             WebElement raw = app.driver.findElement(By.xpath("//td[. = '" + index + "']/.."));
            int num_zones = Integer.parseInt(raw.findElement(By.cssSelector("td.text-center")).getText());
            if (num_zones > 0){
                raw.findElement(By.xpath(".//a[not(@title)]")).click();
                List<String> zones = app.driver.findElements(By.cssSelector("input[name$='[name]']"))
                        .stream()
                        .map((e)-> e.getAttribute("value"))
                        .collect(Collectors.toList());
                assertTrue(app.isAlphabetic(zones));
                app.getAdminHelper().openMenuItem("Countries");
            }

        }
    }



    @Test
    public void myFourthTest(){
        int product_num = 0;
        String tab = "campaign";
        app.openPage("http://localhost/litecart/");
        Product product_main_page = app.getLiteCartHelper().getProductMainPage(product_num,tab);
        Product product_pop_up = app.getLiteCartHelper().getProductPopUp(product_num,tab);
        checkNamesPricesEqual(product_main_page, product_pop_up);
        checkProductFormatted(product_main_page);
        checkProductFormatted(product_pop_up);
    }

    private void checkNamesPricesEqual(Product product_main_page, Product product_pop_up) {
        assertTrue(product_main_page.areNamesEqual(product_pop_up));
        assertTrue(product_main_page.areRegPricesEqual(product_pop_up));
        assertTrue(product_main_page.areDiscPricesEqual(product_pop_up));
    }

    private void checkProductFormatted(Product product) {
        assertTrue(product.isRegPriceCrossed());
        assertTrue(product.getRegularPriceColor().isGray());
        assertTrue(product.isDiscPriceStrong());
        assertTrue(product.getDiscountPriceColor().isRed());
        assertTrue(product.isDiscPriceBigger());
    }

}

package training.selenium.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;
import training.selenium.models.Product;
import training.selenium.models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class MyFirstTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validUsersFromJSON() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null){
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<User> users = gson.fromJson(json, new TypeToken<List<User>>(){}.getType());
        return users.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }



    @Test
    public void myFirstTest() {
        app.getHelperBase().openPage("http://localhost/litecart/admin/");
        app.getHelperBase().login("admin", "admin");
        clickAllMenuItems();
    }

    private void clickAllMenuItems() {
        List<String> menu_names =  app.getHelperBase().getTextFromListObjects(By.xpath("//span[@class = 'name']"));
        for (String name : menu_names){
            app.driver.findElement(By.xpath("//span[.='" + name + "']")).click();
            assertTrue(app.getHelperBase().areElementsPresent(By.cssSelector("h1")));
            List<String> sub_menu_names = app.getHelperBase().getTextFromListObjects(By.cssSelector("ul#box-apps-menu ul span"));
            for (String sub_name : sub_menu_names){
                app.driver.findElement(By.xpath("//span[.='" + sub_name + "']")).click();
                assertTrue(app.getHelperBase().areElementsPresent(By.cssSelector("h1")));
            }
        }
    }





    @Test
    public void mySecondTest(){
        app.getHelperBase().openPage("http://localhost/litecart/");
        app.getAdminHelper().openCategoryHomePage("Popular Products");
        checkStickers();
    }

    private void checkStickers() {
        List<WebElement> items = app.driver.findElements(By.cssSelector("div#popular-products div[data-name]"));
        for (WebElement item : items){
            app.getHelperBase().areElementsPresentInWebElem(item, By.cssSelector("div.sticker"));
        }
    }



    @Test
    public void myThirdTest(){
        app.getHelperBase().loginAdmin();
        app.getAdminHelper().openMenuItem("Countries");
        List<String> countries = app.getHelperBase().getTextFromListObjects(By.xpath("//tbody//td/a[not(@title)]"));
        assertTrue(app.getHelperBase().isAlphabetic(countries));
        checkCountrieZonesAlphabetical();
        app.getAdminHelper().openMenuItem("Geo Zones");
        checkGeoZonesAlphabetical();
    }

    private void checkGeoZonesAlphabetical() {
        List<String> geo_zones = app.getHelperBase().getTextFromListObjects(By.xpath("//tbody//a[not(@title)]"));
        for (String zone : geo_zones){
            app.driver.findElement(By.xpath("//a[. = '" + zone + "']")).click();
            List<String> zones = app.getHelperBase().getTextFromListObjects(By.xpath("//input[contains(@name, 'country_code')]/.."));
            assertTrue(app.getHelperBase().isAlphabetic(zones));

        }
    }

    private void checkCountrieZonesAlphabetical() {
        List<String> indexes = app.getHelperBase().getTextFromListObjects((By.xpath("//tbody/tr/td[4]")));
        for (String index : indexes){
             WebElement raw = app.driver.findElement(By.xpath("//td[. = '" + index + "']/.."));
            int num_zones = Integer.parseInt(raw.findElement(By.cssSelector("td.text-center")).getText());
            if (num_zones > 0){
                raw.findElement(By.xpath(".//a[not(@title)]")).click();
                List<String> zones = app.driver.findElements(By.cssSelector("input[name$='[name]']"))
                        .stream()
                        .map((e)-> e.getAttribute("value"))
                        .collect(Collectors.toList());
                assertTrue(app.getHelperBase().isAlphabetic(zones));
                app.getAdminHelper().openMenuItem("Countries");
            }

        }
    }



    @Test
    public void myFourthTest(){
        int product_num = 0;
        String tab = "campaign";
        app.getHelperBase().openPage("http://localhost/litecart/");
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



    @Test(dataProvider = "validUsersFromJSON")
    public void registration(User user){
        app.getHelperBase().openLiteCart();
        user = app.getLiteCartHelper().registrate(user);
        app.getLiteCartHelper().logOut();
        app.getLiteCartHelper().logIn(user);
        app.getLiteCartHelper().logOut();
    }


    @Test
    public void testProductCreation(){
        app.getHelperBase().loginAdmin();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Product product = new Product().withName("NewProduct_" + timestamp.getTime())
                .withRegularPrice("500");
        app.getAdminHelper().addProduct(product, timestamp);
        assertTrue(app.getHelperBase().isElementPresent(By.xpath(String.format("//a[text()='%s']", product.getName()))));
    }

    @Test
    public void addProductToCart(){
        String tab = "popular";
        int num_products = 3;
        app.getHelperBase().openLiteCart();
        app.getLiteCartHelper().goToTab(tab);
        List<WebElement> items = app.getLiteCartHelper().getItemsMainPage(tab);
        //Iterator<WebElement> iter = items.iterator();
        for (int i=0; i<num_products; i++){
            app.getLiteCartHelper().addProductToCart(items.iterator().next());
            //app.getLiteCartHelper().addProductToCart(iter.next());
        }
        app.getLiteCartHelper().openCart();
        app.getLiteCartHelper().deleteProductFromCart();
    }

}

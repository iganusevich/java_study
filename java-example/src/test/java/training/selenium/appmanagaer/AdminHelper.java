package training.selenium.appmanagaer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import training.selenium.models.Product;

import java.io.File;
import java.sql.Timestamp;
import java.util.Objects;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AdminHelper extends HelperBase {


    public AdminHelper(WebDriver driver) {
        super(driver);


    }

    public void openMenuItem(String item) {
        driver.findElement(By.xpath("//span[. = '" + item + "']")).click();
    }

    public void openCategoryHomePage(String category) {
        driver.findElement(By.xpath("//a[. = '" + category + "']")).click();
    }


    public void addProduct(Product product, Timestamp timestamp) {
        driver.findElement(By.cssSelector("a[href*=catalog]")).click();
        driver.findElement(By.cssSelector("a[href*=edit_product]")).click();
        //General
        driver.findElement(By.cssSelector("input[name*=name]")).sendKeys(product.getName());
        driver.findElement(By.cssSelector("input[name=code]")).sendKeys(Objects.toString(timestamp.getTime()));
        driver.findElement(By.cssSelector("input[data-name*=Rubber]")).click();
        new Select(driver.findElement(By.cssSelector("select[name=default_category_id]")))
                .selectByVisibleText("Rubber Ducks");
        driver.findElement(By.cssSelector("input[name*=product_groups][value='1-3']")).click();
        File image = new File("src/test/resources/duck.jpg");
        uploadImage(By.cssSelector("input[name*=new_images]"), image);
        driver.findElement(By.cssSelector("input[name=date_valid_from]")).sendKeys("11/10/2018");
        driver.findElement(By.cssSelector("input[name=date_valid_to]")).sendKeys("11/10/2019");
        new Select(driver.findElement(By.cssSelector("select[name = manufacturer_id]")))
                .selectByVisibleText("ACME Corp.");

        //Information
        driver.findElement(By.cssSelector("a[href*=information]")).click();
        driver.findElement(By.cssSelector("input[name='short_description[en]']"))
                .sendKeys("Short Description");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("Description");
        driver.findElement(By.cssSelector("textarea[name='attributes[en]']")).sendKeys("Attributes ");
        driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("Head Title");
        driver.findElement(By.cssSelector("input[name='meta_description[en]']"))
                .sendKeys("Meta Description");

        //Prices
        driver.findElement(By.cssSelector("a[href*=prices]")).click();
        driver.findElement(By.cssSelector("input[name=purchase_price]"))
                .sendKeys(product.getRegularPrice());
        new Select(driver.findElement(By.cssSelector("select[name=purchase_price_currency_code]"))).selectByValue("USD");

        //Submit
        driver.findElement(By.cssSelector("button[name=save]")).click();
    }

    private void uploadImage(By locator, File image) {
        driver.findElement(locator)
                .sendKeys(image.getAbsolutePath());
    }
}

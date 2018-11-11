package training.selenium.appmanagaer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import training.selenium.models.MyColor;
import training.selenium.models.Product;

import java.util.ArrayList;
import java.util.List;

public class LiteCartHelper {
    private WebDriver driver;

    public LiteCartHelper(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> collectPoductInfo(By locator1, By locator2, By locator3, WebElement we){
        List<String> list = new ArrayList<>();
        list.add(we.findElement(locator1).getText());
        list.add(we.findElement(locator2).getText());
        list.add(we.findElement(locator3).getText());
        return list;
    }

    public Product getProductMainPage(int product_num, String tab){
        WebElement item = getItemMainPage(product_num, tab);
        Product product = getProductAttributes(item).withName(getItemNameMainPage(item));
        return product;
    }

    private WebElement getItemMainPage(int product_num, String tab) {
        List<WebElement> camp_prod = driver.findElements(By.cssSelector("div#box-" + tab + "-products a.link"));
        return camp_prod.get(product_num);
    }

    public String getItemNameMainPage(WebElement we){
        return we.findElement(By.cssSelector("div.name")).getText();
    }

    public Product getProductPopUp(int product_num, String tab){
        getItemMainPage(product_num,tab).click();
        WebElement item = driver.findElement(By.cssSelector("div#box-product"));
        Product product = getProductAttributes(item).withName(item.getAttribute("data-name"));
        return product;
    }

    public Product getProductAttributes(WebElement we) {
        WebElement reg_price_el = we.findElement(By.cssSelector("*.regular-price"));
        WebElement disc_price_el = we.findElement(By.cssSelector("strong.campaign-price"));
        String reg_price = reg_price_el.getText();
        String disc_price = disc_price_el.getText();
        MyColor reg_price_color = getColor(reg_price_el);
        MyColor disc_price_color = getColor(disc_price_el);
        String reg_price_style = reg_price_el.getTagName();
        String disc_price_style = disc_price_el.getTagName();
        float reg_price_size = getSize(reg_price_el);
        float disc_price_size = getSize(disc_price_el);
        Product product = new Product();
        return product.withRegularPrice(reg_price).withDiscountPrice(disc_price)
                .withRegularPriceColor(reg_price_color).withDiscountPriceColor(disc_price_color)
                .withRegularPriceSize(reg_price_size).withRegularPriceStyle(reg_price_style)
                .withDiscountPriceSize(disc_price_size).withDiscountPriceStyle(disc_price_style);

    }

    public float getSize(WebElement we){
        String size = we.getCssValue("font-size")
                .replace("px", "");
        return Float.parseFloat(size);

    }

    public MyColor getColor(WebElement we){
        String color_st = we.getCssValue("color");
        String[] numbers = color_st.replace("rgba(", "").replace(")", "")
                .replace(" ", "").split(",");
        MyColor color = new MyColor();
        return color.withRed(Integer.parseInt(numbers[0])).withGreen(Integer.parseInt(numbers[1]))
                .withBlue(Integer.parseInt(numbers[2])).withAlpha(Integer.parseInt(numbers[3]));
    }
}

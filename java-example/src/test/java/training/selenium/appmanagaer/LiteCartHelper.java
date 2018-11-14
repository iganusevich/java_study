package training.selenium.appmanagaer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import training.selenium.models.MyColor;
import training.selenium.models.Product;
import training.selenium.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
                .replace(" ", "").replace("rgb(", "")
                .split(",");
        ArrayList numbers_list = new ArrayList<String>(Arrays.asList(numbers));
        if(numbers_list.size() < 4){
            numbers_list.add("-1");
        }
        MyColor color = new MyColor();
        return color.withRed(Integer.parseInt(numbers_list.get(0).toString()))
                .withGreen(Integer.parseInt(numbers_list.get(1).toString()))
                .withBlue(Integer.parseInt(numbers_list.get(2).toString()))
                .withAlpha(Integer.parseInt(numbers_list.get(3).toString()));
    }


    public User registrate(User user) {
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 100000);
        driver.findElement(By.cssSelector("li.account a.dropdown-toggle")).click();
        driver.findElement(By.cssSelector("ul.dropdown-menu a[href*= create_account]")).click();
        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys(user.getFirst_name());
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys(user.getLast_name());
        driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys(user.getPostal_code());
        Select country = new Select(driver.findElement(By.cssSelector("select[name=country_code]")));
        country.selectByValue(user.getCountry());
        Select state = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        state.selectByValue(user.getState());
        String account = user.getEmail().replace("random", Integer.toString(randomNum));
        driver.findElement(By.cssSelector("input[name=email]:not([placeholder])"))
                .sendKeys(account);
        driver.findElement(By.cssSelector("input[name=password]:not([placeholder])")).sendKeys(user.getPassword());
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(user.getPassword());
        driver.findElement(By.cssSelector("button[name=create_account]")).click();
        return user.withEmail(account);
    }

    public void logOut() {
        driver.findElement(By.cssSelector("li.account a.dropdown-toggle")).click();
        driver.findElement(By.cssSelector("ul.dropdown-menu a[href*= logout]")).click();
    }

    public void logIn(User user) {
        driver.findElement(By.cssSelector("li.account a.dropdown-toggle")).click();
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(user.getEmail());
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(user.getPassword());
        driver.findElement(By.cssSelector("button[name=login]")).click();
        }
}

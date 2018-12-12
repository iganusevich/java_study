package appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperBase {
    public WebDriver wd;
    public WebDriverWait wait;

    public HelperBase(WebDriver wd, WebDriverWait wait) {

        this.wd = wd;
        this.wait = wait;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }



    protected void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }
    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected void select(By locator, String option) {
            Select dropdown = new Select(wd.findElement(locator));
            if (option != null){
                dropdown.selectByVisibleText(option);
            } else {
                dropdown.selectByIndex(2);
            }

    }

    protected boolean isElementPresent(By locator) {
        try{
             click(locator);
             return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String extractRegEx(String expression, String text){
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
        {
            return matcher.group(1);
        }
        return "No matches";
    }

    public void hoverOverElement(WebElement item){
        Actions builder = new Actions(wd);
        builder.moveToElement(item).perform();
    }

    public List<String> getNewWindows(List<String> oldWindows) {
        List<String> newWindows = new ArrayList<>(wd.getWindowHandles());
        newWindows.removeAll(oldWindows);
        return newWindows;
    }

    public void switchToNewWindow(String xpath, String specification) {
        List<String> oldWindows = new ArrayList<>(wd.getWindowHandles());
        wd.findElement(By.xpath(String.format(xpath,specification ))).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(oldWindows.size() + 1));
        List<String> new_windows = getNewWindows(oldWindows);
        wd.switchTo().window(new_windows.get(0));
    }

}

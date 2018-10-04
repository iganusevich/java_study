package zibby.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
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

    protected void waitUntilElementPresent(By locator){
        WebDriverWait dynamicElement = new WebDriverWait(wd, 30);
        dynamicElement.until(ExpectedConditions.presenceOfElementLocated(locator));

    }
}

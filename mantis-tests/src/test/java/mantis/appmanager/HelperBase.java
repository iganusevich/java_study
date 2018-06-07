package mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HelperBase {
    protected final ApplicationManager app;
    protected WebDriver wd;


    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();


    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }



    protected void type(By locator, String text) {
        click(locator);
        if(text != null){
            String existingText = wd.findElement(locator).getAttribute("value");
            if(! text.equals(existingText)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
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
}

package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {
   

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groups() {
        click(By.linkText("groups"));
    }

    public void addContact() {wd.findElement(By.linkText("add new")).click();
    }
    public void home() {wd.findElement(By.linkText("home")).click();
    }

}

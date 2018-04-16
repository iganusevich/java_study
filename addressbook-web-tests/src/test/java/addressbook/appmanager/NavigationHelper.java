package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {
   

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
    }

    public void goToAddContactPage() {wd.findElement(By.linkText("add new")).click();
    }

    public void returnToHome() {wd.findElement(By.linkText("home")).click();
    }
}

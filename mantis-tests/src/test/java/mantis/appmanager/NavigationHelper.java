package mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }


    public void manageUsers() {
        wd.findElement(By.cssSelector("a[href*='manage_user_page.php']")).click();
    }

    public void manage() {
        wd.findElement(By.cssSelector("a[href*='manage_overview_page.php']")).click();
    }
}

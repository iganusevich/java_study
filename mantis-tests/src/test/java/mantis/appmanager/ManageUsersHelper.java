package mantis.appmanager;

import org.openqa.selenium.By;

public class ManageUsersHelper extends HelperBase {
    public ManageUsersHelper(ApplicationManager app) {
        super(app);
    }


    public void resetPassword(String username) {
        selectUser(username);
        click(By.cssSelector("input[value='Reset Password']"));
        
    }

    public void selectUser (String username) {
        wd.findElement(By.linkText(username)).click();
    }
}

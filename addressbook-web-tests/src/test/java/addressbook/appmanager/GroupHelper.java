package addressbook.appmanager;

import addressbook.models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupHelper {
    private FirefoxDriver wd;

    public GroupHelper(FirefoxDriver wd) {
         this.wd = wd;
    }

    public void returnToGroupPage() {
        wd.findElement(By.cssSelector("html")).click();
        wd.findElement(By.cssSelector("html")).click();
        wd.findElement(By.linkText("group page")).click();
    }

    public void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillInForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void createNewGroup() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[4]")).click();
    }

    public void deleteSelectedGroup() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[5]")).click();
    }

    public void selectGroup() {
        if (!wd.findElement(By.name("selected[]")).isSelected()) {
            wd.findElement(By.name("selected[]")).click();
        }
    }
}

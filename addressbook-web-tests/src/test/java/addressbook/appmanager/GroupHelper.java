package addressbook.appmanager;

import addressbook.models.GroupData;
import addressbook.models.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillInForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void goToCreateNewGroup() {
        click(By.xpath("//div[@id='content']/form/input[4]"));
    }

    public void deleteSelected() {
        click(By.xpath("//div[@id='content']/form/input[5]"));
    }


    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id +"']")).click();

    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        goToCreateNewGroup();
        fillInForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();

    }

    public void modify(GroupData group) {
        selectById(group.getId());
        initGroupModification();
        fillInForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectById(group.getId());
        deleteSelected();
        groupCache = null;
        returnToGroupPage();
    }


    public boolean isThereAnyGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Groups groupCache = null;

    public Groups all() {
        if(groupCache != null){
            return new Groups(groupCache);
        }
        Groups groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return  groupCache;
    }
}

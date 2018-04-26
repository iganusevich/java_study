package addressbook.appmanager;

import addressbook.models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        returnToGroupPage();

    }

    public void modify(GroupData group) {
        selectById(group.getId());
        initGroupModification();
        fillInForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectById(group.getId());
        deleteSelected();
        returnToGroupPage();
    }


    public boolean isThereAnyGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public Set<GroupData> all() {
        Set<GroupData> groups = new HashSet<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return  groups;
    }
}

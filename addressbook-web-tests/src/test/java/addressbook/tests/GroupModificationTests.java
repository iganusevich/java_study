package addressbook.tests;

import addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAnyGroup()){
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().createNewGroup(new GroupData("test2", "logo2", "footer2"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillInForm(new GroupData("test2MOD", "logo2MOD", "footer2MOD"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
    }
}

package addressbook.tests;

import addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
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
        GroupData group = new GroupData("Test 1", "logo2MOD", "footer2MOD", before.get(before.size() - 1).getId());
        app.getGroupHelper().fillInForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}

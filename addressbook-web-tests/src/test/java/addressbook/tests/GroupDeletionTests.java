package addressbook.tests;


import addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;


public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensuarePreConditions(){
        app.goTo().groups();
        if (app.groups().all().size() == 0){
            app.groups().create(new GroupData().withName("test1").withHeader("logo1").withFooter("footer1"));
        }
    }


    @Test
    public void testGroupDeletion() {
        Set<GroupData> before = app.groups().all();
        GroupData deletedGroup = before.iterator().next();
        app.goTo().groups();
        app.groups().delete(deletedGroup);

        Set<GroupData> after = app.groups().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(deletedGroup);
        Assert.assertEquals(after, before);

    }




}

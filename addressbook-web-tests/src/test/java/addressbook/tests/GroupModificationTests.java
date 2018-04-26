package addressbook.tests;

import addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreConditions(){
        app.goTo().groups();
        if (app.groups().all().size() == 0){
            app.groups().create(new GroupData().withName("test1").withHeader("logo1").withFooter("footer1"));
        }
    }

    @Test
    public void testGroupModification(){
        Set<GroupData> before = app.groups().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withName("testMOD").withHeader("logo2MOD").withFooter("footer2MOD").withId(modifiedGroup.getId());
        app.goTo().groups();
        app.groups().modify(group);
        Set<GroupData> after = app.groups().all();


        Assert.assertEquals(after.size(), before.size());
        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(after, before);
    }

    
}

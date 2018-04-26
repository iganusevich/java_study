package addressbook.tests;

import addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreConditions(){
        app.goTo().groups();
        if (app.groups().list().size() == 0){
            app.groups().create(new GroupData().withName("test1").withHeader("logo1").withFooter("footer1"));
        }
    }

    @Test
    public void testGroupModification(){
        List<GroupData> before = app.groups().list();
        int index = before.size() - 1;
        GroupData group = new GroupData().withName("testMOD").withHeader("logo2MOD").withFooter("footer2MOD").withId(before.get(index).getId());
        app.goTo().groups();
        app.groups().modify(index, group);
        List<GroupData> after = app.groups().list();


        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }

    
}

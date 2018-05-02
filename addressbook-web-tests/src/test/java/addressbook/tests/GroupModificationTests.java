package addressbook.tests;

import addressbook.models.GroupData;
import addressbook.models.Groups;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Groups before = app.groups().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withName("testMOD").withHeader("logo2MOD").withFooter("footer2MOD").withId(modifiedGroup.getId());
        app.goTo().groups();
        app.groups().modify(group);
        Groups after = app.groups().all();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withModified(modifiedGroup, group)));


    }

    
}

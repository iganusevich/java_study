package addressbook.tests;


import addressbook.models.GroupData;
import addressbook.models.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
        Groups before = app.groups().all();
        GroupData deletedGroup = before.iterator().next();
        app.goTo().groups();
        app.groups().delete(deletedGroup);
        assertThat(app.groups().getGroupCount(), equalTo(before.size() - 1));
        Groups after = app.groups().all();
        assertThat(after, equalTo(before.without(deletedGroup)));
        

    }




}

package addressbook.tests;


import addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensuarePreConditions(){
        app.goTo().groups();
        if (app.groups().list().size() == 0){
            app.groups().create(new GroupData("test2", "logo2", "footer2"));
        }
    }


    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.groups().list();
        app.goTo().groups();
        int index =  before.size() - 1;
        app.groups().delete(index);

        List<GroupData> after = app.groups().list();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(index);
        Assert.assertEquals(after, before);

    }




}

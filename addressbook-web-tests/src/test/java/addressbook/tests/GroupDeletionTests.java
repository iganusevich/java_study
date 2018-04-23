package addressbook.tests;


import addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class GroupDeletionTests extends TestBase{


    @Test
    public void  testGroupDeletion (){
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAnyGroup()){
            app.getGroupHelper().createNewGroup(new GroupData("test2", "logo2", "footer2"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);
    }


}

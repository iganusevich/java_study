package addressbook.tests;


import addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GroupDeletionTests extends TestBase{


    @Test
    public void  testGroupDeletion (){
        app.getNavigationHelper().goToGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isThereAnyGroup()){
            app.getGroupHelper().createNewGroup(new GroupData("test2", "logo2", "footer2"));
        }
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before - 1);
    }


}

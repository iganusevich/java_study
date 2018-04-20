package addressbook.tests;


import addressbook.models.GroupData;
import org.testng.annotations.Test;


public class GroupDeletionTests extends TestBase{


    @Test
    public void  testGroupDeletion (){
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAnyGroup()){
            app.getGroupHelper().createNewGroup(new GroupData("test2", "logo2", "footer2"));
        }
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
    }


}

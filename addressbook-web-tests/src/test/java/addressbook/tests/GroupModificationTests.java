package addressbook.tests;

import addressbook.models.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAnyGroup()){
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().createNewGroup(new GroupData("test2", "logo2", "footer2"));
        }
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillInForm(new GroupData("test2MOD", "logo2MOD", "footer2MOD"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}

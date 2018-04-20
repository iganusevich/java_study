package addressbook.tests;

import addressbook.models.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationTests() {

        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().goToCreateNewGroup();
        app.getGroupHelper().fillInForm(new GroupData("test1", "logo", "footer"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
    }

}
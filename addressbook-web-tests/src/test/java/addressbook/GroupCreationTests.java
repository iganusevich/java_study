package addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationTests() {

        goToGroupPage();
        createNewGroup();
        fillInForm(new GroupData("test1", "logo", "footer"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
package addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToAddContactPage();
        app.getContactHelper().selectDeleteContact();

    }
}

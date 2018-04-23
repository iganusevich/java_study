package addressbook.tests;

import addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().returnToHome();
        int before = app.getContactHelper().getContactCount();
        if (!app.getContactHelper().isThereAnyContact()){
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("IreneBD", "Mid", "Test", "Igan", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage","3","February","1991", "5", "March", "2001", "Address 123", "Home 123", "Notes 123"));
        }
        app.getNavigationHelper().returnToHome();
        app.getContactHelper().selectDeleteContact(before - 1);
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().confirmContactDeletion();
        app.getNavigationHelper().returnToHome();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
        String searchCount = app.getContactHelper().getSearchCount();
        Assert.assertEquals(Integer.toString(after), searchCount);

    }                                                               
}

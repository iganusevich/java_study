package addressbook.tests;

import addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase{
    @BeforeMethod
    public void ensurePreConditions(){
        app.goTo().home();
        if (app.contacts().list().size() == 0){
            app.goTo().addContact();
            app.contacts().create(new ContactData("IreneBD", "Mid", "Test", "Igan", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage","3","February","1991", "5", "March", "2001", "Address 123", "Home 123", "Notes 123"));

        }

    }

    @Test
    public void testContactDeletion(){
        List<ContactData> before = app.contacts().list();
        int index = before.size() - 1;
        app.contacts().delete(index);
        
        List<ContactData> after = app.contacts().list();
        before.remove(index);
        Assert.assertEquals(after, before);
        String searchCount = app.contacts().getSearchCount();
        Assert.assertEquals(Integer.toString(after.size()), searchCount);

    }


}

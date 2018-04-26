package addressbook.tests;

import addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactDeletionTests extends TestBase{
    @BeforeMethod
    public void ensurePreConditions(){
        app.goTo().home();
        if (app.contacts().all().size() == 0){
            app.goTo().addContact();
            app.contacts().create(new ContactData().withFirstName("IreneBD").withLastName("Test").withAddress("Address"));

        }

    }

    @Test
    public void testContactDeletion(){
        Set<ContactData> before = app.contacts().all();
        ContactData deletedContact = before.iterator().next();
        app.contacts().delete(deletedContact);
        Set<ContactData> after = app.contacts().all();
        before.remove(deletedContact);
        Assert.assertEquals(after, before);
        
        String searchCount = app.contacts().getSearchCount();
        Assert.assertEquals(Integer.toString(after.size()), searchCount);

    }


}

package addressbook.tests;

import addressbook.models.ContactData;
import addressbook.models.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
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
        Contacts before = app.contacts().all();
        ContactData deletedContact = before.iterator().next();
        app.contacts().delete(deletedContact);
        Contacts after = app.contacts().all();
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));
        String searchCount = app.contacts().getSearchCount();
        Assert.assertEquals(Integer.toString(after.size()), searchCount);

    }


}

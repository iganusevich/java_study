package addressbook.tests;

import addressbook.models.ContactData;
import addressbook.models.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contacts().delete(deletedContact);
        Assert.assertEquals(app.contacts().getContactCount(), before.size()-1);
        String searchCount = app.contacts().getSearchCount();
        Assert.assertEquals(Integer.toString(app.contacts().getContactCount()), searchCount);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));


    }


}

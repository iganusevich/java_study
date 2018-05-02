package addressbook.tests;

import addressbook.models.ContactData;
import addressbook.models.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        Contacts before = app.contacts().all();
        app.goTo().addContact();
        ContactData contact = new ContactData().withFirstName("IreneBD").withLastName("Test").withAddress("Address");
        app.contacts().create(contact);
        app.goTo().home();
        Assert.assertEquals(app.contacts().getContactCount(), before.size() + 1);
        Contacts after = app.contacts().all();
        String searchCount = app.contacts().getSearchCount();
        Assert.assertEquals(Integer.toString(after.size()), searchCount);
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() {
        Contacts before = app.contacts().all();
        app.goTo().addContact();
        ContactData contact = new ContactData().withFirstName("Irene'").withLastName("Test'").withAddress("Address'");
        app.contacts().create(contact);
        app.goTo().home();
        Assert.assertEquals(app.contacts().getContactCount(), before.size());
        Contacts after = app.contacts().all();
        String searchCount = app.contacts().getSearchCount();
        Assert.assertEquals(Integer.toString(after.size()), searchCount);
        assertThat(after, equalTo(before));
    }

   

}

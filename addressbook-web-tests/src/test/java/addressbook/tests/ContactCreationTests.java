package addressbook.tests;

import addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        Set<ContactData> before = app.contacts().all();
        app.goTo().addContact();
        ContactData contact = new ContactData().withFirstName("IreneBD").withLastName("Test").withAddress("Address");
        app.contacts().create(contact);
        app.goTo().home();
        Set<ContactData> after = app.contacts().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        String searchCount = app.contacts().getSearchCount();
        Assert.assertEquals(Integer.toString(after.size()), searchCount);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(after, before);
    }

}

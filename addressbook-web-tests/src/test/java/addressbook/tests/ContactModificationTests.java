package addressbook.tests;

import addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreConditions(){
        app.goTo().home();
        if (app.contacts().all().size() == 0){
            app.goTo().addContact();
            app.contacts().create(new ContactData().withFirstName("IreneBD").withLastName("Test").withAddress("Address"));
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contacts().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withFirstName("IreneMOD").withLastName("TestMOD").withAddress("AddressMOD").withId(modifiedContact.getId());
        app.contacts().modify(contact);

        Set<ContactData> after = app.contacts().all();
        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(after, before);

    }


}

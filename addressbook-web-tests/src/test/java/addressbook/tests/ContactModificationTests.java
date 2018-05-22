package addressbook.tests;

import addressbook.models.ContactData;
import addressbook.models.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
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
        Contacts before = app.contacts().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withFirstName("IreneMOD").withLastName("TestMOD")
                .withAddress("AddressMOD").withCompany("ConpanyMod").withHome("123234 0").withMobile("56756456334 0")
                .withEmail("test0@email.com").withId(modifiedContact.getId());
        app.contacts().modify(contact);
        Assert.assertEquals(app.contacts().getContactCount(), before.size());
        Contacts after = app.contacts().all();
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withModified(modifiedContact, contact)));
    }


}

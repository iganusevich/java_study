package addressbook.tests;

import addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreConditions(){
        app.goTo().home();
        if (app.contacts().list().size() == 0){
            app.goTo().addContact();
            app.contacts().create(new ContactData().withFirstName("IreneBD").withLastName("Test").withAddress("Address"));

        }

    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contacts().list();
        int index =  before.size() - 1;
        ContactData contact = new ContactData().withFirstName("IreneBD").withLastName("Test").withAddress("Address").withId(before.get(index).getId());
        app.contacts().modify(index, contact);

        List<ContactData> after = app.contacts().list();
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

    }


}

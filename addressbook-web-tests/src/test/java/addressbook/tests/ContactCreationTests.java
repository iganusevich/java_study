package addressbook.tests;

import addressbook.models.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        List<ContactData> before = app.contacts().list();
        app.goTo().addContact();
        ContactData contact = new ContactData("IreneBD", "Mid", "Test", "Igan", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage","3","February","1991", "5", "March", "2001", "Address 123", "Home 123", "Notes 123");
        app.contacts().create(contact);
        app.goTo().home();
        
        List<ContactData> after = app.contacts().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        String searchCount = app.contacts().getSearchCount();
        Assert.assertEquals(Integer.toString(after.size()), searchCount);


        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        contact.setId(after.stream().max(byId).get().getId());
        before.add(contact);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }

}

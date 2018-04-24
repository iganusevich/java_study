package addressbook.tests;

import addressbook.models.ContactData;
import addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().returnToHome();
       if (! app.getContactHelper().isThereAnyContact()){
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("IreneBD", "Mid", "Test", "Igan", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage","3","February","1991", "5", "March", "2001", "Address 123", "Home 123", "Notes 123"));

        }
        app.getNavigationHelper().returnToHome();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().returnToHome();
        app.getContactHelper().selectEditContact(before.size() - 1);
        ContactData contact = new ContactData("IreneMOD", "MidMOD", "TestMOD", "IganMOD", "TitleMOD", "CompanyMOD", "AddressMOD", "HomeMOD", "MobileMOD", "WorkMOD", "FaxMOD", "E-mailMOD", "E-mail2MOD", "E-mail3MOD", "HomepageMOD","10","May","1800", "10", "May", "1900", "Address 123MOD", "Home 123MOD", "Notes 123MOD", before.get(before.size() - 1).getId());
        app.getContactHelper().fillInContactForm(contact);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

    }
}

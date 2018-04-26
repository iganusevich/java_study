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
            app.contacts().create(new ContactData("IreneBD", "Mid", "Test", "Igan", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage","3","February","1991", "5", "March", "2001", "Address 123", "Home 123", "Notes 123"));

        }

    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contacts().list();
        int index =  before.size() - 1;
        ContactData contact = new ContactData("IreneMOD", "MidMOD", "TestMOD", "IganMOD", "TitleMOD", "CompanyMOD", "AddressMOD", "HomeMOD", "MobileMOD", "WorkMOD", "FaxMOD", "E-mailMOD", "E-mail2MOD", "E-mail3MOD", "HomepageMOD","10","May","1800", "10", "May", "1900", "Address 123MOD", "Home 123MOD", "Notes 123MOD", before.get(index).getId());
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

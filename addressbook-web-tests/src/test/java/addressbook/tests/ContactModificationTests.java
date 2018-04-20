package addressbook.tests;

import addressbook.appmanager.ContactHelper;
import addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().returnToHome();
       if (! app.getContactHelper().isThereAnyContact()){
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("IreneBD", "Mid", "Test", "Igan", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage","3","February","1991", "5", "March", "2001", "Address 123", "Home 123", "Notes 123"));

        }
        app.getNavigationHelper().returnToHome();
        app.getContactHelper().selectEditContact();
        app.getContactHelper().fillInContactForm(new ContactData("IreneMOD", "MidMOD", "TestMOD", "IganMOD", "TitleMOD", "CompanyMOD", "AddressMOD", "HomeMOD", "MobileMOD", "WorkMOD", "FaxMOD", "E-mailMOD", "E-mail2MOD", "E-mail3MOD", "HomepageMOD","10","May","1800", "10", "May", "1900", "Address 123MOD", "Home 123MOD", "Notes 123MOD"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHome();

    }
}

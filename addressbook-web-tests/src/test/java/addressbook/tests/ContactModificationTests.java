package addressbook.tests;

import addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().returnToHome();
        app.getContactHelper().selectEditContact();
        app.getContactHelper().fillInContactForm(new ContactData("IreneMOD", "Mid", "Test", "Igan", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Address 123", "Home 123", "Notes 123"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHome();

    }
}

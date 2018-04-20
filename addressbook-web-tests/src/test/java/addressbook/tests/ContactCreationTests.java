package addressbook.tests;

import addressbook.models.ContactData;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToAddContactPage();
        app.getContactHelper().createContact(new ContactData("IreneBD", "Mid", "Test", "Igan", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage","3","February","1991", "5", "March", "2001", "Address 123", "Home 123", "Notes 123"));
        app.getNavigationHelper().returnToHome();
    }

}

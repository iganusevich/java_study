package addressbook.tests;

import addressbook.models.ContactData;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToAddContactPage();
        app.getContactHelper().fillInContactForm(new ContactData("Irene", "Mid", "Test", "Igan", "Title", "Company", "Address", "Home", "Mobile", "Work", "Fax", "E-mail", "E-mail2", "E-mail3", "Homepage", "Address 123", "Home 123", "Notes 123"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToHome();
    }

}

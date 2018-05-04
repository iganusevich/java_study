package addressbook.tests;

import addressbook.models.ContactData;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {

    @Test //(enabled = false)
    public void testContactPhones() {
        app.goTo().home();
        ContactData contact = app.contacts().all().iterator().next();
        ContactData infoFromEditForm = app.contacts().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(infoFromEditForm)));
    }

    @Test
    public void testContactAddress(){
        app.goTo().home();
        ContactData contact = app.contacts().all().iterator().next();
        ContactData infoFromEditForm = app.contacts().infoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(infoFromEditForm)));

        
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHome(),contact.getMobile(),contact.getWork()).stream()
                .filter((s) -> ! s.equals("")).map(ContactPhoneTests::cleanedPhones)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3()).stream()
                .filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }

    public static String cleanedPhones(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}

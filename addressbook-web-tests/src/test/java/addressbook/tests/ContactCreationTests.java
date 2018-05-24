package addressbook.tests;

import addressbook.models.ContactData;
import addressbook.models.Contacts;
import addressbook.models.GroupData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @DataProvider
    public Iterator<Object[]> validContactsFromJSON() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null){
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }


    @Test (dataProvider = "validContactsFromJSON")
    public void testContactCreation(ContactData contact) {
        app.goTo().home();
        Contacts before = app.db().contacts();
        app.goTo().addContact();
        //ContactData contact = new ContactData().withFirstName("IreneBD").withLastName("Test").withAddress("Address");
        app.contacts().create(contact);
        app.goTo().home();
        Assert.assertEquals(app.contacts().getContactCount(), before.size() + 1);
        Contacts after = app.db().contacts();
        String searchCount = app.contacts().getSearchCount();
        Assert.assertEquals(Integer.toString(after.size()), searchCount);
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
    public void testBadContactCreation() {
        app.goTo().home();
        Contacts before = app.contacts().all();
        app.goTo().addContact();
        ContactData contact = new ContactData().withFirstName("Irene'").withLastName("Test'").withAddress("Address'");
        app.contacts().create(contact);
        app.goTo().home();
        Assert.assertEquals(app.contacts().getContactCount(), before.size());
        Contacts after = app.contacts().all();
        String searchCount = app.contacts().getSearchCount();
        Assert.assertEquals(Integer.toString(after.size()), searchCount);
        assertThat(after, equalTo(before));
    }

   

}

package addressbook.tests;

import addressbook.models.ContactData;
import addressbook.models.Contacts;
import addressbook.models.GroupData;
import addressbook.models.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hibernate.boot.jaxb.SourceType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactAddToGroupTests extends TestBase{
    @BeforeMethod
    public void ensurePreConditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groups();
            app.groups().create(new GroupData().withName("test1").withHeader("logo1").withFooter("footer1"));
        }

        boolean isThereSutableContact = false;
        for (ContactData c : app.db().contacts()){
            if (!c.getGroups().equals(app.db().groups())){
                isThereSutableContact = true;
                break;
            }
        }
        if (app.db().contacts().size() == 0 || !isThereSutableContact){
            app.goTo().home();
            app.goTo().addContact();
            app.contacts().create(new ContactData().withFirstName("IreneBD").withLastName("Test").withAddress("Address"));
        }


        
    }

    @Test
    public void contactAddToGroupTests(){
        app.goTo().home();
        Contacts contacts =  app.db().contacts();
        Groups groups =  app.db().groups();
        ContactData contact = app.contacts().selectContactNotInAllGroups(contacts, groups);
        GroupData group = app.groups().selectNewGroupForContact(contact, groups);
        app.contacts().addContactToGroup(contact, group);
        Assert.assertEquals(app.db().contactById(contact.getId()).getGroups()
                .stream().anyMatch(g-> g.getId() == group.getId()), true);

    }




}

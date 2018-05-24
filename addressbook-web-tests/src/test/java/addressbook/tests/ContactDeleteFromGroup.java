package addressbook.tests;

import addressbook.models.ContactData;
import addressbook.models.Contacts;
import addressbook.models.GroupData;
import addressbook.models.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactDeleteFromGroup extends TestBase{
    @BeforeMethod
    public void ensurePreConditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groups();
            app.groups().create(new GroupData().withName("test1").withHeader("logo1").withFooter("footer1"));
        }


        if (app.db().contacts().size() == 0) {
            app.goTo().home();
            app.goTo().addContact();
            app.contacts().create(new ContactData().withFirstName("IreneBD").withLastName("Test").withAddress("Address"));
        }

        boolean isThereSutableContact = false;
        for (ContactData c : app.db().contacts()) {
            if (c.getGroups().size() > 0) {
                isThereSutableContact = true;
                break;
            }
        }
        if (!isThereSutableContact) {
            app.goTo().home();
            ContactData contact = app.db().contacts().stream().iterator().next();
            GroupData group = app.db().groups().stream().iterator().next();
            app.contacts().addContactToGroup(contact, group);
        }
    }

    
        @Test
        public void contactDeleteFromGroupTests() {
            app.goTo().home();
            GroupData group = null;
            Groups groups = app.db().groups();
            for(GroupData g : groups){
                if(g.getContacts() != null){
                    group = g;
                    break;
                }
            }
            ContactData contact = group.getContacts().stream().iterator().next();
            app.groups().goToGroupContacts(group);
            GroupData finalGroup = group;
            Assert.assertEquals(app.db().contactById(contact.getId()).getGroups()
                    .stream().anyMatch(g-> g.getId() == finalGroup.getId()), true);
            app.contacts().deleteContactFromGroup(contact);
            Assert.assertEquals(app.db().contactById(contact.getId()).getGroups()
                    .stream().anyMatch(g-> g.getId() == finalGroup.getId()), false);
        }



    }


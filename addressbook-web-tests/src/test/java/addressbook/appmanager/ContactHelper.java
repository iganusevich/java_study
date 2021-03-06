package addressbook.appmanager;

import addressbook.models.ContactData;
import addressbook.models.Contacts;
import addressbook.models.GroupData;
import addressbook.models.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillInForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstName());
        //type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        //type(By.name("nickname"), contactData.getNickName());
        //type(By.name("title"), contactData.getTitle());
        if (contactData.getCompany() != null) {type(By.name("company"), contactData.getCompany());}

        type(By.name("address"),contactData.getAddress());
        if (contactData.getHome() != null) {type(By.name("home"), contactData.getHome());}
        if (contactData.getMobile() != null) {type(By.name("mobile"),contactData.getMobile());}

        //type(By.name("work"), contactData.getWork());
        //type(By.name("fax"),contactData.getFax() );
        if (contactData.getEmail() != null) {type(By.name("email"), contactData.getEmail());}
        //type(By.name("email2"), contactData.getEmail2());
        //type(By.name("email3"), contactData.getEmail3());
        //type(By.name("homepage"), contactData.getHomepage());
        //select(By.name("bday"), contactData.getBday() );
        //select(By.name("bmonth"), contactData.getBmonth());
        //type(By.name("byear"), contactData.getByear());
        //select(By.name("aday"), contactData.getAday() );
        //select(By.name("amonth"), contactData.getAmonth());
        //type(By.name("ayear"), contactData.getAyear());
        //type(By.name("address2"),contactData.getAddress2());
        //type(By.name("phone2"), contactData.getPhone2());
        //type(By.name("notes"), contactData.getNotes());

        if (creation){
            if(contactData.getGroups().size() > 0){
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        }

    }

    private void selectGroupForContact(ContactData contactData, GroupData group) {
        if(contactData.getGroups().size() > 0){
            new Select(wd.findElement(By.name("to_group")))
                    .selectByVisibleText(group.getName());
        }
    }

    public void selectEditById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href*='edit.php?id=%s']", id))).click(); 
    }

    public void submitModification() {
        click(By.name("update"));
    }


    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void submitDeletion() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void confirmDeletion() {
        wd.switchTo().alert().accept();
    }



    public void create(ContactData contact) {

        fillInForm(contact, true);
        submitContactCreation();
        contactCache = null;
        contactsPage();

        

    }
    public void modify(ContactData contact) {
        selectEditById(contact.getId());
        fillInForm(contact, false);
        submitModification();
        contactCache = null;
        contactsPage();
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        submitDeletion();
        confirmDeletion();
        contactCache = null;
        contactsPage();
    }

    public void contactsPage() {wd.findElement(By.linkText("home")).click();
    }

    public boolean isThereAnyContact() {
        return isElementPresent(By.cssSelector("a[href*='edit.php?id=']"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public String getSearchCount() {
        return wd.findElement(By.id("search_count")).getText();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if(contactCache != null){
            return new Contacts(contactCache);
        }
        Contacts contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String firstName =  element.findElement(By.xpath("td[3]")).getText();
            String address =  element.findElement(By.xpath("td[4]")).getText();
            String allPhones = element.findElement(By.xpath("td[6]")).getText();
            String allEmails = element.findElement(By.xpath("td[5]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withFirstName(firstName).withLastName(lastName)
                    .withAddress(address).withAllPhones(allPhones).withId(id).withAllEmails(allEmails));
        }
        return contactCache;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        selectEditById(contact.getId());
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withFirstName(firstName).withLastName(lastName).withAddress(address)
                .withId(contact.getId()).withHome(home).withMobile(mobile).withWork(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        selectById(contact.getId());
        selectGroupForContact(contact, group);
        submitContactToGroup();


    }

    private void submitContactToGroup() {
        click(By.name("add"));
    }

    public ContactData selectContactNotInAllGroups(Contacts contacts, Groups groups) {
        ContactData contact = null;
        for (ContactData c : contacts) {
            if (!c.getGroups().equals(groups)) {
                contact = c;
                break;
            }
        }
        return contact;

    }

    public void submitRemovalFromGroup() {
        click(By.name("remove"));
    }

    public void deleteContactFromGroup(ContactData contact) {
        selectById(contact.getId());
        submitRemovalFromGroup();
    }
}

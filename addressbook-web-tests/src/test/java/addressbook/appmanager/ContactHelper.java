package addressbook.appmanager;

import addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillInForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"),contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"),contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("fax"),contactData.getFax() );
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomepage());
        select(By.name("bday"), contactData.getBday() );
        select(By.name("bmonth"), contactData.getBmonth());
        type(By.name("byear"), contactData.getByear());
        select(By.name("aday"), contactData.getAday() );
        select(By.name("amonth"), contactData.getAmonth());
        type(By.name("ayear"), contactData.getAyear());
        type(By.name("address2"),contactData.getAddress2());
        type(By.name("phone2"), contactData.getPhone2());
        type(By.name("notes"), contactData.getNotes());

    }

    public void selectEditById(int id) {
        wd.findElement(By.cssSelector("a[href*='edit.php?id=" + id + "']")).click();
    }

    public void submitModification() {
        click(By.name("update"));
    }


    public void selectDeleteById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void submitDeletion() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void confirmDeletion() {
        wd.switchTo().alert().accept();
    }



    public void create(ContactData contact) {

        fillInForm(contact);
        submitContactCreation();
        contactsPage();

        

    }
    public void modify(ContactData contact) {
        selectEditById(contact.getId());
        fillInForm(contact);
        submitModification();
        contactsPage();
    }

    public void delete(ContactData contact) {
        selectDeleteById(contact.getId());
        submitDeletion();
        confirmDeletion();
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


    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for (WebElement element : elements){
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String firstName =  element.findElement(By.xpath("td[3]")).getText();
            String address =  element.findElement(By.xpath("td[4]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withFirstName(firstName).withLastName(lastName).withAddress(address).withId(id));
        }
        return contacts;
    }
}

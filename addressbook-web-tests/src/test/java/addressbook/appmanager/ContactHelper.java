package addressbook.appmanager;

import addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillInContactForm(ContactData contactData) {
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

    public void selectEditContact(int index) {
        //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a[@href='edit.php?id=2']"));
        wd.findElements(By.cssSelector("a[href*='edit.php?id=']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void selectDeleteContact(int index) {
       wd.findElements(By.name("selected[]")).get(index).click();

            //wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[1]/input[@name='selected[]']")).click();

    }

    public void submitContactDeletion() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void confirmContactDeletion() {
        wd.switchTo().alert().accept();
    }



    public void createContact(ContactData contact) {

        fillInContactForm(contact);
        submitContactCreation();

        

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
}

package addressbook.models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;


import org.hibernate.annotations.Table;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "addressbook")
public class ContactData {
    @Expose
    @Column(name = "firstName")
    private String firstName;
    private String middleName;
    @Expose
    @Column(name = "lastName")
    private String lastName;
    private String nickName;
    private String title;
    @Expose
    @Type(type = "text")
    private String company;
    @Expose
    @Type(type = "text")
    private String address;
    @Expose
    @Type(type = "text")
    @Column(name = "home")
    private String home;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;

    @Column(name = "work")
    @Type(type = "text")
    private String work;
    @Type(type = "text")
    private String fax;
    @Expose
    @Type(type = "text")
    private String email;
    @Type(type = "text")
    private String email2;
    @Type(type = "text")
    private String email3;
    @Type(type = "text")
    private String homepage;
    //private String bday;
    //private String bmonth;
    //private String byear;

    //@Type(type = "text")
    //private String aday;
    //private String amonth;
    //private String ayear;
    @Type(type = "text")
    private String address2;
    @Type(type = "text")
    private String phone2;
    @Type(type = "text")
    private String notes;
    @Id
    @Column(name = "id")
    private  int id = 0;

    @Transient
    private String allPhones;
    @Transient
    private  String allEmails;

    public String getAllEmails() {
        return allEmails;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getNotes() {
        return notes;
    }

    //public String getBday() {
    //    return bday;
    //}

    //public String getBmonth() {
    //    return bmonth;
    //}

   // public String getByear() {
     //   return byear;
   // }

    //public String getAday() {
    //    return aday;
    //}

    //public String getAmonth() {
    //    return amonth;
    //}

    //public String getAyear() {
    //    return ayear;
   // }
    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

   // public ContactData withBday(String bday) {
   //     this.bday = bday;
   //     return this;
    //}

  //  public ContactData withBmonth(String bmonth) {
   //     this.bmonth = bmonth;
   //     return this;
  //  }

   // public ContactData withByear(String byear) {
   //     this.byear = byear;
   //     return this;
   // }

    //public ContactData withAday(String aday) {
    //    this.aday = aday;
    //    return this;
   // }

  //  public ContactData withAmonth(String amonth) {
  //      this.amonth = amonth;
   //     return this;
   // }

   // public ContactData withAyear(String ayear) {
    //    this.ayear = ayear;
    //    return this;
   // }

    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public ContactData withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public ContactData withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(address, that.address) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, address, id);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", id='" + id + '\'' +
                '}';
    }


}

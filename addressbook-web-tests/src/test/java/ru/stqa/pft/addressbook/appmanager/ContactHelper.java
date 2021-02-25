package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreating() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
        if(creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void submitContactCreating() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectedContactProfile() {
        click(By.xpath("(//input[@name='selected[]'])"));
    }
    public void selectedContact() {
        click(By.name("selected[]"));
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void deleteContact() {
        click(By.xpath("(//input[@name='update'])[3]"));
    }

    public void returnHomePages() {
        click(By.linkText("home page"));
    }

    public void deleteContactFromHomePages() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("(//input[@name='selected[]'])"));
    }

    public void createContact(ContactData contact) {
        initContactCreating();
        fillContactForm(contact, true);
        submitContactCreating();
        returnHomePages();

    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

}

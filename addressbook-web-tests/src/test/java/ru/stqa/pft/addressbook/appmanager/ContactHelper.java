package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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
        click(By.name("submit"));
    }

    public void selectedContactProfile() {
        click(By.xpath("(//input[@name='selected[]'])"));
    }
    public void selectedContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void deleteContact() {
        click(By.xpath("(//input[@name='update'])[3]"));
    }

    public void returnHomePages() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page"));
    }

    public void deleteContactFromHomePages() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox")); // вот так можно искать это сообщение о том, что контакт удалён
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("(//input[@name='selected[]'])"));
    }

    public void create(ContactData contact) {
        initContactCreating();
        fillContactForm(contact, true);
        submitContactCreating();
        returnHomePages();

    }
    public void modifyContact(ContactData contact) {
        fillContactForm(contact, false);
        submitContactModification();
        returnHomePages();

    }
    public void delete(int index) {
        selectedContact(index);
        deleteContactFromHomePages();
        returnHomePages();
    }


    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        // создаем список для контактов
        List<ContactData> contacts = new ArrayList<ContactData>();

        // выбираем элемент разметки из которого будем извлекать lastname  and firstname
        List<WebElement> elements =  wd.findElements(By.cssSelector("tr[name='entry']"));
        
        // создаем цикл где построчно берем lastname  and firstname
        for(WebElement element : elements){
            int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) input")).getAttribute("value"));
            String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();


            //создаем экземпляр объекта ContactData
            ContactData contact =
                    new ContactData(id, firstname, lastname, null, null, null, null);
            //добавляем прочитанные данные
            contacts.add(contact);
        }

        return contacts;
    }
}

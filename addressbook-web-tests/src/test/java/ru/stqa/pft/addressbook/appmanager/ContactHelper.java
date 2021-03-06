package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

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
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), contactData.getPhoto()); // обязательно передать в качестве параметра полный путь .getAbsolutePath()
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName()); // todo лекция 6.1 пересмотреть код
            }
        } else {
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

    public void selectedContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }


    public void returnHomePages() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
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
        contactCache = null;
        returnHomePages();

    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnHomePages();

    }

    public void initContactModificationById(int id) {
        // метод последовательных приближений
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../..")); // нашли нужную строку
        List<WebElement> cells = row.findElements(By.tagName("td"));// берем полный список ячеек
        cells.get(7).findElement(By.tagName("a")).click();
        // wd.findElement(By.xpath("//a[@href='edit.php?id=" + id +"']")).click();

        // Лекция 5.9 время 8.10 -8.45
        // wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

    }

    public void delete(ContactData contact) {
        selectedContactById(contact.getId());
        deleteContactFromHomePages();
        contactCache = null;
        returnHomePages();
    }


    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        //Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            //contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
            //.withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));

            contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withAddress(address)
                    .withAllEmails(allEmails).withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withAddress(address).
                withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public void selectGroup(Contacts contactData) {
        if (contactData.iterator().next().getGroups().size() > 1) {
            Assert.assertTrue(contactData.iterator().next().getGroups().size() == 1);
            new Select(wd.findElement(By.name("group"))).selectByVisibleText(contactData.iterator().next().getGroups().iterator().next().getName());

        }
    }

    public void addContactToGroup() {
        click(By.name("add"));
        contactCache = null;
        returnHomePages();
    }

    public void selectGroupPage(Contacts contactData) {
        if (contactData.iterator().next().getGroups().size() > 0) {
            Assert.assertTrue((contactData.iterator().next().getGroups().size() == 1));
            new Select(wd.findElement(By.name("group"))).selectByVisibleText(contactData.iterator().next().getGroups().iterator().next().getName());
        }
    }

    public void removeContactFromGroup() {
        click(By.name("remove"));
        contactCache = null;
        returnHomePages();
    }

    public void selectGroup(GroupDate group) {
        wd.findElement(By.xpath(String.format("//select[@name='to_group']/option[@value='%s']", group.getId()))).click();
    }


    public void selectContactNotGroup(ContactData contact) {
        click(By.xpath(String.format("//input[@type='checkbox']", contact.getId())));
    }

    public void getGroupData(GroupDate groupData) {
        click(By.xpath(String.format("//select[@name='group']/option[text() = '%s']", groupData.getName())));
        ;
    }

    public void selectContactNotInGroup(ContactData contact) {
        wd.findElement(By.xpath(String.format("//input[@type='checkbox']", contact.getId()))).click();
    }


    public void selectContact(ContactData contact) {
        click(By.xpath(String.format("//input[@type='checkbox']", contact.getId())));
    }

    public void selectContactWithoutGroup(ContactData contact) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[none]");
        click(By.xpath(String.format("//input[@type='checkbox']", contact.getId())));
    }
    public void addGroupToContact(ContactData contact, GroupDate group) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
        selectedContactById(contact.getId());
        new Select(wd.findElement(By.name("to_group")))
                .selectByVisibleText(group.getName());
        click(By.name("add"));
    }
    public void deleteGroupFromContact(ContactData contact, GroupDate group){
        new Select(wd.findElement(By.name("group")))
                .selectByVisibleText(group.getName());
        selectedContactById(contact.getId());
        click(By.name("remove"));
    }
    public void addToGroup(ContactData contact, GroupDate group) {
        selectedContactById(contact.getId());
        wd.findElement(By.name("to_group")).click();
        new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(group.getId()));
        wd.findElement(By.name("add")).click();
    }
    public ContactData findFreeContact(Groups groups, Contacts contacts) {
        ContactData foundContact = null;
        // обходим контакты и пытаемся найти контакт который не во всех группах
        for (ContactData contact : contacts) {
            foundContact = contact;
            if (contact.getGroups().size() != groups.size()) {
                break;
            }
        }
        return foundContact;
    }
    //выбор группы, из которой хотим удалить контакт
    public void selectParentGroupById(int id) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.format("%s", id));
    }
    public void selectContactById(int id) {
        wd.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();
    }
    public void submitContactRemovingFromGroup() {
        click(By.name("remove"));
    }
    //переход на страницу конкретной группы
    public void goToSelectedGroupPage(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='./?group=%s']", id))).click();
    }

    public void removeFromGroup(ContactData contact, GroupDate group) {
        selectParentGroupById(group.getId());
        selectContactById(contact.getId());
        submitContactRemovingFromGroup();
        contactCache = null;
        goToSelectedGroupPage(group.getId());
    }
}



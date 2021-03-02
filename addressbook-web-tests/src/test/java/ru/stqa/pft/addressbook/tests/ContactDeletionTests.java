package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().gotoHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Vlada").withLastName("Petrova").withCompany("LTD").withMobile("8(888)000-00-00").withEmail("petrova@gmail.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactDeletionFromHomePage() {

        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);

    }

// todo альтернативный тест "удаление контакта из профиля" вернуться позже
    /*@Test
    public void testContactDeletionFromProfile() {

        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();

        app.contact().initContactModificationById(modifiedContact.id);
        app.contact().deleteContact();
        app.goTo().gotoHomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(modifiedContact.id);
        Assert.assertEquals(before, after);


    }
    */

}

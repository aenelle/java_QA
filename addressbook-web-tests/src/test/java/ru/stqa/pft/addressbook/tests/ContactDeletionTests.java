package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().gotoHomePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Vlada").withLastName("Petrova").withCompany("LTD").withMobilePhone("8(888)000-00-00").withEmail("petrova@gmail.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactDeletionFromHomePage() {

        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertEquals(app.contact().count(), before.size() - 1);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(deletedContact)));

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

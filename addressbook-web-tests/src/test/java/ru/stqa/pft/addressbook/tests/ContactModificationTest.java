package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectedContactProfile();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Inna", "Ivanova", "Software", "8(978)111-11-77", "oova@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnHomePages();

    }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData
                    ("Vlada", "Petrova", "LTD", "8(888)000-00-00", "petrova@gmail.com", "test1"));
        }
        int before = app.getContactHelper().getContactCount();
        //app.getContactHelper().selectedContactProfile();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData
                ("Inna", "Ivanova", "Software", "8(978)111-11-77", "oova@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnHomePages();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);


    }
}

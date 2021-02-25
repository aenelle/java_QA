package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionFromProfile(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData
                    ("Vlada", "Petrova", "LTD", "8(888)000-00-00", "petrova@gmail.com", "test1"));
        }
        int before = app.getContactHelper().getContactCount();
        //app.getContactHelper().selectedContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);


    }


    @Test
    public void testContactDeletionFromHomePage(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData
                    ("Vlada", "Petrova", "LTD", "8(888)000-00-00", "petrova@gmail.com", "test1"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectedContact();
        app.getContactHelper().deleteContactFromHomePages();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);

    }
}

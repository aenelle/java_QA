package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData
                    ("Vlada", "Petrova", "LTD", "8(888)000-00-00", "petrova@gmail.com", "test1"));
        }
    }

    @Test
    public void testContactDeletionFromProfile(){

        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() -1;
        app.getContactHelper().initContactModification(index);
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Assert.assertEquals(before, after);


    }


    @Test
    public void testContactDeletionFromHomePage(){

        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() -1;
        app.getContactHelper().selectedContact(index);
        app.getContactHelper().deleteContactFromHomePages();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Assert.assertEquals(before, after);


    }
}

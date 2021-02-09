package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionFromProfile(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectedContactProfile();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();


    }
    @Test
    public void testContactDeletionFromHomePage(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectedContactProfile();
        app.getContactHelper().deleteContactFromHomePages();

    }
}

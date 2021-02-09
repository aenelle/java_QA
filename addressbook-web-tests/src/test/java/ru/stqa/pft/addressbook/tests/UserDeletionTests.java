package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase {

    @Test
    public void testUserDeletionFromProfile(){
        app.getNavigationHelper().gotoHomePage();
        app.getUserHelper().selectedUserProfile();
        app.getUserHelper().initUserModification();
        app.getUserHelper().deleteUsers();


    }
    @Test
    public void testUserDeletionFromHomePage(){
        app.getNavigationHelper().gotoHomePage();
        app.getUserHelper().selectedUserProfile();
        app.getUserHelper().deleteUsersFromHomePages();

    }
}

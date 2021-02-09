package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTest extends TestBase {

    @Test
    public void testUserModification(){
        app.getNavigationHelper().gotoHomePage();
        app.getUserHelper().selectedUserProfile();
        app.getUserHelper().initUserModification();
        app.getUserHelper().fillUserForm(new UserData("Inna", "Ivanova", "Software", "8(978)111-11-77", "oova@gmail.com"));
        app.getUserHelper().submitUserModification();
        app.getUserHelper().returnHomePages();

    }
}

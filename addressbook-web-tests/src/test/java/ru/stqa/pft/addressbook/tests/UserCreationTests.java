package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;


public class UserCreationTests extends TestBase {


  @Test
  public void testUserCreation() throws Exception {

    app.getUserHelper().initUsersCreating();
    app.getUserHelper().fillUserForm(new UserData("Ivan", "Ivanov", "Software", "8(978)999-88-77", "iva@gmail.com"));
    app.getUserHelper().submitUserCreating();
    app.getNavigationHelper().gotoHomePage();
  }

}





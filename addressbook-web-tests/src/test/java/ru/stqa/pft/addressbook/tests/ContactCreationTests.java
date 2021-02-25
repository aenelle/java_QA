package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreating();
    app.getContactHelper().createContact(new ContactData
            ("Robert", "Ivanov", "Software", "8(978)999-88-77", "iva@gmail.com", "test1"));
    //app.getContactHelper().submitContactCreating();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
   Assert.assertEquals(after.size(), before.size() + 1);
  }

}





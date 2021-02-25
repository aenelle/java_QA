package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {


    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactCreating();
    app.getContactHelper().fillContactForm(new ContactData
            ("Ivan", "Ivanov", "Software", "8(978)999-88-77", "iva@gmail.com", "test1"), true);
    app.getContactHelper().submitContactCreating();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }

}





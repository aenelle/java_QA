package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreating();
    // создаем переменную
    ContactData contact = new ContactData
            ("Zuka", "Wuka", null, "8(978)999-88-77", "iva@gmail.com", "test1");
    app.getContactHelper().createContact(contact); //передаем в нее значения
    //app.getContactHelper().submitContactCreating(); строка не нужна поскольку есть шаг в методе createContact
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
   Assert.assertEquals(after.size(), before.size() + 1);


   before.add(contact);
   int max = 0;
   for (ContactData g : after) {
     if (g.getId() > max) {
       max = g.getId();
     }
   }
   contact.setId(max);
   Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}





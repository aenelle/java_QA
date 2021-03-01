package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreating();
    // создаем переменную
    ContactData contact = new ContactData
            ("Vlada", "Orlova", null, "8(978)999-88-77", "iva@gmail.com", "test1");
    app.getContactHelper().createContact(contact); //передаем в нее значения созданной переменной
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}





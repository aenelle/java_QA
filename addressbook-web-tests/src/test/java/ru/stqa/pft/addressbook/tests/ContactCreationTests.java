package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    List<ContactData> before = app.contact().list();
    app.contact().initContactCreating();
    ContactData contact = new ContactData
            ("Vlada", "Orlova", null, "8(978)999-88-77", "iva@gmail.com", "test1");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}





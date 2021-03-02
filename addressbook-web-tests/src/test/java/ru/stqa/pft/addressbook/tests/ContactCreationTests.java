package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    Set<ContactData> before = app.contact().all();
    app.contact().initContactCreating();
    ContactData contact = (new ContactData().withFirstName("Vlada").withLastName("Petrova").withCompany("LTD").withMobile("8(888)000-00-00").withEmail("petrova@gmail.com").withGroup("test1"));
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt());
    before.add(contact);

    Assert.assertEquals(before, after);
  }

}





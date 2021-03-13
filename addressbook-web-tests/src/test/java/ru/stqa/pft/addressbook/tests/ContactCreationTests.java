package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {

    Contacts before = app.contact().all();
    app.contact().initContactCreating();
    ContactData contact = (new ContactData().withFirstName("Vlada").withLastName("Petrova").withCompany("LTD").withMobilePhone("8(888)000-00-00").withEmail("petrova@gmail.com").withGroup("test1"));
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadContactCreation() {

    Contacts before = app.contact().all();
    app.contact().initContactCreating();
    ContactData contact = (new ContactData().withFirstName("Vla'").withLastName("Petrova").withCompany("LTD").withMobilePhone("8(888)000-00-00").withEmail("petrova@gmail.com").withGroup("test1"));
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();


    assertThat(after, equalTo(before));
  }


}





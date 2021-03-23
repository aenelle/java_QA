package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactInGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().gotoHomePage();
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstName("Vlada").withLastName("Petrova").withCompany("LTD")
              .withMobilePhone("8(888)000-00-00").withEmail("petrova@gmail.com"));
      app.goTo().gotoHomePage();
    }
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupDate().withName("testZero").withHeader("testZeroHeader").withFooter("testZeroFooter"));
      app.goTo().gotoHomePage();
    }
    Contacts contacts = app.db().contacts();
    if (contacts.stream().iterator().next().getGroups().size() == 0){
      app.contact().selectedContactById(contacts.iterator().next().getId());
      app.contact().selectGroup(contacts);
      app.contact().addContactToGroup();
      app.goTo().gotoHomePage();
    }
  }

  @Test
  public void testContactInGroup(){
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    app.contact().selectedContactById(before.iterator().next().getId());
    app.contact().selectGroup(before);
    app.contact().addContactToGroup();
    Contacts after = app.db().contacts();
    assertThat(before.iterator().next(), equalTo(after.iterator().next()));

  }

}

package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.Matchers.equalTo;

public class ContactAddGroupTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditionsSorted() {
    if (app.db().contacts().size() == 0) {
      app.contact().initContactCreating();
      app.contact().create(new ContactData().withFirstName("Vlada").withLastName("Ollo"));
      app.goTo().gotoHomePage();
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupDate().withName("test 0"));
      app.goTo().gotoHomePage();
    }
  }

  @Test
  public void testContactAddGroupTest(){
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    ContactData contactForAddingToGroup = contacts.iterator().next();
    Groups groupsOfSelectedContactsBefore = contactForAddingToGroup.getGroups();

    boolean wasAdded = false;
    GroupDate group = null;

    for (GroupDate g: groups)
    {

      if (!groupsOfSelectedContactsBefore.contains(g))
      {
        app.goTo().gotoHomePage();
        app.contact().addGroupToContact(contactForAddingToGroup,g);
        wasAdded = true;
        group = g;
        break;
      }
    }

    if (!wasAdded)
    {
      app.goTo().groupPage();
      GroupDate g = new GroupDate().withName("test 0");
      app.group().create(g);
      app.goTo().gotoHomePage();
      app.contact().addGroupToContact(contactForAddingToGroup,g);
      group = g;

    }

    Contacts contactsAfterAdding = app.db().contacts();

    for(ContactData c: contactsAfterAdding) {

      if (c.equals(contactForAddingToGroup)) {
        MatcherAssert.assertThat(c.getGroups().size(), equalTo(groupsOfSelectedContactsBefore.size()+1));
        MatcherAssert.assertThat(c.getGroups(),
                equalTo(contactForAddingToGroup.getGroups().withAdded(group)));
      }
    }

  }
}

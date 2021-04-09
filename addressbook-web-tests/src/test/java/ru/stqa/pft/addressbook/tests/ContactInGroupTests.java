package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactInGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().gotoHomePage();
    if (app.db().contacts().size() == 0){
      app.contact().initContactCreating();
      app.contact().create(new ContactData().withFirstName("Vlada").withLastName("Petrova").withCompany("LTD")
              .withMobilePhone("8(888)000-00-00").withEmail("petrova@gmail.com"));
      app.goTo().gotoHomePage();
    }
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.contact().initContactCreating();
      app.group().create(new GroupDate().withName("testZero").withHeader("testZeroHeader").withFooter("testZeroFooter"));
      app.goTo().gotoHomePage();
    }
    if (app.db().contactWithoutGroups().size()==0){
      app.goTo().gotoHomePage();
      app.contact().initContactCreating();
      app.contact().create(new ContactData().withFirstName("Vlada").withLastName("Sokolova"));
    }
  }

  @Test
  public void testContactAddToGroup(){
    ContactData before = app.db().contactWithoutGroup();
    Groups groups = app.db().groups();
    GroupDate group = groups.iterator().next();
    app.goTo().gotoHomePage();
    app.contact().selectContactWithoutGroup(before);
    app.contact().selectGroup(group);
    app.contact().addContactToGroup();
    ContactData after = app.db().contactById(before.getId());
    assertTrue(after.getGroups().contains(group));
    verifyGroupListInUi();
  }

  @Test
  public void testContactRemoveFromGroup() {
    ContactData before = app.db().contactInGroup();
    GroupDate group = before.getGroups().iterator().next();
    app.goTo().gotoHomePage();
    app.contact().getGroupData(group);
    app.contact().selectContact(before);
    app.contact().removeContactFromGroup();
    ContactData after = app.db().contactById(before.getId());
    assertTrue(after.getGroups().contains(group));
    verifyGroupListInUi();
  }
}

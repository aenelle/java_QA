package ru.stqa.pft.addressbook.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class SampleTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupDate().withName("test1"));
    }
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Vlada").withLastName("Petrova").withCompany("LTD")
              .withMobilePhone("8(888)000-00-00").withEmail("petrova@gmail.com"));
      app.goTo().gotoHomePage();
    }
  }
  @Test
  public void TestSample(){
    ContactData contact; // контакт, который будем удалять
    Groups groupSetBefore; //набор групп этого контакта
    GroupDate parentGroup; //группа, из которой будем удалять
    Contacts contactSetBefore; //набор контактов этой группы

    Contacts contactSet = app.db().contacts(); //все контакты в бд
    Contacts contactsInGroup = new Contacts();

    //ищем контакты, которые уже есть в какой-нибудь группе
    for (ContactData c : contactSet) {
      if (c.getGroups().size() > 0) {
        contactsInGroup = contactsInGroup.withAdded(c);
      }
    }

    if (contactsInGroup.size() == 0) { //если нет ни одного контакта, входящего в какую-нибудь группу
      contact = app.db().contacts().iterator().next();
      parentGroup = app.db().groups().iterator().next();
      app.goTo().gotoHomePage();
      app.contact().addToGroup(contact, parentGroup); //предварительно добавляем любой контакт в любую группу
      groupSetBefore = app.db().contactById(contact.getId()).getGroups(); //по id заново получаем из бд контакт, получаем список его групп
      contactSetBefore = app.db().groupById(parentGroup.getId()).getContacts(); //по id заново получаем из бд группу, получаем список её контактов
    } else {
      contact = contactsInGroup.iterator().next();
      groupSetBefore = contact.getGroups();
      parentGroup = groupSetBefore.iterator().next();
      contactSetBefore = parentGroup.getContacts();
    }

    app.goTo().gotoHomePage();
    app.contact().removeFromGroup(contact, parentGroup);

    assertThat(app.contact().count(), equalTo(contactSetBefore.size() - 1)); //сравниваем количество контактов в конкретной группе до и после
    Groups groupSetAfter = app.db().contactById(contact.getId()).getGroups(); //по id получаем из бд контакт, который удаляли из группы, получаем его список групп
    Contacts contactSetAfter = app.db().groupById(parentGroup.getId()).getContacts(); //по id получаем из бд группу, из которой удалялся контакт, получаем её список контактов
    assertThat(groupSetAfter, equalTo(groupSetBefore.withOut(parentGroup)));
    assertThat(contactSetAfter, equalTo(contactSetBefore.withOut(contact)));

    verifyContactsInGroupUI(parentGroup);
  }
  @Test
  public void testContactDelGroup(){
    Groups groups = app.db().groups();
    GroupDate group = groups.iterator().next();

    Contacts contacts = app.db().contacts();
    ContactData contactForDeletingFromGroup = contacts.iterator().next();
    Groups groupsOfSelectedContactsBefore = contactForDeletingFromGroup.getGroups();

    if (groupsOfSelectedContactsBefore.size()==0) {
      app.goTo().gotoHomePage();
      app.contact().addGroupToContact(contactForDeletingFromGroup,group);
      app.goTo().gotoHomePage();

      Contacts contactsAfterAdding = app.db().contacts();
      for(ContactData c: contactsAfterAdding) {
        if (c.equals(contactForDeletingFromGroup)) {
          groupsOfSelectedContactsBefore = c.getGroups();
        }
      }
      app.contact().deleteGroupFromContact(contactForDeletingFromGroup,group);
    }else{
      app.contact().deleteGroupFromContact(contactForDeletingFromGroup,group);
      app.goTo().gotoHomePage();
    }

    Contacts contactsAfterDeleting = app.db().contacts();

    for(ContactData c: contactsAfterDeleting) {

      if (c.equals(contactForDeletingFromGroup)) {
        assertThat(c.getGroups().size(), Matchers.equalTo(groupsOfSelectedContactsBefore.size()-1));
        assertThat(c.getGroups(),
                Matchers.equalTo(contactForDeletingFromGroup.getGroups().withOut(group)));
      }
    }
  }
}

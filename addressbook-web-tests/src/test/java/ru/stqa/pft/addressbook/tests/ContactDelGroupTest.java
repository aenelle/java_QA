package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ContactDelGroupTest extends TestBase {

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
  public void testContactDelGroup1(){

    Groups groups = app.db().groups();
    int groupID = 0;
    int contactId = 0;
    for (GroupDate gr : groups) {
      if (gr.getContacts().size() == 0) {
        groupID = gr.getId();
        ContactData contact = app.db().contacts().iterator().next();
        contactId = contact.getId();
        app.goTo().gotoHomePage();
        app.contact().addToGroup(contact, gr);
        app.goTo().gotoHomePage();
      } else {
        //ищем группу в которой есть контакты
        Optional<GroupDate> groupWithContacts = groups.stream().filter((g) -> g.getContacts().size() > 0).findFirst();
        assertTrue(groupWithContacts.isPresent());
        groupID = groupWithContacts.get().getId();
        contactId = groupWithContacts.get().getContacts()
                .stream()
                .findFirst().get().getId();
      }
    }

    //удаляем контакт из группы
    app.group().removeContact(groupID, contactId);

    //проверим: группа и контакт не удалены
    int finalGroupID = groupID;
    Optional<GroupDate> group = app.db().groups()
            .stream()
            .filter((g) -> g.getId() == finalGroupID)
            .findFirst();

    int finalContactId = contactId;
    Optional<ContactData> contact = app.db().contacts()
            .stream()
            .filter((c) -> c.getId() == finalContactId)
            .findFirst();

    assertThat(finalGroupID, equalTo(group.get().getId()));
    assertThat(finalContactId, equalTo(contact.get().getId()));
    //проверим: контакт не входит в группу
    assertFalse(contact.get().getGroups().contains(group));
  }

}



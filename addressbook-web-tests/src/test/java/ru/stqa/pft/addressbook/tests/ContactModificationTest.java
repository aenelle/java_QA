package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        if (app.db().contacts().size() == 0){
          app.goTo().gotoHomePage();
            app.contact().create(new ContactData().withFirstName("Vlada").withLastName("Petrova")
                    .withCompany("LTD").withMobilePhone("8(888)000-00-00").withEmail("petrova@gmail.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {

      Contacts before = app.db().contacts();
      ContactData modifiedContact = before.iterator().next();
      ContactData contact =
              new ContactData().withId(modifiedContact.getId()).withFirstName("Vlada").withLastName("Levchenko").withCompany("Software").withMobilePhone("8(978)111-11-77").withEmail("oova@gmail.com");  // создаем локальную переменную, чтобы ее везде использовать
      app.contact().modify(contact);
      assertEquals(app.contact().count(), before.size());
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

    }

}

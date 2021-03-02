package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().gotoHomePage();
        if (app.contact().all().size() == 0){
            app.contact().create
                    (new ContactData().withFirstName("Vlada").withLastName("Petrova").withCompany("LTD").withMobile("8(888)000-00-00").withEmail("petrova@gmail.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification(){

        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact =
                new ContactData().withId(modifiedContact.getId()).withFirstName("Vlada").withLastName("Levchenko").withCompany("Software").withMobile("8(978)111-11-77").withEmail("oova@gmail.com");  // создаем локальную переменную, чтобы ее везде использовать

        app.contact().modify(contact);
       Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);

    }


}

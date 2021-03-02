package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData
                    ("Vlada", "Petrova", "LTD", "8(888)000-00-00", "petrova@gmail.com", "test1"));
        }
    }

    @Test
    public void testContactModification(){

        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        // контролируем выбор контакта для модификации
        app.getContactHelper().initContactModification(index);

        // создаем локальную переменную, чтобы ее везде использовать
        ContactData contact = new ContactData
                (before.get(index).getId(),"Vlada", "Levchenko", "Software", "8(978)111-11-77", "oova@gmail.com", null);
        app.getContactHelper().modifyContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());


        before.remove(index);

        before.add(contact);

        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }


}

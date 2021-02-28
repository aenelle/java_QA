package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData
                    ("Vlada", "Petrova", "LTD", "8(888)000-00-00", "petrova@gmail.com", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        //app.getContactHelper().selectedContactProfile();
        app.getContactHelper().initContactModification();
        // создаем локальную переменную, чтобы ее везде использовать
        ContactData contact = new ContactData
                (before.get(before.size() - 1).getId(),before.get(before.size() - 1).getFirstName(), before.get(before.size() - 1).getLastName(), "Software", "8(978)111-11-77", "oova@gmail.com", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnHomePages();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());


        before.remove(before.size() -1);
        System.out.println(new HashSet<Object>(before));
        System.out.println(new HashSet<Object>(after));

        before.add(contact);

        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        System.out.println(new HashSet<Object>(before));
        System.out.println(new HashSet<Object>(after));
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


    }
}

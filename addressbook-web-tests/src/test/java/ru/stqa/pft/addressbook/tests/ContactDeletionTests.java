package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

   /* @Test
    public void testContactDeletionFromProfile(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData
                    ("Vlada", "Petrova", "LTD", "8(888)000-00-00", "petrova@gmail.com", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        //app.getContactHelper().selectedContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);*/
        // этот тест по что не будет работать, поскольку  при удалении контактов из профиля мы с помощью селекта можем
        // точно регулировать какую группу мы будем удалять, при удалении из профиля мы выбираем произвольный Edit из списка
        // и продолжаем с ним работать, таким образом не знаем какой именно remove  надо сделать, и тту два варианта, или селектить
        // или сделать по принципу модификации группы


    //}


    @Test
    public void testContactDeletionFromHomePage(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData
                    ("Vlada", "Petrova", "LTD", "8(888)000-00-00", "petrova@gmail.com", "test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectedContact(before.size() -1);
        app.getContactHelper().deleteContactFromHomePages();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);


    }
}

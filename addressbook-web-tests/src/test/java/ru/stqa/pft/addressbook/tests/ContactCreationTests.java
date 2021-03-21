package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]>  validContactsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contscts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
    return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation2(ContactData contact) {
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    app.goTo().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
  }

  @Test  //тест как по лекции
  public void testContactCreation1() {
    app.goTo().gotoHomePage();
    app.contact().initContactCreating();
    File photo = new File("src/test/resources/cat.jpg");
    app.contact().fillContactForm(new ContactData().withFirstName("Ollo").withLastName("Ups").withCompany("LTD")
            .withMobilePhone("8(888)000-00-00").withEmail("petrova@gmail.com").withPhoto(photo),true);
    app.contact().submitContactCreating();
    app.contact().returnHomePages();

  }

  @Test  //(enabled = false)
  public void testContactCreation() {
    Contacts before = app.contact().all();
    app.contact().initContactCreating();
    File photo = new File("src/test/resources/cat.jpg");

    ContactData contact = (new ContactData().withFirstName("Avrora").withLastName("Petrova").withCompany("LTD")
            .withMobilePhone("8(888)000-00-00").withEmail("petrova@gmail.com").withPhoto(photo).withGroup("test1"));
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test //(enabled = false)
  public void testBadContactCreation() {

    Contacts before = app.contact().all();
    app.contact().initContactCreating();
    ContactData contact = (new ContactData().withFirstName("Vla'").withLastName("Petrova").withCompany("LTD")
            .withMobilePhone("8(888)000-00-00").withEmail("petrova@gmail.com").withGroup("test1"));
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}

  /*@Test //не тест, а метод поиска относительной директории
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println("Относительный путь" + currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/cat.jpg");  // создаем объект типа файл который соотв. уже существующему (не забываем расширение файла . jpg)
    System.out.println("Полный путь" + photo.getAbsolutePath());
    System.out.println(photo.exists()); //Проверяем что фото существует
  }
*/






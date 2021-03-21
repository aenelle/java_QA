package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.File;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @Test //тест как по лекции
  public void testContactCreation1() {
    app.goTo().gotoHomePage();
    app.contact().initContactCreating();
    File photo = new File("src/test/resources/cat.jpg");
    app.contact().fillContactForm(new ContactData().withFirstName("Ollo").withLastName("Ups").withCompany("LTD")
            .withMobilePhone("8(888)000-00-00").withEmail("petrova@gmail.com").withPhoto(photo),true);
    app.contact().submitContactCreating();
    app.contact().returnHomePages();

  }

  @Test //(enabled = false)
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






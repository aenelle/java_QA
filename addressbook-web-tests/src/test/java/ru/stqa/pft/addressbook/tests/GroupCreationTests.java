package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreating();
    app.getGroupHelper().fillGroupForm(new GroupDate("test1", "test2", "test2"));
    app.getGroupHelper().submitGroupCreating();
    app.getGroupHelper().returnToGroupPage();
    //wd.findElement(By.linkText("Logout")).click();
  }

}

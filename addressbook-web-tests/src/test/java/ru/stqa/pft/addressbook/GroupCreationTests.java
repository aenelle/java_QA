package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    gotoGroupPage();
    initGroupCreating();
    fillGroupForm(new GroupDate("test1", "test2", "test2"));
    submitGroupCreating();
    returnToGroupPage();
    //wd.findElement(By.linkText("Logout")).click();
  }

}

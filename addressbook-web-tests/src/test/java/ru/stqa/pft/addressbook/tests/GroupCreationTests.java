package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    List<GroupDate> before = app.group().list();
    GroupDate group = new GroupDate("test254", null, null);
    app.group().create(group);
    List<GroupDate> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);


    before.add(group);
    Comparator<? super GroupDate> byId = Comparator.comparingInt(GroupDate::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}

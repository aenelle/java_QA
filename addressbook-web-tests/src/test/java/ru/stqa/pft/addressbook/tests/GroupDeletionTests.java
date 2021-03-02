package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.List;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePrecondition(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupDate("test1", "test2", "test3"));
    }

  }


  @Test
  public void testGroupDeletion() {
    List<GroupDate> before = app.getGroupHelper().getGroupList();
    int index = before.size() -1;
    app.getGroupHelper().selectGroup(index);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupDate> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
      Assert.assertEquals(before, after);
    }
  }



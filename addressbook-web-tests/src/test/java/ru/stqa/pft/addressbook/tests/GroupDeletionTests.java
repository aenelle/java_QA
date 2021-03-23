package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePrecondition(){
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupDate().withName("test1"));
    }

  }


  @Test
  public void testGroupDeletion() {
    Groups before = app.db().groups();
    GroupDate deletedGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);
    assertEquals(app.group().count(), before.size() -1);
    Groups after = app.db().groups();;
    assertThat(after, equalTo(before.withOut(deletedGroup)));
    verifyGroupListInUi();
    }

}



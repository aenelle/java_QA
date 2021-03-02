package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupDate("test1", "test2", "test3"));
        }

    }

    @Test
    public void testCroupModification() {
        List<GroupDate> before = app.getGroupHelper().getGroupList();
        int index = before.size() - 1;
        GroupDate group = new GroupDate(before.get(index).getId(), "test1", "test2", "testPRO");
        app.getGroupHelper().modifyGroup(index, group);
        List<GroupDate> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupDate> byId = Comparator.comparingInt(GroupDate::getId);
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);

    }



}

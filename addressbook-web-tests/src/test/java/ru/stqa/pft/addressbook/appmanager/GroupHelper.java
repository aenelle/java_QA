package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.List;


public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void initGroupCreating() {
        click(By.name("new"));
    }
    public void fillGroupForm(GroupDate groupDate) {
        type(By.name("group_name"), groupDate.getName());
        type(By.name("group_header"), groupDate.getHeader());
        type(By.name("group_footer"), groupDate.getFooter());
    }
    public void submitGroupCreating() {
        click(By.name("submit"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupDate group) {
        initGroupCreating();
        fillGroupForm(group);
        submitGroupCreating();
        groupCache = null;
        returnToGroupPage();
    }


    public void modify(GroupDate group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }


    public void delete(GroupDate group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCache = null;
        returnToGroupPage();
    }


    public boolean isThereAGroup() {
        return  isElementPresent(By.name("selected[]"));
    }
    public int count(){
        return wd.findElements(By.name("selected[]")).size();
    }

    private Groups groupCache = null;


    public Groups all() {
        if(groupCache != null){
            return new Groups(groupCache);
        }

        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupDate().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }
    public void removeContact(int groupId, int contactId) {
        wd.findElement(By.cssSelector("select[name='group']")).click();
        new Select(wd.findElement(By.name("group"))).selectByValue(Integer.toString(groupId));
        wd.findElement(By.cssSelector("input[id='" + contactId + "']")).click();
        wd.findElement(By.name("remove")).click();
    }

    public GroupDate findFreeGroup(Groups groups, ContactData contact) {
        GroupDate foundGroup = null;
        for (GroupDate group : groups) {
            if (!contact.getGroups().contains(group)) {
                foundGroup = group;
                break;
            }
        }
        return foundGroup;
    }



}

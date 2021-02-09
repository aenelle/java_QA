package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.UserData;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void initUsersCreating() {
        click(By.linkText("add new"));
    }

    public void fillUserForm(UserData userData) {
        type(By.name("firstname"), userData.getFirstName());
        type(By.name("lastname"), userData.getLastName());
        type(By.name("company"), userData.getCompany());
        type(By.name("mobile"), userData.getMobile());
        type(By.name("email"),userData.getEmail());
    }

    public void submitUserCreating() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectedUserProfile() {
        click(By.xpath("(//input[@name='selected[]'])"));
    }

    public void initUserModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitUserModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void deleteUsers() {
        click(By.xpath("(//input[@name='update'])[3]"));
    }

    public void returnHomePages() {
        click(By.linkText("home page"));
    }

    public void deleteUsersFromHomePages() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }
}

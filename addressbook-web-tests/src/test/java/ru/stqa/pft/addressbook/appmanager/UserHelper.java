package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.UserData;

public class UserHelper extends HelperBase {
    private WebDriver wd;

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

}

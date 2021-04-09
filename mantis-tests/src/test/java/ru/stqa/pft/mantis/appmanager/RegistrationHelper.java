package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{

  public RegistrationHelper(ApplicationManager app){
   super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Зарегистрироваться']"));

  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"),username);
    click(By.xpath("//form[@id = 'login-form']//input[@type='submit']"));

    type(By.name("password"), password);
    click(By.xpath("//form[@id = 'login-form']//input[@type='submit']"));
  }

  public void changePassword(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
  }
  public void showUserList() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  public void selectUser(String userId) {
    wd.get(app.getProperty("web.baseUrl") + String.format("/manage_user_edit_page.php?user_id=%s",userId));
  }

  public void clickChangePassword(){
    click(By.xpath("//form[@id = 'manage-user-reset-form']//input[@type='submit']"));
  }

}

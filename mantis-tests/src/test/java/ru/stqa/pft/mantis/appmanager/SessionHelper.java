package ru.stqa.pft.mantis.appmanager;
import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import java.util.List;


public class SessionHelper extends HelperBase{
  public SessionHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"),username);
    click(By.xpath("//form[@id = 'login-form']//input[@type='submit']"));
    type(By.name("password"), password);
    click(By.xpath("//form[@id = 'login-form']//input[@type='submit']"));
  }

  public void updateUserPassword(String confirmationLink,String username, String password) {
    wd.get(confirmationLink);
    type(By.name("realname"), username);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }

  public void usersList() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  public void selectUser(String userId) {
    wd.get(app.getProperty("web.baseUrl") + String.format("/manage_user_edit_page.php?user_id=%s",userId));
  }

  public void changePassword(){
    click(By.xpath("//form[@id = 'manage-user-reset-form']//input[@type='submit']"));
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}
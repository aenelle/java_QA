package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final GroupHelper groupHelper = new GroupHelper();

    public void init() {
        groupHelper.wd = new FirefoxDriver();
        groupHelper.wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        groupHelper.wd.get("http://localhost/addressbook/group.php");
        Login("admin", "secret");
    }

    public void Login(String username, String password) {
      groupHelper.wd.findElement(By.name("user")).click();
      groupHelper.wd.findElement(By.name("user")).clear();
      groupHelper.wd.findElement(By.name("user")).sendKeys(username);
      groupHelper.wd.findElement(By.name("pass")).clear();
      groupHelper.wd.findElement(By.name("pass")).sendKeys(password);
      groupHelper.wd.findElement(By.id("LoginForm")).submit();
    }

    public void gotoGroupPage() {
      groupHelper.wd.findElement(By.linkText("groups")).click();
    }

    public void stop() {
        groupHelper.wd.quit();
    }

    public boolean isElementPresent(By by) {
      try {
        groupHelper.wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    public boolean isAlertPresent() {
      try {
        groupHelper.wd.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }
}

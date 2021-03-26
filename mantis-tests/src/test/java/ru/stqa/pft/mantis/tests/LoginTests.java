package ru.stqa.pft.mantis.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase{

@Test

    public void testLogin() throws Exception{
      HttpSession session = app.newSession();
      assertTrue(session.login("administrator", "root"));
      assertTrue(session.isLoggedInAs("administrator"));

    }
  }


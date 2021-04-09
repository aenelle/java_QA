package ru.stqa.pft.mantis.tests;

import com.google.common.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import com.solidfire.gson.Gson;
import com.solidfire.gson.JsonElement;
import com.solidfire.gson.JsonParser;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import org.testng.SkipException;
import ru.stqa.pft.mantis.model.Issue;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),
                "mantisbt-2.25.0/config/config_inc.php", "mantisbt-2.25.0/config/config_inc.php.back");
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("mantisbt-2.25.0/config/config_inc.php.back", "mantisbt-2.25.0/config/config_inc.php");
        app.stop();
    }
    boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        String json = RestAssured.get(String.format("http://bugify.stqa.ru/api/issues/%s.json", issueId)).asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
        return !issue.iterator().next().getState().equals("3");
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
    public ApplicationManager getApp() {
        return app;
    }
}

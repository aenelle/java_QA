package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }
    @BeforeMethod
    public void LogTestStart(Method m, Object[] p) {
        logger.info("Start test" + m.getName() + "with parameters " + Arrays.asList(p));
    }
    @AfterMethod (alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test" + m.getName());
    }

    public void verifyGroupListInUi() {
        if (Boolean.getBoolean("verifyUI")){
            Groups dbGroups =  app.db().groups();
            Groups uiGroups =  app.group().all();
            //нам нужно организовать правильное сравнение если просто сравнивать uiGroups  qualTo(dbGroups) результатом будет несоответствие данных,
            // поэтому с помощью потока нам надо упросить, вывести только идентификатор и имя lesson 7.5 time 4.15
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupDate().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
        }
    }
    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((g) -> new ContactData().withId(g.getId()).withFirstName(g.getFirstName()))
                    .collect(Collectors.toSet())));
        }
    }
    public void verifyContactsInGroupUI(GroupDate group) {
        //проверка, что указано системное свойство verifyUI - по умолчанию false (без проверки)
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContactSet = app.db().groupById(group.getId()).getContacts();
            Contacts uiContactSet = app.contact().all();
            assertThat(uiContactSet, equalTo(dbContactSet.stream()
                    .map((c) -> new ContactData().withId(c.getId()).withFirstName(c.getFirstName()).withLastName(c.getLastName())
                            .withAllPhones(c.getAllPhones()).withAllEmails(c.getAllEmails()).withAddress(c.getAddress()))
                    .collect(Collectors.toSet())));
        }
    }
}

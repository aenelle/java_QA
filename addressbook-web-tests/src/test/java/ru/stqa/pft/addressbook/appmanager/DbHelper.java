package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class DbHelper {
  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupDate> result = session.createQuery("from GroupDate").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public ContactData contactNotInGroup() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where groups.size = 0 and deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }

  public ContactData contactById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery(String.format("from ContactData where id = %s ", id)).list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }

  public ContactData contactInGroup() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery(String.format("from ContactData where groups.size > 0 and deprecated = '0000-00-00'")).list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }

  public ContactData contactWithoutGroup() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where groups.size = 0 and deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }

  public Contacts contactWithoutGroups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where groups.size = 0 and deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public GroupDate groupById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupDate> result = session.createQuery(String.format("from GroupData where group_id = '%s'", id)).list();
    session.getTransaction().commit();
    session.close();
    GroupDate group = result.iterator().next(); //в результате всегда будет список из одной группы, поэтому выбираем любую
    return group;

  }
}




package ru.stqa.pft.mantis.appmanager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.Users;

public class DbHelper {
  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Users mantisUser() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Users result = (Users) session.createQuery("from Users where email like '%@localhost' and access_level <> 'reporter'").list().get(2);
    session.getTransaction().commit();
    session.close();
    return result;
  }
}

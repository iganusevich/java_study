package mantis.appmanager;

import mantis.models.User;
import mantis.models.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper  {
    private final SessionFactory sessionFactory;

    public DbHelper(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public User userByUsername(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result =
                session.createQuery( "from User where username = :username")
                        .list(); //.setParameter("id", username)
        session.getTransaction().commit();
        session.close();
        return new Users(result).stream().iterator().next();
    }

    public Users users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery( "from User where username != 'administrator'").list();
        session.getTransaction().commit();
        session.close();
        return new Users(result);
    }

    public void hbConnection(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery( "from User").list();
        session.getTransaction().commit();
        session.close();
        for ( User user : result) {
            System.out.println( user );
            //System.out.println(contact.getGroups());
        }
        //return new Users(result);

    }

}

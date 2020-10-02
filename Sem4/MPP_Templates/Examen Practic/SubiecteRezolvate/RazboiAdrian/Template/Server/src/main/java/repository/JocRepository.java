package repository;

import model.Joc;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class JocRepository {

    private static SessionFactory sessionFactory;

    public JocRepository() {
        initialize();
    }

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public Joc findOne(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Joc joc = session.createQuery("FROM Joc WHERE id = :id", Joc.class)
                        .setInteger("id", id)
                        .getSingleResult();
                transaction.commit();
                return joc;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }

    public Joc save(Joc joc) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(joc);
                transaction.commit();
                return null;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return joc;
    }
}

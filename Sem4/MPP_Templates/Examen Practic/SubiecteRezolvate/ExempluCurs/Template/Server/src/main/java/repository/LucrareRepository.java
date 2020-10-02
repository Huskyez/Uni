package repository;

import model.Corector;
import model.Lucrare;
import model.Participant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.stream.StreamSupport;

public class LucrareRepository {

    static SessionFactory sessionFactory;
    public LucrareRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public LucrareRepository() {
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

    public Lucrare save(Lucrare lucrare) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(lucrare);
                transaction.commit();
                return null;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return lucrare;
    }

    public Iterable<Lucrare> findAll(){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Iterable<Lucrare> lucrari = session.createQuery("FROM Lucrare", Lucrare.class).list();
                transaction.commit();
                return lucrari;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }



    public Lucrare findOne(Integer paperId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Lucrare lucrare = session.createQuery("FROM Lucrare WHERE paperId = :paperId", Lucrare.class)
                        .setInteger("paperId", paperId)
                        .getSingleResult();
                transaction.commit();
                return lucrare;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }

    public Lucrare update(Lucrare lucrare) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.update(lucrare);
                transaction.commit();
                return lucrare;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }
}

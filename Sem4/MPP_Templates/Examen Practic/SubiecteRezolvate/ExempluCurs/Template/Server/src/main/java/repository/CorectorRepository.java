package repository;

import model.Corector;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CorectorRepository {

    private SessionFactory sessionFactory;

    public CorectorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Corector save(Corector corector) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(corector);
                transaction.commit();
                return null;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return corector;
    }

    public Corector findOne(String username) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Corector corector = session.createQuery("FROM Corector WHERE username = :username", Corector.class)
                        .setString("username", username)
                        .getSingleResult();
                transaction.commit();
                return corector;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }
}

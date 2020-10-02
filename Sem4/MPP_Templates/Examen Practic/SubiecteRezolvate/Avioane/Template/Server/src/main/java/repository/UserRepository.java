package repository;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserRepository {

    private SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User save(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
                return null;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return user;
    }

    public User findOne(String username) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.createQuery("FROM User WHERE username = :username", User.class)
                        .setString("username", username)
                        .getSingleResult();
                transaction.commit();
                return user;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }
}

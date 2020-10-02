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

    public User update(User user){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.update(user);
                transaction.commit();
                return user;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }

    public User delete(Integer id){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.createQuery("FROM model.User WHERE id = :id", User.class).setParameter("id", id).getSingleResult();
                session.delete(user);
                transaction.commit();
                return user;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }

    public User findOne(String username) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.createQuery("FROM model.User WHERE username = :username", User.class)
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

    public Iterable<User> findAll(){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Iterable<User> all = session.createQuery("FROM model.User", User.class).list();
                transaction.commit();
                return all;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }

    public Integer findIndex() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = (User) session.createQuery("FROM model.User u ORDER BY u.id DESC").setMaxResults(1).uniqueResult();
                transaction.commit();
                //return the id;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }
}

package repository;

import model.Jucator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class JucatorRepository {

    private SessionFactory sessionFactory;

    public JucatorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Jucator save(Jucator jucator) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(jucator);
                transaction.commit();
                return null;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return jucator;
    }

    public Jucator update(Jucator jucator){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.update(jucator);
                transaction.commit();
                return jucator;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }

    public Jucator delete(Integer id){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Jucator jucator = session.createQuery("FROM Jucator WHERE id = :id", Jucator.class).setInteger("id",id).getSingleResult();
                session.delete(jucator);
                transaction.commit();
                return jucator;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }

    public Jucator findOne(String username) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Jucator jucator = session.createQuery("FROM Jucator WHERE username = :username", Jucator.class)
                        .setString("username", username)
                        .getSingleResult();
                transaction.commit();
                return jucator;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }

    public Iterable<Jucator> findAll(){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Iterable<Jucator> all = session.createQuery("FROM Jucator", Jucator.class).list();
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
                Jucator jucator = (Jucator) session.createQuery("FROM Jucator u ORDER BY u.id DESC").setMaxResults(1).uniqueResult();
                transaction.commit();
                //return the id;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }
}

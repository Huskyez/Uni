package repository;

import model.Game;
import model.TestClass;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class GameRepository {
    private static SessionFactory sessionFactory;

    public GameRepository() {
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
    public Game save(Game game) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(game);
                transaction.commit();
                return null;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return game;
    }

    public Iterable<Game> findAll(){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Iterable<Game> games = session.createQuery("FROM Game", Game.class).list();
                transaction.commit();
                return games;
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
                Game game = (Game)session.createQuery("FROM Game g ORDER BY g.id DESC").setMaxResults(1).uniqueResult();
                transaction.commit();
                if(game == null)
                    return 1;
                else
                    return game.getId()+1;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }
    public Game findOneByUsers(String user1, String user2) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Game> games = session.createQuery("FROM Game WHERE user1 = :user1 AND user2 = :user2", Game.class).setString("user1", user1).setString("user2",user2).list();
                transaction.commit();
                if(games != null)
                return games.get(0);
                else
                    return null;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }

    public Iterable<Game> findOne(String user1) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Game> games = session.createQuery("FROM Game WHERE user1 = :user1 or user2 = :user1", Game.class).setString("user1", user1).list();
                transaction.commit();
                if(games != null)
                    return games;
                else
                    return null;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return null;
    }
    public TestClass save(TestClass game) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(game);
                transaction.commit();
                return null;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return game;
    }

    public TestClass findOneTest(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                TestClass user = session.createQuery("FROM TestClass WHERE id = :id", TestClass.class)
                        .setInteger("id", id)
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

package repository;

import model.Corector;
import model.Participant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ParticipantRepository {

    private SessionFactory sessionFactory;

    public ParticipantRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Participant save(Participant participant) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(participant);
                transaction.commit();
                return null;
            } catch (Exception exception) {
                if (transaction != null) transaction.rollback();
            }
        }
        return participant;
    }

}

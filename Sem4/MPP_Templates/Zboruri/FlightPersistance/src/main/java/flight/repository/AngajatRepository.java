package flight.repository;

import flight.domain.Angajat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class AngajatRepository {
//    private DatabaseUtils dbUtils;
//
//    private static final Logger logger = LogManager.getLogger();
//
//    public AngajatRepository(Properties props) {
//        dbUtils = new DatabaseUtils(props);
//    }
//
//    public Angajat logIn(String username, String password){
//        logger.traceEntry("Finding an employee with u:{} ",username);
//        try(Connection con = dbUtils.getConnection();
//            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Angajati WHERE Username = ? AND Password = ?")){
//                preparedStatement.setString(1,username);
//                preparedStatement.setString(2,password);
//                try(ResultSet result = preparedStatement.executeQuery()){
//                    if(result.next()) {
//                        String user = result.getString("Username");
//                        String pass = result.getString("Password");
//                        return new Angajat(user, pass);
//                    }
//                }
//        } catch (SQLException e) {
//            logger.error(e);
//            e.printStackTrace();
//        }
//        logger.traceExit("Couldn't log in");
//        return null;
//    }
//
//    public Iterable<Angajat> findAll() {
//        logger.traceEntry();
//        List<Angajat> angajati = new ArrayList<>();
//        try(Connection con = dbUtils.getConnection();
//            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Angajati")){
//            try(ResultSet result = preparedStatement.executeQuery()){
//                while(result.next()){
//                    String ID = result.getString("Username");
//                    String pass = result.getString("Password");
//                    angajati.add(new Angajat(ID,pass));
//                }
//            }
//        } catch (SQLException e) {
//            logger.error(e);
//        }
//        logger.traceExit(angajati);
//        return angajati;
//    }
    static SessionFactory sessionFactory;

    public AngajatRepository(){
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

    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }

    public Angajat logIn(String username, String password){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Angajat> angajati =
                        session.createQuery("FROM Angajat WHERE ID=:u AND password=:p", Angajat.class)
                        .setString("u",username).setString("p",password).list();
                Angajat angajat = null;
                for (Angajat a : angajati) {
                    angajat = a;
                }
                tx.commit();
                return angajat;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    public Iterable<Angajat> findAll() {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Angajat> angajati =
                        session.createQuery("FROM Angajat", Angajat.class)
                                .list();
                tx.commit();
                return angajati;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }
}

package repository;

import domain.Angajat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class AngajatRepository {
    private DatabaseUtils dbUtils;

    private static final Logger logger = LogManager.getLogger();

    public AngajatRepository(Properties props) {
        dbUtils = new DatabaseUtils(props);
    }

    public Angajat findOne(String username, String password){
        logger.traceEntry("Logging in an employee with u:{} and p:{}",username,password);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Angajati WHERE Username LIKE ? AND Password LIKE ?")){
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            try(ResultSet result = preparedStatement.executeQuery()){
                if(result.next()){
                    Integer ID = result.getInt("ID");
                    String User = result.getString("Username");
                    String Password = result.getString("Password");
                    Angajat angajat = new Angajat(ID,User,Password);
                    logger.traceExit();
                    return angajat;
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        logger.traceExit("Couldn't log in");
        return null;
    }

}

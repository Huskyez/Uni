package repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {
    private Properties props;
    private static final Logger logger = LogManager.getLogger();

    public DatabaseUtils(Properties props){
        this.props = props;
    }

    private Connection instance = null;
    private Connection getNewConnection(){
        logger.traceEntry();
        String driver = props.getProperty("zboruri.jdbc.driver");
        String url = props.getProperty("zboruri.jdbc.url");
        logger.info("Connecting to {}", url);
        Connection con = null;
        try{
            Class.forName(driver);
            logger.info("Loaded driver {}",driver);
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
        } catch (SQLException e) {
            System.out.println("Error getting connection" + e);;
        }
        return con;
    }

    public Connection getConnection(){
        logger.traceEntry();
        try{
            if(instance == null || instance.isClosed())
                instance = getNewConnection();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB"+e);
        }
        logger.traceExit(instance);
        return instance;
    }
}

package flight.repository;

import flight.domain.Zbor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Component
public class ZborRepository implements ZborRepoInterface {
    private DatabaseUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();
    public Properties serverProps;

    public ZborRepository() {
        try {
            setProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbUtils = new DatabaseUtils(serverProps);
    }

    public void setProperties() throws IOException {
        serverProps = new Properties();
        serverProps.load(ZborRepository.class.getResourceAsStream("/flightserver.properties"));
    }
    @Override
    public void save(Zbor entity) {
        logger.traceEntry("Saving flight with id: {}", entity.getId());
        try (Connection con = dbUtils.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Zboruri VALUES (?,?,?,?,?)")) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getDestinatie());
            preparedStatement.setString(3, entity.getPlecare().toString());
            preparedStatement.setString(4, entity.getAeroport());
            preparedStatement.setInt(5, entity.getLocuri());
            int result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer ID) {
        logger.traceEntry("Deleting flight with id={}", ID);
        try (Connection con = dbUtils.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Zboruri WHERE id=?")) {
            preparedStatement.setInt(1, ID);
            int result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
    }

    @Override
    public int size() {
        logger.traceEntry();
        try (Connection con = dbUtils.getConnection();
             PreparedStatement preStmt = con.prepareStatement("SELECT count(*) AS [SIZE] FROM Zboruri")) {
            try (ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    logger.traceExit(result.getInt("SIZE"));
                    return result.getInt("SIZE");
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return 0;
    }

    @Override
    public Zbor findOne(Integer ID) {
        logger.traceEntry("Finding flight with id = {}", ID);
        try (Connection con = dbUtils.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Zboruri WHERE ID = ?")) {
            preparedStatement.setInt(1, ID);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    Integer Id = result.getInt("ID");
                    String Destinatia = result.getString("Destinatia");
                    String PlecareS = result.getString("Plecare");
                    LocalDateTime Plecare = LocalDateTime.parse(PlecareS);
                    String Aeroport = result.getString("Aeroport");
                    Integer Locuri = result.getInt("Locuri");
                    logger.traceExit();
                    return new Zbor(Id, Destinatia, Plecare, Aeroport, Locuri);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit("Couldn't find the flight with id: {}", ID);
        return null;
    }

    @Override
    public Iterable<Zbor> findAll() {
        logger.traceEntry();
        List<Zbor> zboruri = new ArrayList<>();
        try (Connection con = dbUtils.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Zboruri")) {
            try (ResultSet result = preparedStatement.executeQuery()) {
                System.out.println(con.isClosed()? "yes":"no");
                while (result.next()) {
                    Integer Id = result.getInt("ID");
                    String Destinatia = result.getString("Destinatia");
                    String PlecareS = result.getString("Plecare");
                    LocalDateTime Plecare = LocalDateTime.parse(PlecareS);
                    String Aeroport = result.getString("Aeroport");
                    Integer Locuri = result.getInt("Locuri");
                    zboruri.add(new Zbor(Id, Destinatia, Plecare, Aeroport, Locuri));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit(zboruri);
        return zboruri;
    }

    @Override
    public Iterable<Zbor> findByDestinatieAndPlecare(String destinatie, String plecare) {
        logger.traceEntry();
        List<Zbor> zboruri = new ArrayList<>();
        try (Connection con = dbUtils.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Zboruri WHERE Destinatia = ? AND DATE(Plecare) = ? ")) {
            preparedStatement.setString(1, destinatie);
            preparedStatement.setString(2, plecare);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    Integer Id = result.getInt("ID");
                    String Destinatia = result.getString("Destinatia");
                    String PlecareS = result.getString("Plecare");
                    LocalDateTime Plecare = LocalDateTime.parse(PlecareS);
                    String Aeroport = result.getString("Aeroport");
                    Integer Locuri = result.getInt("Locuri");
                    zboruri.add(new Zbor(Id, Destinatia, Plecare, Aeroport, Locuri));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit(zboruri);
        return zboruri;
    }

    @Override
    public Zbor findOneByDestinatieAndDateTime(String Destinatie, String DateTime) {
        logger.traceEntry();
        try (Connection con = dbUtils.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Zboruri WHERE Destinatia = ? AND Plecare = ?")) {
            preparedStatement.setString(1, Destinatie);
            preparedStatement.setString(2, DateTime);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    Integer Id = result.getInt("ID");
                    String Destinatia = result.getString("Destinatia");
                    String PlecareS = result.getString("Plecare");
                    LocalDateTime Plecare = LocalDateTime.parse(PlecareS);
                    String Aeroport = result.getString("Aeroport");
                    Integer Locuri = result.getInt("Locuri");
                    logger.traceExit();
                    return new Zbor(Id, Destinatia, Plecare, Aeroport, Locuri);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Zbor updateZbor(Integer Id, Integer locuri) {
        logger.traceEntry("Updating flight with id={}", Id);
        try (Connection con = dbUtils.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("UPDATE Zboruri SET Locuri = ? WHERE ID =?")) {
            preparedStatement.setInt(2, Id);
            preparedStatement.setInt(1,locuri);
            int result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return null;
    }
}
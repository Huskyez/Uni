package flight.repository;

import flight.domain.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ClientRepository implements ClientRepoInterface{
    private DatabaseUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public ClientRepository(Properties props) {
        dbUtils = new DatabaseUtils(props);
    }

    @Override
    public void save(Client entity) {
        logger.traceEntry("Saving client with id: {}", entity.getID());
        try(Connection con = dbUtils.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Clienti VALUES (?,?,?)")){
            preparedStatement.setInt(1,entity.getID());
            preparedStatement.setString(2,entity.getNume());
            preparedStatement.setString(3,entity.getAdresa());
            int result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer ID) {
        logger.traceEntry("Deleting client with id={}",ID);
        try(Connection con=dbUtils.getConnection();
            PreparedStatement preparedStatement=con.prepareStatement("DELETE FROM Clienti WHERE id=?")){
            preparedStatement.setInt(1,ID);
            int result=preparedStatement.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
        }
        logger.traceExit();
    }

    @Override
    public int size() {
        logger.traceEntry();
        try(Connection con=dbUtils.getConnection();
            PreparedStatement preStmt=con.prepareStatement("SELECT count(*) AS [SIZE] FROM Clienti")) {
            try(ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    logger.traceExit(result.getInt("SIZE"));
                    return result.getInt("SIZE");
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
        }
        return 0;
    }

    @Override
    public Client findOne(Integer ID) {
        logger.traceEntry("Finding client with id = {}", ID);
        try(Connection con = dbUtils.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Clienti WHERE ID = ?")){
            preparedStatement.setInt(1,ID);
            try(ResultSet result = preparedStatement.executeQuery()){
                if(result.next()){
                    Integer Id = result.getInt("ID");
                    String Nume = result.getString("Nume");
                    String Adresa = result.getString("Adresa");
                    Client client = new Client(Id,Nume,Adresa);
                    logger.traceExit();
                    return client;
                }
            }
        }
        catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit("Couldn't find the client with id: {}", ID);
        return null;
    }

    @Override
    public Iterable<Client> findAll() {
        logger.traceEntry();
        List<Client> clienti = new ArrayList<>();
        try(Connection con = dbUtils.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Clienti")){
            try(ResultSet result = preparedStatement.executeQuery()){
                while(result.next()){
                    Integer ID = result.getInt("ID");
                    String Nume = result.getString("Nume");
                    String Adresa = result.getString("Adresa");
                    Client client = new Client(ID, Nume, Adresa);
                    clienti.add(client);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit(clienti);
        return clienti;
    }

    @Override
    public Client findOneByNumeAndAdress(String nume, String adresa) {
        logger.traceEntry();
        try(Connection con = dbUtils.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Clienti WHERE Nume=? AND Adresa = ?")) {
            preparedStatement.setString(1,nume);
            preparedStatement.setString(2,adresa);
            try(ResultSet result = preparedStatement.executeQuery()){
                while(result.next()){
                    Integer Id = result.getInt("ID");
                    String Nume = result.getString("Nume");
                    String Adresa = result.getString("Adresa");
                    return new Client(Id,Nume,Adresa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.traceExit();
        return null;
    }
}

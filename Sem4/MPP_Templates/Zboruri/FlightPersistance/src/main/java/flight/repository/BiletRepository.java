package flight.repository;

import flight.domain.Bilet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BiletRepository implements BiletRepoInterface{
    private DatabaseUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public BiletRepository(Properties props) {
        dbUtils = new DatabaseUtils(props);
    }

    @Override
    public void save(Bilet entity) {
        logger.traceEntry("Saving ticket with id: {}", entity.getID());
        try(Connection con = dbUtils.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Bilete VALUES (?,?,?,?,?)")){
            preparedStatement.setInt(1,entity.getID());
            preparedStatement.setInt(2,entity.getZborID());
            preparedStatement.setInt(3,entity.getClientID());
            preparedStatement.setString(4,entity.getTuristi());
            preparedStatement.setInt(5,entity.getLocuri());
            int result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit();

    }

    @Override
    public void delete(Integer ID) {
        logger.traceEntry("Deleting ticket with id={}",ID);
        try(Connection con=dbUtils.getConnection();
            PreparedStatement preparedStatement=con.prepareStatement("DELETE FROM Bilete WHERE id=?")){
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
            PreparedStatement preStmt=con.prepareStatement("SELECT COUNT(*) AS [SIZE] FROM Bilete")) {
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
    public Bilet findOne(Integer ID) {
        logger.traceEntry("Finding ticket with id = {}", ID);
        try(Connection con = dbUtils.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Bilete WHERE ID = ?")){
            preparedStatement.setInt(1,ID);
            try(ResultSet result = preparedStatement.executeQuery()){
                if(result.next()){
                    Integer Id = result.getInt("ID");
                    Integer IdZbor = result.getInt("ZborID");
                    Integer IdClient = result.getInt("ClientId");
                    String Turisti = result.getString("Turisti");
                    Integer Locuri = result.getInt("Locuri");
                    Bilet bilet = new Bilet(Id,IdZbor,IdClient,Turisti,Locuri);
                    logger.traceExit();
                    return bilet;
                }
            }
        }
        catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit("Couldn't find the ticket with id: {}", ID);
        return null;
    }

    @Override
    public Iterable<Bilet> findAll() {
        logger.traceEntry();
        List<Bilet> bilete = new ArrayList<>();
        try(Connection con = dbUtils.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Bilete")){
            try(ResultSet result = preparedStatement.executeQuery()){
                while(result.next()){
                    Integer Id = result.getInt("ID");
                    Integer IdZbor = result.getInt("ZborID");
                    Integer IdClient = result.getInt("ClientId");
                    String Turisti = result.getString("Turisti");
                    Integer Locuri = result.getInt("Locuri");
                    Bilet bilet = new Bilet(Id,IdZbor,IdClient,Turisti,Locuri);
                    bilete.add(bilet);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit(bilete);
        return bilete;
    }

    @Override
    public Integer findCorrectIndex() {
        logger.traceEntry();
        try(Connection con = dbUtils.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT MAX(ID) AS [Index] FROM Bilete")) {
            try(ResultSet result = preparedStatement.executeQuery()){
                while(result.next()){
                    Integer index = result.getInt("Index");
                    return index;
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit();
        return -1;
    }
}

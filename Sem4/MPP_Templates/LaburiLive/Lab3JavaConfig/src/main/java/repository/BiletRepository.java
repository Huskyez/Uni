package repository;

import domain.Bilet;
import domain.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BiletRepository implements Repository<Integer, Bilet> {
    private DatabaseUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public BiletRepository(Properties props) {
        dbUtils = new DatabaseUtils(props);
    }

    @Override
    public void save(Bilet entity) {
        logger.traceEntry("Saving ticket with id: {}", entity.getID());
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Bilete VALUES (?,?,?)")){
            preparedStatement.setInt(1,entity.getID());
            preparedStatement.setInt(2,entity.getZborID());
            preparedStatement.setInt(3,entity.getClientID());
            int result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit();

    }

    @Override
    public void delete(Integer ID) {
        logger.traceEntry("Deleting ticket with id={}",ID);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preparedStatement=con.prepareStatement("DELETE FROM Bilete WHERE id=?")){
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
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("SELECT count(*) AS [SIZE] FROM Bilete")) {
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
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Bilete WHERE ID = ?")){
            preparedStatement.setInt(1,ID);
            try(ResultSet result = preparedStatement.executeQuery()){
                if(result.next()){
                    Integer Id = result.getInt("ID");
                    Integer IdZbor = result.getInt("ZborID");
                    Integer IdClient = result.getInt("ClientId");
                    Bilet bilet = new Bilet(Id,IdZbor,IdClient);
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
        Connection con = dbUtils.getConnection();
        List<Bilet> bilete = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Bilete")){
            try(ResultSet result = preparedStatement.executeQuery()){
                while(result.next()){
                    Integer Id = result.getInt("ID");
                    Integer IdZbor = result.getInt("ZborID");
                    Integer IdClient = result.getInt("ClientId");
                    Bilet bilet = new Bilet(Id,IdZbor,IdClient);
                    bilete.add(bilet);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit(bilete);
        return bilete;
    }
}

package repository;

import domain.Client;
import domain.Zbor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class ZborRepository implements Repository <Integer, Zbor>{
    private DatabaseUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public ZborRepository(Properties props) {
        dbUtils = new DatabaseUtils(props);
    }


    @Override
    public void save(Zbor entity) {
        logger.traceEntry("Saving flight with id: {}", entity.getID());
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Zboruri VALUES (?,?,?,?,?)")){
            preparedStatement.setInt(1,entity.getID());
            preparedStatement.setString(2,entity.getDestinatie());
            Date dateP = new Date(entity.getPlecare().getDayOfMonth(),entity.getPlecare().getMonthValue(),entity.getPlecare().getYear());
            java.sql.Date sDate = new java.sql.Date(dateP.getTime());
            preparedStatement.setDate(3, sDate);
            preparedStatement.setString(4,entity.getAeroport());
            preparedStatement.setInt(5,entity.getLocuri());
            int result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer ID) {
        logger.traceEntry("Deleting flight with id={}",ID);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preparedStatement=con.prepareStatement("DELETE FROM Zboruri WHERE id=?")){
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
        try(PreparedStatement preStmt=con.prepareStatement("SELECT count(*) AS [SIZE] FROM Zboruri")) {
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
    public Zbor findOne(Integer ID) {
        logger.traceEntry("Finding flight with id = {}", ID);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Zboruri WHERE ID = ?")){
            preparedStatement.setInt(1,ID);
            try(ResultSet result = preparedStatement.executeQuery()){
                if(result.next()){
                    Integer Id = result.getInt("ID");
                    String Destinatia = result.getString("Destinatia");
                    String PlecareS = result.getString("Plecare");
                    List<String> listDate = Arrays.asList(PlecareS.split("-"));
                    LocalDateTime Plecare = LocalDateTime.of(Integer.parseInt(listDate.get(2)),Integer.parseInt(listDate.get(1)),Integer.parseInt(listDate.get(0)),0,0);
                    String Aeroport = result.getString("Aeroport");
                    Integer Locuri = result.getInt("Locuri");
                    logger.traceExit();
                    return new Zbor(Id,Destinatia,Plecare,Aeroport,Locuri);
                }
            }
        }
        catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit("Couldn't find the flight with id: {}", ID);
        return null;
    }

    @Override
    public Iterable<Zbor> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Zbor> zboruri = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Zboruri")){
            try(ResultSet result = preparedStatement.executeQuery()){
                while(result.next()){
                    Integer Id = result.getInt("ID");
                    String Destinatia = result.getString("Destinatia");
                    String PlecareS = result.getString("Plecare");
                    List<String> listDate = Arrays.asList(PlecareS.split("-"));
                    LocalDateTime Plecare = LocalDateTime.of(Integer.parseInt(listDate.get(2)),Integer.parseInt(listDate.get(1)),Integer.parseInt(listDate.get(0)),0,0);
                    String Aeroport = result.getString("Aeroport");
                    Integer Locuri = result.getInt("Locuri");
                    zboruri.add(new Zbor(Id,Destinatia,Plecare,Aeroport,Locuri));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.traceExit(zboruri);
        return zboruri;
    }
}

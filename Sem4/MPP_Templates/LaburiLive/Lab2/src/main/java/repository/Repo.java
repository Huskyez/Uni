package repository;

import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Repo {
    private JdbcUtils dbUtils;

    public Repo(Properties props){
        dbUtils=new JdbcUtils(props);
    }

    public int size() {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) as [SIZE] from Student")) {
            try(ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    return result.getInt("SIZE");
                }
            }
        }catch(SQLException ex){
            System.out.println("Error DB "+ex);
        }
        return 0;
    }

    public void save(Student entity) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Student values (?,?,?)")){
            preStmt.setInt(1,entity.getID());
            preStmt.setString(2,entity.getNume());
            preStmt.setInt(3,entity.getVarsta());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Error DB "+ex);
        }
    }

    public void delete(Integer integer) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Student where id=?")){
            preStmt.setInt(1,integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Error DB "+ex);
        }
    }
}

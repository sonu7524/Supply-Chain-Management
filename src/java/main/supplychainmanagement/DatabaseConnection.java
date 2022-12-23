package main.supplychainmanagement;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DatabaseConnection {
    private static final String dburl ="jdbc:mysql://127.0.0.1:3306/supply_chain";
    private static final String  dbuserName ="root";
    private static final String dbpassword = "Orai@123";
    public Statement getStatement(){
        Statement statement = null;
        Connection conn;
        try{
            conn = DriverManager.getConnection(dburl, dbuserName, dbpassword);
            statement = conn.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return statement;
    }
    public ResultSet getQueryTable(String query){
        Statement statement = getStatement();
        try{
            return statement.executeQuery(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    public static void main(String[] args){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ResultSet rs = databaseConnection.getQueryTable("Select email, first_name from customer");
        try {
            while (rs.next()){
                System.out.println(rs.getString("email")+" "+rs.getString("first_name"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}




package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Sen-Joysbright
 */
public final class DatabaseHandler {
    
    private static DatabaseHandler handler = null;
    
    private static final String DB_URL = "jdbc:derby:src/db/dat00;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    private DatabaseHandler(){
        createConnection();
        setupTableStaff("staff");
        setupTableCustomer("customer");
        setupTableRoom();
        setupTransactionTable();
        setupCheckinTable();
        
    }
    
    public static DatabaseHandler getInstance(){
        if(handler == null){
                handler = new DatabaseHandler();
        }
            return handler;
    }
    
    void createConnection(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
                 }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Cannot load database", "Database Error",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
     void setupTableStaff(String tableName){
        
        String TABLE_NAME = tableName;
        try {
            stmt = conn.createStatement();
            
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null,null,TABLE_NAME.toUpperCase(),null);
            
            if (tables.next()){
                System.out.println("Table " + TABLE_NAME + " \t already exists. Ready for go!");
            }
            else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "(" 
                        + "     id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                        + "     staffid varchar(200) PRIMARY KEY NOT NULL,\n"
                        + "     name varchar(200) NOT NULL,\n"
                        + "     rank varchar(200) NOT NULL,\n"
                        + "     password varchar(20) NOT NULL\n"
                        + " )");
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage() + "... setupDatabase");
        }
        finally {            
        }
    }
     
    void setupTableCustomer(String tableName){
        
        String TABLE_NAME = tableName;
        try {
            stmt = conn.createStatement();
            
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null,null,TABLE_NAME.toUpperCase(),null);
            
            if (tables.next()){
                System.out.println("Table " + TABLE_NAME + " \t already exists. Ready for go!");
            }
            else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "(" 
                        + "     id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                        + "     name varchar(200) NOT NULL,\n"
                        + "     customerid varchar(200) PRIMARY KEY NOT NULL,\n"
                        + "     gender varchar(200) NOT NULL,\n"
                        + "     passport varchar(200) NOT NULL,\n"
                        + "     card varchar(200) NOT NULL,\n"
                        + "     address varchar(20) NOT NULL\n"
                        + " )");
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage() + "... setupDatabase");
        }
        finally {            
        }
    }
    
    public ResultSet execQuery(String query){
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
             }
        catch (SQLException ex){
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        finally {
            
        }
        return result;        
    }
    
    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        }
        finally {
            
        }
    }

    private void setupTableRoom() {
       String TABLE_NAME = "room";
        try {
            stmt = conn.createStatement();
            
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null,null,TABLE_NAME.toUpperCase(),null);
            
            if (tables.next()){
                System.out.println("Table " + TABLE_NAME + " \t already exists. Ready for go!");
            }
            else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "(" 
                        + "     id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                        + "     roomid varchar(200) PRIMARY KEY NOT NULL,\n"
                        + "     type varchar(200),\n"
                        + "     number varchar(20),\n"
                        + "     amount varchar(200) NOT NULL,\n"
                        + "     floor varchar(100),\n" 
                        + "     availability varchar(100),\n" 
                        + "     discount varchar(200) NOT NULL\n"                       
                        + " )");
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage() + "... setupDatabase");
        }
        finally {            
        }
    }
    
    
    
    private void setupTransactionTable() {
       String TABLE_NAME = "transactions";
        try {
            stmt = conn.createStatement();
            
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null,null,TABLE_NAME.toUpperCase(),null);
            
            if (tables.next()){
                System.out.println("Table " + TABLE_NAME + " \t already exists. Ready for go!");
            }
            else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "(" 
                        + "     customerid varchar(200),\n"
                        + "     customerName varchar(200),\n"
                        + "     roomid varchar(200)  primary key,\n"
                        + "     roomAmount varchar(200),\n"
                        + "     time timestamp default CURRENt_TIMESTAMP,\n"
                        + "     FOREIGN KEY (customerid) REFERENCES CUSTOMER(customerid),\n"
                        + "     FOREIGN KEY (roomid) REFERENCES ROOM(roomid)\n"                        
                        + " )");
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage() + "... setupDatabase");
        }
        finally {            
        }
    }
    
    private void setupCheckinTable() {
       String TABLE_NAME = "checkin";
        try {
            stmt = conn.createStatement();
            
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null,null,TABLE_NAME.toUpperCase(),null);
            
            if (tables.next()){
                System.out.println("Table " + TABLE_NAME + " \t already exists. Ready for go!");
            }
            else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "(" 
                        + "     customerid varchar(200),\n"
                        + "     customerName varchar(200),\n"
                        + "     roomid varchar(200)  primary key,\n"
                        + "     roomAmount varchar(200),\n"
                        + "     time timestamp default CURRENt_TIMESTAMP,\n"
                        + "     renew_count integer default 0,\n"
                        + "     FOREIGN KEY (customerid) REFERENCES CUSTOMER(customerid),\n"
                        + "     FOREIGN KEY (roomid) REFERENCES ROOM(roomid)\n"                        
                        + " )");
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage() + "... setupDatabase");
        }
        finally {            
        }
    }
}

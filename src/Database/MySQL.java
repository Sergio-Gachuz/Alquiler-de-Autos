package Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL {
    private static Connection conn = null;
    private static String hostname   = "localhost";
    private static String dbname = "TallerBD_Alquiler";
    private static String dbuser = "topicos";
    private static String dbpass = "topicosprogramacion";

    public static void Connect() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://"+ hostname +":3306/" + dbname, dbuser, dbpass);
                System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        }catch (ClassNotFoundException ex) {
                Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
                Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection(){
        if(conn == null) Connect();
        return conn;
    }
    
    public static void Disconnect() {
        try{
            conn.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package config;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Koneksi {

    private static Connection connection;

    public static Connection getConnection() {

        if (connection == null) {

            try {
                String url = "jdbc:mysql://localhost:3306/db_ewallet";
                String user = "root";
                String password = "";

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

                connection = DriverManager.getConnection(url, user, password);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Koneksi gagal : " + e.getMessage());
            }

        }

        return connection;
        
    }
    
}

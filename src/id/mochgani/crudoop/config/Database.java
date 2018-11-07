/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.mochgani.crudoop.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MochGani
 */
public class Database {
    private static Connection connection;
    
    public static Connection getConnection(){
        if (connection==null) {
            try {
                String url = "jdbc:mysql://localhost:3306/db_coba";
                String user ="root";
                String pass = "root";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection = (Connection) DriverManager.getConnection(url, user, pass);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);     
            }
        }
        return connection;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package pfeapplication;*/
package pfeapplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mounir
 */
public class Connexion {
   Connection connection;
 public void loadDriver() throws RuntimeException {
    try {
       
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//Connection conn = DriverManager.getConnection(url);
    } catch (Exception e) {
        throw new RuntimeException("Driver no charger!");
    }
}
public Connection connect() throws ClassNotFoundException, SQLException
 

	{
		try {
                    //Class.forName("com.mysql.jdbc.Driver");
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                     connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=hopital;integratedSecurity=true");
        //connection.setAutoCommit(false);
    } catch (   ClassNotFoundException | SQLException e) {
        throw new RuntimeException("Erreur de connexion!");
    }
return connection;	
	}
	
}
 
 

 



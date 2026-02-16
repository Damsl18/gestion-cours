/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author DAVID
 */
public class connexion {
    private static final String URL ="jdbc:mysql://localhost:3306/cours_en_ligne";
    private static final String USER ="root";
    private static final String PASSWORD ="";
    
    private static Connection connexion = null;
    public static Connection getConnection(){
        if(connexion == null){
            try {
                connexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Success");
            } catch (Exception e) {
                System.out.println("Sorry");
            }
        }
        return connexion;
    }

    
}

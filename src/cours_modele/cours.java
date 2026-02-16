/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours_modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static mysql.connexion.getConnection;

/**
 *
 * @author DAVID
 */
public class cours {
    
    public static void insertData(String titulaire, String nom, String duree,String description){
          String sql = "INSERT INTO cours (titulaire,nom_cours,duree, description) VALUES (?, ?, ?, ?)" ;
          try(PreparedStatement ps=getConnection().prepareStatement(sql)) {
              ps.setString(1, titulaire);
              ps.setString(2, nom);
              ps.setString(3, duree);
              ps.setString(3, description);
              ps.executeUpdate();             
        } catch (Exception e) {
              System.err.println(""+e.getMessage());
            
        }
    }
    public static void readAll() {
        String sql = "SELECT * FROM cours";
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Titulaire: " + rs.getInt("titulaire") + 
                                   " | Nom: " + rs.getString("nom_cours")+
                        " | duree: " + rs.getString("duree") + 
                        " | description: " + rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update(int id, String nouveauNom) {
        String sql = "UPDATE t_etudiant SET nom = ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, nouveauNom);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Etudiant mis à jour !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
}

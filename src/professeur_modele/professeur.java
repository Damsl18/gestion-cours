/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professeur_modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static mysql.connexion.getConnection;

/**
 *
 * @author DAVID
 */
public class professeur {
    public static void insertData(String postnom, String prenom, String tel, String password, String nom, String duree,String description){
          String sql = "INSERT INTO utilisateurs (nom_user, postnom_user, prenom_user, tel_user, password) VALUES (?, ?, ?, ?, ?)" ;
          try(PreparedStatement ps=getConnection().prepareStatement(sql)) {
              ps.setString(1, nom);
              ps.setString(2, postnom);
              ps.setString(3, prenom);
              ps.setString(4, tel);
              ps.setString(5, password);
              ps.executeUpdate();             
        } catch (Exception e) {
              System.err.println(""+e.getMessage());
            
        }
    }
    public static void readAll() {
        String sql = "SELECT * FROM utilisateurs";
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Nom: " + rs.getInt("nom_user") + 
                                   " | Postnom: " + rs.getString("postnom_user")+
                        " | prenom: " + rs.getString("prenom_user") + 
                        " | tel: " + rs.getString("tel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void readById(int id){
        String sql =  "SELECT * FROM utilisateurs WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("ID: " + rs.getInt("id") + 
                    " | Nom: " + rs.getString("nom_user") + 
                    " | postnom: " + rs.getString("postnom_user") + 
                    " | prenom: " + rs.getString("prenom_user"));
        } catch (Exception e) {
            System.out.println("Erreur, id non trouvé");
            e.printStackTrace();
        }
    }
    public static void update(int id, String nom, String postnom, String prenom) {
        String sql = "UPDATE cours SET nom_user = ?, postnom_user = ?, prenom_user = ?, tel_user = ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, nom );
            pstmt.setString(2, postnom);
            pstmt.setString(3, prenom);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("Etudiant mis à jour !");
        } catch (SQLException e) {
            System.out.println("Erreur, id non trouvé");
            e.printStackTrace();
        }
    }
    public static void delete(int id) {
        String sql = "DELETE FROM utilisateurs WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Etudiant supprimé !");
        } catch (SQLException e) {
            System.out.println("Erreur, id non trouvé");
            e.printStackTrace();
        }
    }
}

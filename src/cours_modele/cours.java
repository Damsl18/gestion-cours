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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
    
    //peut permettre de lister TOUT les cours
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
    public static void remplirTableau(JTable table_toutcours) {
        String sql = "SELECT * FROM cours";
        
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Identifiant");
        table.addColumn("Nom du cours");
        table.addColumn("Titulaire");
        table.addColumn("Duree");
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String identifiant = String.valueOf(rs.getInt("id"));
                String Titulaire = String.valueOf(rs.getInt("titulaire"));
                String Nom = rs.getString("nom_cours");
                String Duree = rs.getString("duree");
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Titulaire: " + rs.getInt("titulaire") + 
                                   " | Nom: " + rs.getString("nom_cours") +
                        " | duree: " + rs.getString("duree") + 
                        " | description: " + rs.getString("description"));
                String[] row = {identifiant, Nom, Titulaire, Duree};
                table.addRow(row);
                
                table_toutcours.setModel(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void readById(int id){
        String sql =  "SELECT * FROM cours WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("ID: " + rs.getInt("id") + 
                    " | titulaire: " + rs.getInt("titulaire") + 
                    " | nom: " + rs.getString("nom_cours") + 
                    " | duree: " + rs.getString("duree") + 
                    " | Description: " + rs.getString("description"));
        } catch (Exception e) {
            System.out.println("Erreur, id non trouvé");
            e.printStackTrace();
        }
    }
    public static void update(int id, String statut) {
        String sql = "UPDATE cours SET statut = ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, statut);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            int test = pstmt.executeUpdate(); 
            if (test>0) {
                System.out.println("Cours mis à jour !");
            } else{
                System.out.println("Erreur, id non trouvé");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void delete(int id) {
        String sql = "DELETE FROM cours WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Cours supprimé !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  
    
    
    
}

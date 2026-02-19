/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours_modele;

import graphic.page_de_cours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static mysql.connexion.getConnection;

/**
 *
 * @author DAVID
 */
public class cours {
    
    public static void insertData(int titulaire, String nom, String duree,String description){
          page_de_cours page = new page_de_cours();
          String sql = "INSERT INTO cours (titulaire, nom_cours, duree, description) VALUES (?, ?, ?, ?)" ;
          try(PreparedStatement ps=getConnection().prepareStatement(sql)) {
              ps.setInt(1, titulaire);
              ps.setString(2, nom);
              ps.setString(3, duree);
              ps.setString(4, description);
              ps.executeUpdate();         
              System.out.println("Success !");
              
             
        } catch (Exception e) {
              System.err.println(""+e.getMessage());
              
            
        }
    }
    
    //peut permettre de lister TOUT les cours
    public static List<String> readAll() {
        List<String> liste = new ArrayList<>();
        String sql = "SELECT * FROM cours";
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                liste.add(String.valueOf(rs.getInt("id")));
                liste.add(String.valueOf(rs.getInt("titulaire")));
                liste.add(rs.getString("nom_cours"));
                liste.add(rs.getString("duree"));
                liste.add(rs.getString("description"));
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
    public static void remplirTableau(JTable table_toutcours) {
        String sql = "SELECT * FROM cours";        
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Identifiant");
        table.addColumn("Nom du cours");
        table.addColumn("id Titulaire");
        table.addColumn("Duree");
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {            
            while (rs.next()) {
                String identifiant = String.valueOf(rs.getInt("id"));
                String Titulaire = String.valueOf(rs.getInt("titulaire"));
                String Nom = rs.getString("nom_cours");
                String Duree = rs.getString("duree");
                
                String[] row = {identifiant, Nom, Titulaire, Duree};
                table.addRow(row);               
                table_toutcours.setModel(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void viderTableau(JTable tableau, int id ){
        DefaultTableModel table = (DefaultTableModel) tableau.getModel();
        table.setRowCount(0);
        try {
            participation_modele.participation.delete(id);
        } catch (Exception e) {
            System.out.println("Echec !");
        }
    }
    public static List<String> readById(int id){
        String sql =  "SELECT * FROM cours WHERE id = ?";
        List<String> liste = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                liste.add(String.valueOf(rs.getInt("id")));
                liste.add(String.valueOf(rs.getInt("titulaire")));
                liste.add(rs.getString("nom_cours"));
                liste.add(rs.getString("duree"));
                liste.add(rs.getString("description"));
                
            }
            
        } catch (Exception e) {
            System.out.println("Erreur, id non trouvé");
            e.printStackTrace();
        }
        return liste;
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

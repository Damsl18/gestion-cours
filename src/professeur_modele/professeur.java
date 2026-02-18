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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static mysql.connexion.getConnection;

/**
 *
 * @author DAVID
 */
public class professeur {
    public static void insertData( String nom, String postnom, String prenom, String tel){
          String sql = "INSERT INTO professeur (nom_prof, postnom_prof, prenom_prof, tel_prof) VALUES (?, ?, ?, ?)" ;
          try(PreparedStatement ps=getConnection().prepareStatement(sql)) {
              ps.setString(1, nom);
              ps.setString(2, postnom);
              ps.setString(3, prenom);
              ps.setString(4, tel);
              ps.executeUpdate();             
        } catch (Exception e) {
              System.err.println("" +e.getMessage());
            
        }
    }
    public static void readAll() {
        String sql = "SELECT * FROM professeur";
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Nom: " + rs.getString("nom_prof") + 
                                   " | Postnom: " + rs.getString("postnom_prof")+
                        " | prenom: " + rs.getString("prenom_prof") + 
                        " | tel: " + rs.getString("tel_prof"));
            }
        } catch (SQLException e) {
            System.err.println("" +e.getMessage());
            e.printStackTrace();
        }
    }
    public static void remplirTableau(JTable tableau_prof) {
        String sql = "SELECT * FROM professeur";
        
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Identifiant");
        table.addColumn("Nom");
        table.addColumn("Postnom");
        table.addColumn("Prenom");
        table.addColumn("Téléphone");
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String identifiant = String.valueOf(rs.getInt("id"));
                String Nom = String.valueOf(rs.getString("nom_prof"));
                String Postnom = rs.getString("postnom_prof");
                String Prenom = rs.getString("prenom_prof");
                String tel = rs.getString("tel_prof");
                String[] row = {identifiant, Nom, Postnom, Prenom, tel};
                table.addRow(row);
                tableau_prof.setModel(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void readById(int id){
        String sql =  "SELECT * FROM professeur WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                System.out.println("ID: " + rs.getInt("id") + 
                    " | Nom: " + rs.getString("nom_prof") + 
                    " | postnom: " + rs.getString("postnom_prof") + 
                    " | prenom: " + rs.getString("prenom_prof"));
            }
            
        } catch (Exception e) {
            System.out.println("Erreur, id non trouvé");
            e.printStackTrace();
        }
    }
    public static void update(int id, String nom, String postnom, String prenom, String tel) {
        String sql = "UPDATE professeur SET nom_prof = ?, postnom_prof = ?, prenom_prof = ?, tel_prof = ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, nom );
            pstmt.setString(2, postnom);
            pstmt.setString(3, prenom);
            pstmt.setString(4, tel);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            int test = pstmt.executeUpdate(); 
            if (test>0) {
                System.out.println("Professeur mis à jour !");
            } else{
                System.out.println("Erreur, id non trouvé");
            }
           
        } catch (SQLException e) {
            System.out.println("Erreur, id non trouvé");
            e.printStackTrace();
        }
    }
    public static void delete(int id) {
        String sql = "DELETE FROM professeur WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Professeur supprimé !");
        } catch (SQLException e) {
            System.out.println("Erreur, id non trouvé");
            e.printStackTrace();
        }
    }
}

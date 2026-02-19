/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package participation_modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static mysql.connexion.getConnection;

/**
 *
 * @author DAVID
 */
public class participation {
    public static void insertData(int id_user, int id_cours){
          String sql = "INSERT INTO participation (etudiant, cours) VALUES (?, ?)" ;
          try(PreparedStatement ps=getConnection().prepareStatement(sql)) {
              ps.setInt(1, id_user);
              ps.setInt(2, id_cours);
              ps.executeUpdate();             
        } catch (Exception e) {
              System.err.println(""+e.getMessage());
            
        }
    }
    public static void readAll() {
        String sql = "SELECT * FROM participation";
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                System.out.println("ID Etudiant: " + rs.getInt("etudiant") + 
                                   " | ID cours: " + rs.getInt("cours"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void remplirTableau(JTable table_toutcours, int etudiant) {
        String sql = "SELECT * FROM participation WHERE etudiant = ?";
        
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Identifiant");
        table.addColumn("Identifiant Etudiant");
        table.addColumn("Identifiant Cours");
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql);
                ){
            pstmt.setInt(1, etudiant);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String identifiant = String.valueOf(rs.getInt("id"));
                String Etudiant = String.valueOf(rs.getInt("etudiant"));
                String cours = String.valueOf(rs.getInt("cours"));
                String[] row = {identifiant, Etudiant, cours};
                table.addRow(row);
                
                table_toutcours.setModel(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void remplirTableauTout(JTable table_toutcours) {
        String sql = "SELECT * FROM participation";
        
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Identifiant");
        table.addColumn("Identifiant Etudiant");
        table.addColumn("Identifiant Cours");
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql);
                ){
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String identifiant = String.valueOf(rs.getInt("id"));
                String Etudiant = String.valueOf(rs.getInt("etudiant"));
                String cours = String.valueOf(rs.getInt("cours"));
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Titulaire: " + rs.getInt("Etudiant"));
                String[] row = {identifiant, Etudiant, cours};
                table.addRow(row);
                
                table_toutcours.setModel(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public static void deleteParticipation(int id) {
        String sql = "DELETE FROM participation WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Participation au Cours supprimé !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        public static void delete(int id) {
            String sql = "DELETE FROM participation WHERE etudiant = ?";
            try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                System.out.println("Participation Annulée !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}

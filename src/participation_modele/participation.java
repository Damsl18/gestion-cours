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
        public static void delete(int id) {
        String sql = "DELETE FROM participation WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Cours supprimé !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

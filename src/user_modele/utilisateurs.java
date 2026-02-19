/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user_modele;


import graphic.page_de_cours;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static mysql.connexion.getConnection;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static mysql.connexion.getConnection;

/**
 *
 * @author DAVID
 */
public class utilisateurs {
    public static void insertData( String nom, String postnom, String prenom, String tel, String password, String promotion){
          String sql = "INSERT INTO utilisateurs (nom_user, postnom_user, prenom_user, tel_user, password_user, promotion) VALUES (?, ?, ?, ?, ?, ?)" ;
          try(PreparedStatement ps=getConnection().prepareStatement(sql)) {
              ps.setString(1, nom);
              ps.setString(2, postnom);
              ps.setString(3, prenom);
              ps.setString(4, tel);
              ps.setString(5, password);
              ps.setString(6, promotion);
              ps.executeUpdate();   
              readByName(nom, postnom, prenom, tel);
              System.out.println("effectué !");
        } catch (Exception e) {
              System.err.println("" +e.getMessage());
            
        }
    }
    public static void readAll() {
        String sql = "SELECT * FROM utilisateurs";
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Nom: " + rs.getString("nom_user") + 
                                   " | Postnom: " + rs.getString("postnom_user")+
                        " | prenom: " + rs.getString("prenom_user") + 
                        " | tel: " + rs.getString("tel_user") + 
                        " | promotion: " + rs.getString("promotion"));
            }
        } catch (SQLException e) {
            System.err.println("" +e.getMessage());
            e.printStackTrace();
        }
    }
    public static void remplirTableau(JTable tableau) {
        String sql = "SELECT * FROM utilisateurs";
        page_de_cours page = new page_de_cours();

        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Identifiant");
        table.addColumn("Nom");
        table.addColumn("Postnom");
        table.addColumn("Prenom");
        table.addColumn("Téléphone");
        table.addColumn("Promotion");
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String identifiant = String.valueOf(rs.getInt("id"));
                String Nom = String.valueOf(rs.getString("nom_user"));
                String Postnom = rs.getString("postnom_user");
                String Prenom = rs.getString("prenom_user");
                String tel = rs.getString("tel_user");
                String promo = rs.getString("promotion");
                String[] row = {identifiant, Nom, Postnom, Prenom, tel, promo};
                table.addRow(row);
                tableau.setModel(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void readById(int id){
        page_de_cours page = new page_de_cours();
        String sql =  "SELECT * FROM utilisateurs WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String identifiant = String.valueOf(id);
                String nom = rs.getString("nom_user");
                String postnom = rs.getString("postnom_user");
                String prenom = rs.getString("prenom_user");
                String tel = rs.getString("tel_user");
                String promotion = rs.getString("promotion");
                System.out.println("ID: " + rs.getInt("id") + 
                    " | Nom: " + rs.getString("nom_user") + 
                    " | postnom: " + rs.getString("postnom_user") + 
                    " | prenom: " + rs.getString("prenom_user") + 
                    " | promotion: READ BY ID COMMENCE ICI" + rs.getString("promotion"));
                
                page.liste.add(identifiant);
                page.liste.add(nom);
                page.liste.add(postnom);
                page.liste.add(prenom);
                page.liste.add(promotion);
                System.out.println(page.liste);
            }
            
        } catch (Exception e) {
            System.out.println("Erreur, id non trouvé");
            e.printStackTrace();
        }
    }
    
    public static void readByName(String nom, String postnom, String prenom, String tel){
        List <String> liste = new ArrayList<>();
        page_de_cours page = new page_de_cours();
        String sql =  "SELECT * FROM utilisateurs WHERE nom_user = ? AND tel_user = ? AND postnom_user = ? AND prenom_user = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
            pstmt.setString(1, nom);
            pstmt.setString(2, tel);
            pstmt.setString(3, postnom);
            pstmt.setString(4, prenom);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                liste.add(String.valueOf(rs.getInt("id")));
                liste.add(rs.getString("nom_user"));
                liste.add(rs.getString("postnom_user"));
                liste.add(rs.getString("prenom_user"));
                liste.add(rs.getString("promotion"));
                liste.add(rs.getString("tel_user"));
                
            }       
        } catch (Exception e) {
            System.out.println("Erreur, id non trouvé");
            e.printStackTrace();
        }
        page.liste = liste;
        System.out.println(liste);
    }
    public static void update(int id, String nom, String postnom, String prenom, String tel) {
        String sql = "UPDATE utilisateurs SET nom_user = ?, postnom_user = ?, prenom_user = ?, tel_user = ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, nom );
            pstmt.setString(2, postnom);
            pstmt.setString(3, prenom);
            pstmt.setString(4, tel);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            int test = pstmt.executeUpdate(); 
            if (test>0) {
                System.out.println("Etudiant mis à jour !");
            } else{
                System.out.println("Erreur, id non trouvé");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean exist(int id, String mdp){
        page_de_cours page = new page_de_cours();
        String sql = "SELECT password_user FROM utilisateurs WHERE id = ? AND password_user = ?";
        String mot = "";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.setString(2, mdp);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                mot = rs.getString("password_user");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !mot.isEmpty();
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

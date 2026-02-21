/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur_courant;

/**
 *
 * @author DAVID
 */
public class utilisateurCourant {
    private String id;
    private String nom;
    private String postnom;
    private String prenom;
    private String promotion;


    public utilisateurCourant(String id, String nom, String postnom, String prenom, String promotion) {
        this.id = id;
        this.nom = nom;
        this.postnom = postnom;
        this.prenom = prenom;
        this.promotion = promotion;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPostnom() {
        return postnom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPromotion() {
        return promotion;
    } 
}

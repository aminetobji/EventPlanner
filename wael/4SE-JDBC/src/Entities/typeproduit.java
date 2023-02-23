/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Administrateur
 */
public class typeproduit {
    private int id_typeproduit ;
            private String nom;
            
            private String details;

    public typeproduit() {
    }

    public typeproduit(int id_typeproduit, String nom, String details) {
        this.id_typeproduit = id_typeproduit;
        this.nom = nom;
        this.details = details;
    }

    public typeproduit(String nom, String details) {
        this.nom = nom;
        this.details = details;
    }

    public int getId_typeproduit() {
        return id_typeproduit;
    }

    public void setId_typeproduit(int id_typeproduit) {
        this.id_typeproduit = id_typeproduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
            
            
}

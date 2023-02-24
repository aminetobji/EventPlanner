/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

/**
 *
 * @author jasse
 */
public class categorieevent {
    private int id_categorie;
    private String nomcategorie;

    public categorieevent(int id_categorie, String nomcategorie) {
        this.id_categorie = id_categorie;
        this.nomcategorie = nomcategorie;
    }

    categorieevent(int i) {
        this.id_categorie = id_categorie;
        
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    @Override
    public String toString() {
        return "categorieevent{" + "id_categorie=" + id_categorie + ", nomcategorie=" + nomcategorie + '}';
    }
    
    
}

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
public class evenement {
    private int id_event;
    private int id_user;
    private int is_categorie;
    private String nom;
    private String description;
    private String ville;
    private String date_event;

    public evenement() {
    }

    public evenement(int id_event, int id_user, int is_categorie, String nom, String description, String ville, String date_event) {
        this.id_event = id_event;
        this.id_user = id_user;
        this.is_categorie = is_categorie;
        this.nom = nom;
        this.description = description;
        this.ville = ville;
        this.date_event = date_event;
    }
    
    evenement(int id_evenement) {
this.id_event=id_event;
    }


    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getIs_categorie() {
        return is_categorie;
    }

    public void setIs_categorie(int is_categorie) {
        this.is_categorie = is_categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDate_event() {
        return date_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }

    @Override
    public String toString() {
        return "evenement{" + "id_event=" + id_event + ", id_user=" + id_user + ", is_categorie=" + is_categorie + ", nom=" + nom + ", description=" + description + ", ville=" + ville + ", date_event=" + date_event + '}';
    }
    
    
}

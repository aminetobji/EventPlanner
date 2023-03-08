/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.entities;

/**
 *
 * @author Amine
 */
public class Admin extends User {

    private String adresseGeo;

    public Admin(String adresseGeo, int id, String nom, String prenom, String adresse, String password, String role, int num_telephone) {
        super(id, nom, prenom, adresse, password, role, num_telephone);
        this.adresseGeo = adresseGeo;
    }

    public Admin(String adresseGeo, String nom, String prenom, String adresse, String password, String role, int num_telephone) {
        super(nom, prenom, adresse, password, role, num_telephone);
        this.adresseGeo = adresseGeo;
    }

    public Admin() {
    }

}

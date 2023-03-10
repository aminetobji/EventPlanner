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
public class Client extends User {

    public Client() {
    }

    public Client(int id, String nom, String prenom, String adresse, String password, String role, int num_telephone, int isActtive) {
        super(id, nom, prenom, adresse, password, role, num_telephone, isActtive);
    }

    public Client(String nom, String prenom, String adresse, String password, String role, int num_telephone) {
        super(nom, prenom, adresse, password, role, num_telephone);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.tests;

import edu.event.entities.User;
import edu.event.services.ServiceUser;
import edu.event.utils.DataSource;
import java.security.Provider;

/**
 *
 * @author Amine
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataSource Ds = DataSource.getIstance();
        ServiceUser Su = new ServiceUser();
        User user1 = new User(
                "amine", "tobji",
                "aaa@ss.com", "baaa",
                "admin", 12321
        ) {
        };
        User user2 = new User(2,
                "tobji", "ghofran",
                "aaa@ss.com", "baaa",
                "admin", 12321
        ) {
        };

        //Su.ajouter(user2);
        //Su.supprimer(7);
        //Su.modifier(user2);
        //System.out.println(Su.getAll());
        // System.out.println(Su.getOneById(0));
    }

}

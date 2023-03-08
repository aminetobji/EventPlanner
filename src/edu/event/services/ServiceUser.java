/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.services;

import edu.event.entities.Admin;
import edu.event.entities.Client;
import edu.event.entities.EventManager;
import edu.event.entities.User;
import edu.event.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amine
 */
public class ServiceUser implements IUser<User> {

    Connection cnx = DataSource.getIstance().getConx();

    @Override
    public void ajouter(User p) {
        try {
            String role = "admin";
            if (p instanceof Client) {
                role = "client";
            } else if (p instanceof EventManager) {
                role = "eventmanager";
            }
            String req = "INSERT INTO `user` ( `nom`, `prenom`, `adresse`, `password`, `num_telephone`, `role`) VALUES ('" + p.getNom() + "', '" + p.getPrenom() + "','" + p.getAdresse() + "','" + p.getPassword() + "','" + p.getNum_telephone() + "','" + role + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(">USER created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id =" + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(">USER Deleated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User p) {
        try {
            String req = " UPDATE `user` SET `nom`='" + p.getNom()
                    + "',`prenom`='" + p.getPrenom()
                    + "',`adresse`='" + p.getAdresse()
                    + "',`password`='" + p.getPassword()
                    + "',`num_telephone`=" + p.getNum_telephone() + ",`role`='" + p.getRole() + "' WHERE id =" + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(">USER Modified !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                User u = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("password"), rs.getString("role"), rs.getInt("num_telephone")) {
                };

                list.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public User getOneById(int id
    ) {
        User p = null;
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                p = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("password"), rs.getString("role"), rs.getInt("num_telephone")) {
                };
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

}

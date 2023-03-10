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
            String req = "INSERT INTO `user` ( `nom`, `prenom`, `adresse`, `password`, `num_telephone`, `role`, `isActive`) VALUES ('" + p.getNom() + "', '" + p.getPrenom() + "','" + p.getAdresse() + "','" + p.getPassword() + "','" + p.getNum_telephone() + "','" + role + "','" + p.getIsActive() + "' )";
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

                User u = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("password"), rs.getString("role"), rs.getInt("num_telephone"), rs.getInt("isActive")) {
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
                p = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("password"), rs.getString("role"), rs.getInt("num_telephone"), rs.getInt("isActive")) {
                };
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

    @Override
    public Boolean VerifierMailPaswd(String psw, String mail) {
        Boolean x = false;
        //  "SELECT * FROM `user` WHERE email like '"+ mail +"'";
        try {
            String req = "SELECT "
                    + "   CASE WHEN EXISTS "
                    + "    (  SELECT * FROM user u WHERE u.adresse = '" + mail + "' AND u.password  = '" + psw + "' ) THEN 'TRUE' "
                    + "    ELSE 'FALSE'"
                    + " END";

            Statement st;
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                x = rs.getBoolean(1);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x;
    }

    @Override
    public int getNbrClientActif() {
        int nb = 0;
        try {
            String req = "select id_user ,count(*) as 'nb' from commande group  by id_user order by id_user";

            Statement st;
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getInt("nb") > 2) {
                    nb++;
                }

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nb;
    }

    @Override
    public int getNbrCLient() {
        int Nb = 0;
        try {
            String req = "select count(*) as 'nb' from user u where u.role like 'client' ";

            Statement st;
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Nb = rs.getInt("nb");

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return Nb;
    }

    public int getNbrCLientNoActif() {

        return getNbrCLient() - getNbrClientActif();

    }

    @Override
    public int getNbrEventManagerActif() {
        int nb = 0;
        try {
            String req = "select id_user ,count(*) as 'nb' from evenement group  by id_user order by id_user";

            Statement st;
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getInt("nb") > 2) {
                    nb++;
                }

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nb;
    }

    @Override
    public int getNbrEventManager() {
        int Nb = 0;
        try {
            String req = "select count(*) as 'nb' from user u where u.role like 'agent' ";

            Statement st;
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Nb = rs.getInt("nb");

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return Nb;
    }

    public int getNbrEventManagerNoActif() {

        return getNbrEventManager() - getNbrEventManagerActif();

    }

    @Override
    public Boolean SiADMIN(int idUser) {
        Boolean x = false;

        try {
            String req = "SELECT "
                    + "   CASE WHEN EXISTS "
                    + "    (  SELECT * FROM user u WHERE u.id= " + idUser + " AND u.role like '%admin%') THEN 'TRUE' "
                    + "    ELSE 'FALSE'"
                    + " END";
            //tester SI c'est Artiste pour peut Participer

            Statement st;
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                x = rs.getBoolean(1);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x;
    }

    @Override
    public Boolean SiEVENTMANAGER(int idUser) {
        Boolean x = false;

        try {
            String req = "SELECT "
                    + "   CASE WHEN EXISTS "
                    + "    (  SELECT * FROM user u WHERE u.id= " + idUser + " AND u.role like '%agent%') THEN 'TRUE' "
                    + "    ELSE 'FALSE'"
                    + " END";
            //tester SI c'est Artiste pour peut Participer

            Statement st;
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                x = rs.getBoolean(1);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x;
    }

    public User Login(String email) {

        User p = null;
        try {
            String req = "Select * from user where adresse='" + email + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                p = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("password"), rs.getString("role"), rs.getInt("num_telephone"), rs.getInt("isActive")) {
                };
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;

    }

    public List<String> getAllEmail() {
        List<String> list = new ArrayList<String>();
        try {
            String req = "Select * from user ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getString(4));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public Boolean SiEmailExiste(String mail) {
        Boolean x = false;

        try {
            String req = "SELECT "
                    + "   CASE WHEN EXISTS "
                    + "    (  SELECT * FROM user u WHERE u.adresse = '" + mail + "' ) THEN 'TRUE' "
                    + "    ELSE 'FALSE'"
                    + " END";

            Statement st;
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                x = rs.getBoolean(1);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return x;
    }

    @Override
    public User getUserByMail(String mail) {

        User s = new User(0, "no name", "no prenom", "no adresse", "no password", "No Role", 000, 0) {
        };
        try {
            String req = "SELECT * FROM `user` WHERE adresse like '" + mail + "'";
            //tester SI c'est Artiste pour peut Participer

            Statement st;
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                // System.out.print("Dkhalet llwhile");
                // Studio s = new Studio(rs.getInt("id_user"), rs.getString("role"),rs.getString("login"),rs.getString("password"),rs.getString("adresse_email"),rs.getString("description"));
                s.setId(rs.getInt("id"));
                s.setNom(rs.getString("nom"));
                s.setPrenom(rs.getString("prenom"));
                s.setAdresse(rs.getString("adresse"));
                s.setPassword(rs.getString("password"));
                s.setNum_telephone(rs.getInt("num_telephone"));
                s.setRole(rs.getString("role"));
                s.setIsActive(rs.getInt("isactive"));
                // System.out.print("\n7atit lmax fi e\n");
                return s;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return s;
    }

}

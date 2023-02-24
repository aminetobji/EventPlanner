/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.entities.evenement;
import tn.edu.esprit.utils.DataSource;

/**
 *
 * @author jasse
 */
public class ServiceEvenement implements Ievenement<evenement>{
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajout(evenement e) {
      //DataSource conn=new DataSource();
    
          try {
            String req = "INSERT INTO evenement(id_event,id_user,nom,description,ville,date_event,id_categorie) VALUES ('" +e.getId_event()+ "' , '" +e.getId_user()+ "' , '" +e.getNom()+ "', '" +e.getDescription()+ "', '" +e.getVille()+ "', '" +e.getDate_event()+ "', '" +e.getIs_categorie()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("evenement ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

      @Override
    public List<evenement> getAll() {
        List<evenement> list = new ArrayList<>();
        try {
            String req = "Select * from evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                evenement C = new evenement(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                list.add(C);
            }
                System.out.println("Notre Ev");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;    
    }

    @Override
    public boolean modifier(evenement e) {
        boolean modif=true;
       try {
           String req = "UPDATE evenement "
                   + "SET id_user=?, "
                   + "nom=?, "
                   + "description=?,"
                   + "ville=?,"	
                   + "date_event=?,"
                   + "id_categorie=?"
                   + "WHERE id_categorie =?";
           PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, e.getId_event());
            ps.setInt(2,e.getId_user());
            ps.setString(3,e.getNom());
            ps.setString(4,e.getDescription());
            ps.setString(5,e.getVille());
            ps.setString(6,e.getDate_event());
            ps.setInt(7,e.getIs_categorie());
            ps.executeUpdate(); 
            System.out.println("Categorie Modifièè  bravo!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            modif=false;
        }
        return modif;    
    }
    
    

  @Override
    public boolean supprimer(evenement e) {
        boolean modif=false;
        try {
            Statement st = cnx.createStatement();
            String req = "DELETE FROM evenement WHERE id_event=?";
               PreparedStatement ps =  cnx.prepareStatement(req);
            ps.setInt(1, e.getId_event());
            ps.executeUpdate();
            System.out.println("event supprimer!");

        } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                         modif=false;

        }
        return modif;
    }
    
}

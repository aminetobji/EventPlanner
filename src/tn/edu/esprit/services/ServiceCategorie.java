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
import tn.edu.esprit.entities.categorieevent;
import tn.edu.esprit.utils.DataSource;

/**
 *
 * @author jasse
 */
public class ServiceCategorie implements Icategorie<categorieevent>{

    Connection cnx = DataSource.getInstance().getCnx();
             @Override
    public void ajout(categorieevent e) {
      //DataSource conn=new DataSource();
    
          try {
            String req = "INSERT INTO categorieevent(id_categorie,nomcategorie) VALUES ('" +e.getId_categorie()+ "' , '" +e.getNomcategorie()+ "'  )";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie   ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

      @Override
    public List<categorieevent> getAll() {
 List<categorieevent> list = new ArrayList<>();
        
        try {
            String req = "Select * from categorieevent";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                categorieevent C = new categorieevent(rs.getInt(1),rs.getString(2));
                list.add(C);
            }
                System.out.println("Notre Ctegorie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;    }

    @Override
    public boolean modifier(categorieevent e) {
  boolean modif=true;
       try {
           String req = "UPDATE categorieevent SET nomcategorie=? WHERE id_categorie =?";
           PreparedStatement ps = cnx.prepareStatement(req);
         //  super.modifier(t);
            ps.setInt(1, e.getId_categorie());
           

           
           ps.setString(2, e.getNomcategorie());
            

            ps.executeUpdate(); 
            System.out.println("Categorie Modifièè  bravo!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            modif=false;
        }
        return modif;    }
    
    

  @Override
    public boolean supprimer(categorieevent e) {
    boolean modif=false;

        try {

            Statement st = cnx.createStatement();
            String req = "DELETE FROM categorieevent WHERE id_categorie=?";
               PreparedStatement ps =  cnx.prepareStatement(req);
            ps.setInt(1, e.getId_categorie());
            
            ps.executeUpdate();
            System.out.println("cagtegtorie supprimer!");

        } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                         modif=false;

        }
                return modif;
    }
    
}

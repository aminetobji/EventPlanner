/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.MyDB;
import Entities.typeproduit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author wael
 */
public class TypeProduitService {
        Connection connexion;   
  public TypeProduitService() {
        connexion = MyDB.getInstance().getConnection();
    }
  
     public void modifiertypeproduit(typeproduit u) throws SQLException {
        String req = "UPDATE typeproduit SET "
                + "nom ='"   +   u.getNom()+"'"
            
                + ", details='"+   u.getDetails()+"' where id_typeproduit  = "+u.getId_typeproduit()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }   
     
         public void Supprimertypeproduit(typeproduit u) throws SQLException {

        String req = "DELETE FROM typeproduit WHERE id_typeproduit =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, u.getId_typeproduit());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
     
    
 public void Ajoutertypeproduit(typeproduit u) throws SQLException {
        String req = "INSERT INTO `typeproduit` (`nom`,`details`) "
                + "VALUES (?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, u.getNom());
    
   ps.setString(2,  u.getDetails());

        ps.executeUpdate();
    }
 
    public List<typeproduit> AfficherAlltypeproduit() throws SQLException {

        List<typeproduit> assu = new ArrayList<>();
        String req = "select * from typeproduit";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            typeproduit u = new typeproduit(rst.getInt("id_typeproduit")
                    , rst.getString("nom")
                    , rst.getString("details")
            );
            assu.add(u);
        }
        return assu;
    }
 

  
  
  
}

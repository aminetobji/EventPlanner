/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.services;

import edu.event.entities.Produit;
import edu.event.utils.DataSource;
import java.security.NoSuchAlgorithmException;
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
public class ProduitService {

    Connection connexion;

    public ProduitService() {
        connexion = DataSource.getIstance().getConx();
    }

    public void modifierProduit(Produit e) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE produit SET "
                + " nom='" + e.getNom() + "'"
                + ", prix='" + e.getPrix() + "'"
                + ", date_expo='" + (java.sql.Date) (Date) e.getDate_expo() + "'"
                + ", date_fin='" + (java.sql.Date) (Date) e.getDate_fin() + "'"
                + ", disponibilite='" + e.getDisponibilite() + "'"
                + ", imgproduit='" + e.getImgproduit() + "'"
                + ", id_user='" + e.getId_user() + "'"
                + ", id_typeproduit='" + e.getId_typeproduit() + "' where id  = " + e.getId() + "";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    public void ajouterProduit(Produit e) throws SQLException {
        String req = "INSERT INTO `produit` (`nom`,`prix`,`date_expo`,`date_fin`,`disponibilite`,`imgproduit`,`id_user`,`id_typeproduit`) "
                + "VALUES (?,?,?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, e.getNom());
        ps.setFloat(2, e.getPrix());
        ps.setDate(3, (java.sql.Date) (Date) e.getDate_expo());
        ps.setDate(4, (java.sql.Date) (Date) e.getDate_fin());
        ps.setString(5, e.getDisponibilite());

        ps.setString(6, e.getImgproduit());
        ps.setInt(7, e.getId_user());
        ps.setInt(8, e.getId_typeproduit());

        ps.executeUpdate();
    }

    // @Override
    public List<Produit> AfficherAllProduit() throws SQLException {
        List<Produit> Produits = new ArrayList<>();
        String req = "select * from produit ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Produit e = new Produit(rst.getInt("id"),
                     rst.getString("nom"),
                     rst.getFloat("prix"),
                     rst.getDate("date_expo"),
                     rst.getDate("date_fin"),
                     rst.getString("disponibilite"),
                     rst.getString("imgproduit"),
                     rst.getInt("id_user"),
                     rst.getInt("id_typeproduit"));
            Produits.add(e);
        }
        return Produits;
    }

    // @Override
    public void SupprimerProduit(Produit e) throws SQLException {

        String req = "DELETE FROM produit WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    //  @Override
    public void supp2(Produit m) throws SQLException {

        String req = "DELETE FROM produit WHERE id =" + m.getId() + "";

        PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();

    }

    public void supprimerProduit(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Produit> listerProduits() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

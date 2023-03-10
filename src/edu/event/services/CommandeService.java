package edu.event.services;

import edu.event.entities.Commande;
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
import java.util.Scanner;

/**
 *
 * @author wael
 */
public class CommandeService {

    Connection connexion;

    public CommandeService() {
        connexion = DataSource.getIstance().getConx();
    }

    // @Override
    public void ajouterCommande(Commande e) throws SQLException {
        String req = "INSERT INTO `commande` (`produit_id`,`prix`,`id_user`,`numero`,`date`) "
                + "VALUES (?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, e.getProduit_id());
        ps.setInt(2, e.getPrix());
        ps.setInt(3, e.getId_user());

        ps.setInt(4, e.getNumero());
        ps.setDate(5, (java.sql.Date) (Date) e.getDate());
        ps.executeUpdate();
    }

    // @Override
    public List<Commande> AfficherAllCommande() throws SQLException {
        List<Commande> Commandes = new ArrayList<>();
        String req = "select * from commande ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Commande e = new Commande(rst.getInt("id"),
                    rst.getInt("produit_id"),
                    rst.getInt("prix"),
                    rst.getInt("id_user"),
                    rst.getInt("numero"),
                    rst.getDate("date"));
            Commandes.add(e);
        }
        return Commandes;
    }

    public List<Commande> AfficherAllCommandeByDate() throws SQLException {
        List<Commande> Commandes = new ArrayList<>();
        String req = "select * from commande order by date ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Commande e = new Commande(rst.getInt("id"),
                    rst.getInt("produit_id"),
                    rst.getInt("prix"),
                    rst.getInt("id_user"),
                    rst.getInt("numero"),
                    rst.getDate("date"));
            Commandes.add(e);
        }
        return Commandes;
    }

    public List<Commande> AfficherAllCommandeByProduct() throws SQLException {
        List<Commande> Commandes = new ArrayList<>();
        String req = "select * from commande order by produit_id ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Commande e = new Commande(rst.getInt("id"),
                    rst.getInt("produit_id"),
                    rst.getInt("prix"),
                    rst.getInt("id_user"),
                    rst.getInt("numero"),
                    rst.getDate("date"));
            Commandes.add(e);
        }
        return Commandes;
    }

    public List<Commande> RecherchCommande(String Nom) throws SQLException {
        List<Commande> Commandes = new ArrayList<>();
        String req = "select  * from  commande c  where c.numero LIKE'" + Nom + "'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Commande e = new Commande(rst.getInt("id"),
                    rst.getInt("produit_id"),
                    rst.getInt("prix"),
                    rst.getInt("id_user"),
                    rst.getInt("numero"),
                    rst.getDate("date"));

            Commandes.add(e);
        }
        return Commandes;

    }

    public void SupprimerCommande(Commande e) throws SQLException {

        String req = "DELETE FROM commande WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void modifierCommande(Commande e) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE commande SET "
                + " produit_id='" + e.getProduit_id() + "'"
                + ", prix='" + e.getPrix() + "'"
                + ", id_user='" + e.getId_user() + "'"
                + ", numero  ='" + e.getNumero() + "'"
                + ", date='" + (java.sql.Date) (Date) e.getDate() + "' where id  = " + e.getId() + "";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    public Produit findProduit(int id) throws SQLException {

        Produit c = new Produit();

        String requete = "select * from produit where id=" + id;

        Statement st = connexion.createStatement();
        ResultSet rst = st.executeQuery(requete);
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

        }
        return c;
    }

    public boolean verifNumero(String s) {
        return s.matches("[0-9]{8}");
    }

    public List<Commande> AfficherAlldepensebyDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

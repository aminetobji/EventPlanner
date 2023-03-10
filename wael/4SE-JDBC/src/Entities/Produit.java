/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author wael
 */
public class Produit {
    private int id ;
  
    private String nom;
      private String imgproduit;
    private float prix;
    
    private Date date_expo;
     private Date date_fin;
        private String disponibilite;
  private int id_user;
  private int id_typeproduit ;

    public Produit() {
    }


    public Produit(int id, String nom, float prix, Date date_expo, Date date_fin, String disponibilite,
            String imgproduit,int id_user , int id_typeproduit) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.date_expo = date_expo;
        this.date_fin = date_fin;
        this.disponibilite = disponibilite;
      
        this.imgproduit = imgproduit;
          this.id_user = id_user;
                 this.id_typeproduit = id_typeproduit;
          
    }
        public Produit( String nom, float prix, Date date_expo, Date date_fin, String disponibilite,
            String imgproduit,int id_user, int id_typeproduit) {
        
        this.nom = nom;
        this.prix = prix;
        this.date_expo = date_expo;
        this.date_fin = date_fin;
        this.disponibilite = disponibilite;
      
        this.imgproduit = imgproduit;
          this.id_user = id_user;
           this.id_typeproduit = id_typeproduit;
    }
    public Produit(int id) {
        this.id = id;
    }

    public Produit(int aInt, float aFloat, java.sql.Date date, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Produit(int aInt, float aFloat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_typeproduit() {
        return id_typeproduit;
    }

    public void setId_typeproduit(int id_typeproduit) {
        this.id_typeproduit = id_typeproduit;
    }



    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    

    public Date getDate_expo() {
        return date_expo;
    }

    public void setDate_expo(Date date_expo) {
        this.date_expo = date_expo;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

  

    public String getImgproduit() {
        return imgproduit;
    }

    public void setImgproduit(String imgproduit) {
        this.imgproduit = imgproduit;
    }

    public String getImgae() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_Produit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getnom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

                    
    
    
    
}

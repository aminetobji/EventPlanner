package edu.event.entities;


public class Category {
    private int id;
    private String nom;
 
    public Category() {}
    
    public Category(String nom) {
        this.nom = nom;
    }
    
    public Category(int id, String nom) {
        this.id = id;
        this.nom = nom;
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

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", nom=" + nom + '}';
    }
}

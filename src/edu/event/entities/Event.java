package edu.event.entities;


public class Event {
    private int id;
    private int userId;
    private int categoryId;
    private String nom;
    private String description;
    private String ville;
    private String dateEvent;

    public Event() {}
    public Event(int userId, String nom, String description, String ville,int categoryId, String dateEvent) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.nom = nom;
        this.description = description;
        this.ville = ville;
        this.dateEvent = dateEvent;
    }
    public Event(int id, int userId, String nom, String description, String ville,int categoryId,String dateEvent) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.nom = nom;
        this.description = description;
        this.ville = ville;
        this.dateEvent = dateEvent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDate_event() {
        return dateEvent;
    }

    public void setDate_event(String date_event) {
        this.dateEvent = date_event;
    }

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", userId=" + userId + ", categoryId=" + categoryId + ", nom=" + nom + ", description=" + description + ", ville=" + ville + ", date_event=" + dateEvent + '}';
    }
    
}

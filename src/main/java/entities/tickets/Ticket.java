/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities.tickets;

import entities.event.Evenement;
import entities.user.User;


public class Ticket {
    private int id;
    private float price;
    private String image;
    private String description;
    private int user_id;
    private int event_id;
    public Ticket() { this.id = -1; }
    public Ticket(int id, float price, String description,String image, int user_id, int event_id) {
        this.id = id;
        this.price = price;
        this.image = image;
        this.description = description;
        this.user_id = user_id;
        this.event_id = event_id;
    }
    
    public Ticket(float price, String description,String image, int user_id, int event_id) {
        this.price = price;
        this.image = image;
        this.description = description;
        this.user_id = user_id;
        this.event_id = event_id;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", price=" + price + ", image=" + image + ", description=" + description + ", user_id=" + user_id + ", event_id=" + event_id + '}';
    }



    
}

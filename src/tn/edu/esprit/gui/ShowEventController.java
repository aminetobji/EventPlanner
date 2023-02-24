package tn.edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ShowEventController implements Initializable {
    @FXML
    private Label id_event;
    
    @FXML
    private Label lblPrenom;

    @FXML
    private Label id_user;
    
    @FXML
    private Label is_categorie;
    
    @FXML
    private Label nom;
    
    @FXML
    private Label description;
    
    @FXML
    private Label ville;
    
    @FXML
    private Label date_event;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void setId_event(String tid_event) {
        id_event.setText(tid_event);
    }

    public void setId_user(String tid_user) {
        id_user.setText( tid_user);
    }

    public void setIs_categorie(String tis_categorie) {
        is_categorie.setText(tis_categorie);
    }

    public void setDescription(String tdescription) {
        description.setText( tdescription);
    }

    public void setVille(String tville) {
        ville .setText(tville);
    }

    public void setDate_event(String tdate_event) {
        date_event.setText(tdate_event);
    }

}

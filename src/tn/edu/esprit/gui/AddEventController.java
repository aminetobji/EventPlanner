package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import tn.edu.esprit.entities.evenement;
import tn.edu.esprit.services.ServiceEvenement;

public class AddEventController implements Initializable {
    @FXML
    private TextField id_event;

    @FXML
    private TextField id_user;
    
    @FXML
    private TextField is_categorie;
    
    @FXML
    private TextField nom;
    
    @FXML
    private TextField description;
    
    @FXML
    private TextField ville;
    
    @FXML
    private TextField date_event;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void ajouterEvent(ActionEvent event) throws IOException, SQLException {
        //controle de saisie
        if (false) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Quelque Chose ne marche pas", ButtonType.OK);
            a.showAndWait();
        } else {
            ServiceEvenement se = new ServiceEvenement();
            evenement e = new evenement(
                Integer.parseInt(id_event.getText()),
                Integer.parseInt(id_event.getText()), 
                Integer.parseInt(is_categorie.getText()),  
                nom.getText(),
                description.getText(), 
                ville.getText(),
                date_event.getText()
                );
            se.ajout(e);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Event added !", ButtonType.OK);
            a.showAndWait();
        }

    }
}

package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import tn.edu.esprit.entities.evenement;
import tn.edu.esprit.services.ServiceEvenement;

public class DeleteEventController implements Initializable {
    @FXML
    private TextField id_event;

    @FXML
    private Button deleteButton;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void supprimerEvent(ActionEvent event) throws IOException, SQLException {
        ServiceEvenement se = new ServiceEvenement();
        evenement e = new evenement();
        e.setId_event(Integer.parseInt(id_event.getText()));
        se.supprimer(e);
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Event added !", ButtonType.OK);
        a.showAndWait();
    }
}

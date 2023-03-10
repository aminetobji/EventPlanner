/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.gui;

import edu.event.entities.User;
import edu.event.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ResetPaswordController implements Initializable {

    static String mail;

    private Stage stage;
    private Stage stage2;
    @FXML
    private Button btnreset;
    @FXML
    private TextField txtResetPass;
    @FXML
    private TextField txtVerResetPass;
    @FXML
    private Button btnRestHome1;
    @FXML
    private Label VerifierRepetation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Voila EMAIL a change :" + mail);
    }

    @FXML
    private void Resetb(ActionEvent event) {

        ServiceUser Cc = new ServiceUser();

        if (txtResetPass.getText().equals(txtVerResetPass.getText())) {
            User userUpdated = Cc.getUserByMail(mail);
            String pass = txtResetPass.getText();
            pass = BCrypt.hashpw(pass, BCrypt.gensalt(13));
            pass = pass.replaceFirst("a", "y");
            userUpdated.setPassword(pass);
            Cc.modifier(userUpdated);
            Alert mail = new Alert(Alert.AlertType.CONFIRMATION);
            Alert succes = new Alert(Alert.AlertType.INFORMATION);
            succes.setTitle("Information Dialog");
            succes.setHeaderText(null);
            succes.setContentText("Password Change !");
            succes.showAndWait();
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("GestionUserFXML.fxml"));
                stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage2.setScene(scene);
                stage2.show();

            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        } else {

            VerifierRepetation.setText("les Deux password sont pas identique *__*");
        }

    }

    @FXML
    private void btnRestHome(ActionEvent event) {

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("GestionUserFXML.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    public static void setMail(String mail) {
        ResetPaswordController.mail = mail;
    }

}

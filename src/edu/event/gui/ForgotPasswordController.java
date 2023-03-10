/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.gui;

import edu.event.entities.User;
import edu.event.services.ServiceUser;
import edu.event.utils.MailModule;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
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

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField emailCode;
    @FXML
    private Button VerifierCode;
    @FXML
    private TextField Codemail;
    @FXML
    private Label CheckMail;

    @FXML
    private Button SendCode1;
    /**
     * Initializes the controller class.
     *
     */
    static int Code;
    Random rand = new Random();
    int min = 100000; // Minimum value for 6-digit integer
    int max = 999999; // Maximum value for 6-digit integer
    //int randomNum = rand.nextInt((max - min) + 1) + min;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void VerifierCode(ActionEvent event) throws IOException {
        ServiceUser Cc = new ServiceUser();
        String getEmail;
        getEmail = emailCode.getText();
        if (Cc.SiEmailExiste(getEmail)) {

            if (Code == Integer.parseInt(Codemail.getText())) {
                User newU = Cc.getUserByMail(getEmail);
                ResetPaswordController RpController = new ResetPaswordController();
                RpController.setMail(getEmail);
                Parent page1 = FXMLLoader.load(getClass().getResource("ResetPasword.fxml"));

                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);

                stage.show();

            } else {
                Alert mail = new Alert(Alert.AlertType.CONFIRMATION);
                Alert succes = new Alert(Alert.AlertType.INFORMATION);
                succes.setTitle("Information Dialog");
                succes.setHeaderText(null);
                succes.setContentText("Incorrect Info !");
                succes.showAndWait();
            }

        } else {
            CheckMail.setText("No Account With this Mail!!");
        }
    }

    @FXML
    private void SendCode1(ActionEvent event) throws Exception {

        ServiceUser Cc = new ServiceUser();
        String getEmail;
        getEmail = emailCode.getText();
        if (Cc.SiEmailExiste(getEmail)) {
            Code = rand.nextInt((max - min) + 1) + min;
            Alert mail = new Alert(Alert.AlertType.CONFIRMATION);

            MailModule.sendMailAmine(getEmail, "Voila Votre Code --" + Code + "--");
            Alert succes = new Alert(Alert.AlertType.INFORMATION);
            succes.setTitle("Information Dialog");
            succes.setHeaderText(null);
            succes.setContentText("Email Envoyée avec succés!");
            succes.showAndWait();

        } else {
            CheckMail.setText("No Account With this Mail!!");
        }

    }

}

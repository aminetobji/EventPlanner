/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class GestionUserFXMLController implements Initializable {

    @FXML
    private Label MenuClose;
    @FXML
    private Label UserSing1;
    @FXML
    private Button btMood;
    @FXML
    private ImageView ImgMood;
    @FXML
    private Button BtLogin;
    @FXML
    private Button btInscrire;
    @FXML
    private GridPane pninscrire;
    @FXML
    private TextField TxtNomInscri;
    @FXML
    private TextField TxtPasswordIscri;
    @FXML
    private TextField TxtAdresseInsci;
    @FXML
    private TextField TxtPrenomInsc;
    @FXML
    private Button participer;
    @FXML
    private TextField TxtNumTelIscri;
    @FXML
    private TextField TxtRoleTraiInscri;
    @FXML
    private Button Inscrire;
    @FXML
    private Label error_nom;
    @FXML
    private Label error_prenom;
    @FXML
    private Label error_adresse;
    @FXML
    private Label error_password;
    @FXML
    private Label error_num;
    @FXML
    private Label error_role;
    @FXML
    private GridPane pnLogin;
    @FXML
    private TextField TxtPasswordIscri1;
    @FXML
    private TextField TxtPasswordIscri11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ChangeMood(ActionEvent event) {
    }

    @FXML
    private void handleClick(ActionEvent event) {
        if (event.getSource() == BtLogin) {
            pnLogin.toFront();
        }
        if (event.getSource() == btInscrire) {
            pninscrire.toFront();
        }

    }

    @FXML
    private void participer(ActionEvent event) {
    }

    @FXML
    private void inscrire(ActionEvent event) {
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class AcceuilleManagerController implements Initializable {

    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Categorie(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("TypeProduitGestion.fxml"));
            Stage myWindow = (Stage) btn.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilleManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void CategorieEvent(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("CategoryDash.fxml"));
            Stage myWindow = (Stage) btn.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilleManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void evenement(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("EventDash.fxml"));
            Stage myWindow = (Stage) btn.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilleManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Logout(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GestionUserFXML.fxml"));
            Stage myWindow = (Stage) btn.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilleManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void gestionProduit(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ProduitGestion.fxml"));
            Stage myWindow = (Stage) btn.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilleManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

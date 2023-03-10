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
 *
 * @author Cardinal
 */
public class StartController implements Initializable{
    @FXML
    private Button start;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start.setOnAction((ActionEvent event) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/pkgfinal/gui/FXML/CategoryDash.fxml"));
                Stage window = (Stage) start.getScene().getWindow();
                Scene scene = new Scene(root);
                scene.getStylesheets().add("style.css");
                window.setTitle("Events");
                window.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(EventDashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
}

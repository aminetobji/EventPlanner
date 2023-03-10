/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.gui;

import edu.event.entities.Category;
import edu.event.services.ServiceCategory;
import edu.event.utils.Data;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Cardinal
 */
public class ModifyCategoryController implements Initializable {

    private ServiceCategory cnx;

    @FXML
    private TextField nom;

    @FXML
    private Label feedback;

    @FXML
    private Label id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cnx = new ServiceCategory();
        Data data = new Data();
        int idc = data.getId();
        if (idc != -1) {
            id.setText(String.valueOf(idc));
            data.deletePassedId();
        }

    }

    @FXML
    public void modifyCategory() {
        String str = nom.getText();
        Category c = new Category(Integer.parseInt(id.getText()), nom.getText());
        System.out.println(c);
        if ("".equals(str)) {
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Blank Value!");
        } else if (str.matches(".*\\d.*")) {
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Digits Not Allowed!");
        } else if (cnx.modify(c)) {
            feedback.setTextFill(Color.color(0, 1, 0));
            feedback.setText("Updated Successfully");
        } else {
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Problem While Updating Value");
        }
    }

    @FXML
    private void Retour(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AcceuilleManager.fxml"));
            Stage myWindow = (Stage) feedback.getScene().getWindow();
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

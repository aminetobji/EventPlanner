/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.gui;

import edu.event.entities.Event;
import edu.event.services.ServiceEvent;
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
public class ModifyEventController implements Initializable {

    private ServiceEvent cnx;

    @FXML
    private TextField userId;
    @FXML
    private TextField categoryId;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField ville;
    @FXML
    private TextField dateEvent;

    @FXML
    private Label feedback;

    @FXML
    private Label id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cnx = new ServiceEvent();
        Data data = new Data();
        int idc = data.getId();
        if (idc != -1) {
            id.setText(String.valueOf(idc));
            data.deletePassedId();
        }
    }

    @FXML
    public void modifyEvent() {
        String strUserId = userId.getText();
        String strCategoryId = categoryId.getText();
        String strNom = nom.getText();
        String strDescription = description.getText();
        String strVille = ville.getText();
        String strDateEvent = dateEvent.getText();

        if ("".equals(strUserId) || "".equals(strCategoryId) || "".equals(strNom) || "".equals(strDescription) || "".equals(strVille) || "".equals(strDateEvent)) {
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Blank Value!");
        } else if (cnx.modify(new Event(Integer.parseInt(strUserId),
                strNom,
                strDescription,
                strVille,
                Integer.parseInt(strCategoryId),
                strDateEvent)
        )) {
            feedback.setTextFill(Color.color(0, 1, 0));
            feedback.setText("Added Successfully");
        } else {
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Problem While Adding Value");
        }

    }

    @FXML
    private void retour(ActionEvent event) {

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

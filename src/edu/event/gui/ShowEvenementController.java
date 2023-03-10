/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.gui;

import edu.event.entities.Event;
import edu.event.services.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowEvenementController implements Initializable {

    @FXML
    private ListView<Event> listView;

    ObservableList<Event> data;

    public static int idE;

    ServiceEvent ds = new ServiceEvent();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceEvent cs = new ServiceEvent();
        List<Event> all = cs.getAll();
        ObservableList<Event> observableq = FXCollections.observableArrayList(all);
        data = (ObservableList<Event>) observableq;
        listView.setItems(data);
        listView.setCellFactory((ListView<Event> param) -> new ListViewEvenement());

        // TODO
    }

    @FXML
    private void Retour(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AcceuilleManager.fxml"));
            Stage myWindow = (Stage) listView.getScene().getWindow();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.gui;

import edu.event.entities.Event;
import edu.event.services.ServiceEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Cardinal
 */
public class EventDashUserController implements Initializable {

    private ServiceEvent cnx;

    @FXML
    private ListView<Event> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*cnx = new ServiceEvent();
      ObservableList<Event> events = FXCollections.observableArrayList(cnx.getAll());
      list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      list.setItems(events);*/
    }
}

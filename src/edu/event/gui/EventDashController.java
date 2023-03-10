/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.gui;

import edu.event.entities.Event;
import edu.event.entities.User;
import edu.event.services.ServiceEvent;
import edu.event.utils.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Cardinal
 */
public class EventDashController implements Initializable {

    private ServiceEvent cnx;

    @FXML
    private Button categories;
    @FXML
    private TableView<Event> eventTable;
    @FXML
    private TableColumn<Event, Integer> idColumn;
    @FXML
    private TableColumn<Event, Integer> userIdColumn;
    @FXML
    private TableColumn<Event, Integer> categoryIdColumn;
    @FXML
    private TableColumn<Event, String> nomColumn;
    @FXML
    private TableColumn<Event, String> descriptionColumn;
    @FXML
    private TableColumn<Event, String> villeColumn;
    @FXML
    private TableColumn<Event, String> dateEventColumn;

    @FXML
    private Button add;
    @FXML
    private Button modify;
    @FXML
    private Button refresh;
    @FXML
    private Button rate;
    @FXML
    private TextField rechercher;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
        categories.setOnAction((ActionEvent event) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("CategoryDash.fxml"));
                Stage window = (Stage) categories.getScene().getWindow();
                Scene scene = new Scene(root);
                scene.getStylesheets().add("style.css");
                window.setTitle("Application");
                window.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(CategoryDashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add.setOnAction((ActionEvent event) -> {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AddEvent.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add("style.css");
                stage.setTitle("Add Event");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(EventDashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        modify.setOnAction((ActionEvent event) -> {
            try {
                Event selected = (Event) eventTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    Data data = new Data(selected.getId());
                    data.passId();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("ModifyEvent.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("style.css");
                    stage.setTitle("Modify Event");
                    stage.setScene(scene);
                    stage.show();
                }

            } catch (IOException ex) {
                Logger.getLogger(EventDashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void refreshTable() {
        List<Event> all = cnx.getAll();
        ObservableList<Event> observable = FXCollections.observableArrayList(all);
        eventTable.setItems(observable);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        categoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        villeColumn.setCellValueFactory(new PropertyValueFactory<>("ville"));
        dateEventColumn.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));
    }

    @FXML
    public void deleteSelected() {
        Event selected = (Event) eventTable.getSelectionModel().getSelectedItem();
        cnx.delete(selected);
        refreshTable();
    }

    public void addWindow() {
        Event selected = (Event) eventTable.getSelectionModel().getSelectedItem();
        cnx.delete(selected);
        refreshTable();
    }

    @FXML
    public void refreshFromScene() {
        refreshTable();
    }

    @FXML
    private void retour(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AcceuilleManager.fxml"));
            Stage myWindow = (Stage) add.getScene().getWindow();
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
    private void rechercher(ActionEvent event) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        categoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        villeColumn.setCellValueFactory(new PropertyValueFactory<>("ville"));
        dateEventColumn.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));

        List<Event> all = cnx.getAll();
        ObservableList<Event> observableq = FXCollections.observableArrayList(all);
        //tableview.setItems(observablelist);
        FilteredList<Event> filtredData = new FilteredList<>(observableq, b -> true);
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            Predicate<? super Event> Reservation;
            filtredData.setPredicate((Event e) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (e.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (e.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else {
                    return false;
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Event> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(eventTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        eventTable.setItems(sortedData);

    }

}

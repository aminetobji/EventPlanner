/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgfinal.gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pkgfinal.entities.Event;
import pkgfinal.service.ServiceEvent;
import pkgfinal.util.Data;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
        categories.setOnAction((ActionEvent event) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/pkgfinal/gui/FXML/CategoryDash.fxml"));
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
                Parent root = FXMLLoader.load(getClass().getResource("/pkgfinal/gui/FXML/AddEvent.fxml"));
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
                if (selected!=null){
                    Data data = new Data(selected.getId());
                    data.passId();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/pkgfinal/gui/FXML/ModifyEvent.fxml"));
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
    
    public void refreshTable(){
        this.cnx = new ServiceEvent();
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
    public void deleteSelected(){
        Event selected = (Event) eventTable.getSelectionModel().getSelectedItem();
        cnx.delete(selected);
        refreshTable();
    }
    
    @FXML
    public void addWindow(){
        Event selected = (Event) eventTable.getSelectionModel().getSelectedItem();
        cnx.delete(selected);
        refreshTable();
    }
   
    @FXML
    public void refreshFromScene(){
        refreshTable();
    }
    
}

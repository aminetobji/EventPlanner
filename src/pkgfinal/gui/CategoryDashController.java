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
import pkgfinal.entities.Category;
import pkgfinal.service.ServiceCategory;
import pkgfinal.util.Data;

/**
 *
 * @author Cardinal
 */
public class CategoryDashController implements Initializable {
    
    private ServiceCategory cnx;
    
    @FXML
    private Button events;
    @FXML
    private TableView<Category> categoryTable;
    @FXML
    private TableColumn<Category, Integer> idColumn;
    @FXML
    private TableColumn<Category, String> nomColumn;
    @FXML
    private Button add;
    @FXML
    private Button modify;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
        events.setOnAction((ActionEvent event) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/pkgfinal/gui/FXML/EventDash.fxml"));
                Stage window = (Stage) events.getScene().getWindow();
                Scene scene = new Scene(root);
                scene.getStylesheets().add("style.css");
		window.setTitle("Application");
                window.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(EventDashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add.setOnAction((ActionEvent event) -> {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/pkgfinal/gui/AddCategory.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add("style.css");
                stage.setTitle("Add Category");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(EventDashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        modify.setOnAction((ActionEvent event) -> {
            try {
                Category selected = (Category) categoryTable.getSelectionModel().getSelectedItem();
                if (selected!=null){
                     Data data = new Data(selected.getId());
                    data.passId();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/pkgfinal/gui/ModifyCategory.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("style.css");
                    stage.setTitle("Modify Category");
                    stage.setScene(scene);
                    stage.show();
                }
               
            } catch (IOException ex) {
                Logger.getLogger(EventDashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    public void refreshTable(){
        this.cnx = new ServiceCategory();
        List<Category> all = cnx.getAll();
        ObservableList<Category> observable = FXCollections.observableArrayList(all);
        categoryTable.setItems(observable);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }
    
    @FXML
    public void deleteSelected(){
        Category selected = (Category) categoryTable.getSelectionModel().getSelectedItem();
        cnx.delete(selected);
        refreshTable();
    }
    
    @FXML
    public void addWindow(){
        Category selected = (Category) categoryTable.getSelectionModel().getSelectedItem();
        cnx.delete(selected);
        refreshTable();
    }
   
    @FXML
    public void refreshFromScene(){
        refreshTable();
    }
    
}


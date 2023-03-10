/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.typeproduit;
import Services.TypeProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class TypeProduitGestionController implements Initializable {
 public ObservableList<typeproduit> list;
 private Label label;
    @FXML
    private Button supp;
    @FXML
    private Button modif;
    @FXML
    private Button Ajouter;
    @FXML
    private Label imgpathttt;
    @FXML
    private Label labelid;
    @FXML
    private TextField inputRech;
    @FXML
    private Hyperlink Retour;
    @FXML
    private TableView<typeproduit> tableview;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> details;
    @FXML
    private TextField inputnom;
    @FXML
    private Button confirmer;
    @FXML
    private TextField inputdetails;
    @FXML
    private Hyperlink Produits;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypeProduitService pss = new TypeProduitService();
        ArrayList<typeproduit> c = new ArrayList<>();
  
        try {
            c = (ArrayList<typeproduit>) pss.AfficherAlltypeproduit();
        } catch (SQLException ex) {
        }
   
        
        ObservableList<typeproduit> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        
           
        
        
 nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
 details.setCellValueFactory(new PropertyValueFactory<>("details"));
   
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAlltypeproduit()
            );        
        
        
   FilteredList<typeproduit> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super typeproduit>) typeproduits -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (typeproduits.getNom().toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<typeproduit> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    

    @FXML
    private void supp(ActionEvent event) throws SQLException {
     if (event.getSource() == supp) {
            typeproduit rec = new typeproduit();

            rec.setId_typeproduit(tableview.getSelectionModel().getSelectedItem().getId_typeproduit());
            TypeProduitService cs = new TypeProduitService();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete");
      alert.setHeaderText("Are you sure want to delete this typeproduit");
      alert.setContentText(" ");

      // option != null.
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
           cs.Supprimertypeproduit(rec);
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Supprimer avec succés");
            tray.setMessage("Supprimer avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
             resetTableData();
       
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
          
           
         
            
            
            resetTableData();

        }
        
    }
  public void resetTableData() throws SQLDataException, SQLException {
        TypeProduitService cs = new TypeProduitService();
        List<typeproduit> listrec = new ArrayList<>();
        listrec = cs.AfficherAlltypeproduit();
        ObservableList<typeproduit> data = FXCollections.observableArrayList(listrec);
        tableview.setItems(data);
    }

    @FXML
    private void Modif(ActionEvent event) {
    
            inputnom.setText(tableview.getSelectionModel().getSelectedItem().getNom()); 
        labelid.setText( Integer.toString(tableview.getSelectionModel().getSelectedItem().getId_typeproduit())); 
     inputdetails.setText(tableview.getSelectionModel().getSelectedItem().getDetails()); 

        confirmer.setVisible(true);
        
        
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
            TypeProduitService productService = new TypeProduitService();

        if (inputnom.getText().equals("") || inputdetails.getText().equals("")) {
               
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
    
        
        
        else {

            typeproduit ccc = new typeproduit( inputnom.getText(),inputdetails.getText());
                   
           

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
                 productService.Ajoutertypeproduit(ccc);
                         TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Ajouter avec succés");
            tray.setMessage("Ajouter avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
             resetTableData();
     

          
      
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
      

          

        }; 
        
        
           resetTableData();  
        
        
    }

    @FXML
    private void Retour(ActionEvent event) {
        
        
        
    }


    @FXML
    private void confirmer(ActionEvent event) throws SQLException {
             TypeProduitService productService = new TypeProduitService();

        if (inputnom.getText().equals("") || inputdetails.getText().equals("")) {
               
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez saisir tous les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        } 
    

        else {

            typeproduit ccc = new typeproduit( Integer.parseInt(labelid.getText()),inputnom.getText(),inputdetails.getText());

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
                 productService.modifiertypeproduit(ccc);
                         TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Modifié avec succés");
            tray.setMessage("Modifié avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
             resetTableData();
     

          
      
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
      

          

        }; 
        
        
           resetTableData();  
        
        
    }

    @FXML
    private void Produits(ActionEvent event) throws IOException {
                Parent page1 = FXMLLoader.load(getClass().getResource("ProduitGestion.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }
    
}

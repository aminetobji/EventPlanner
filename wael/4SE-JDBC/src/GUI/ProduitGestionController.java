/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.MyDB;
import Entities.Produit;
import Services.ProduitService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class ProduitGestionController implements Initializable {

    @FXML
    private TableView<Produit> tableview;
    @FXML
    private TableColumn<?, ?> type_produit;
    @FXML
    private TableColumn<?, ?> prix_produit;
    @FXML
    private TableColumn<?, ?> date_expo;
    @FXML
    private TableColumn<?, ?> date_fin;
    @FXML
    private TableColumn<?, ?> disponibilite;
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
    private TextField inputtype_produit;
    @FXML
    private Button Confirmer;
    @FXML
    private Button Timage;
    @FXML
    private ImageView imgajoutincours;
 public ObservableList<Produit> list;
    @FXML
    private TextField inputprix_produit;
    private Label label;
    @FXML
    private DatePicker inputdate_expo;
    @FXML
    private ComboBox<String> inputdisponibilite;
    @FXML
    private DatePicker inputdatedispo;
    @FXML
    private Hyperlink Commande;
    @FXML
    private ComboBox<Integer> inputuser;
       Connection connexion;  
    @FXML
    private TableColumn<?, ?> user;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private ComboBox<Integer> inputtype2;
    @FXML
    private Hyperlink GoTypeProduit;
      public ProduitGestionController() {
        connexion = MyDB.getInstance().getConnection();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
            String req = "select * from typeproduit";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                Integer yy = rst.getInt("id_typeproduit");
                inputtype2.getItems().add(yy);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        try {
            String req = "select * from user";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                Integer xx = rst.getInt("id");
                inputuser.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        ProduitService pss = new ProduitService();
        ArrayList<Produit> c = new ArrayList<>();
        try {
            c = (ArrayList<Produit>) pss.AfficherAllProduit();
        } catch (SQLException ex) {
        }
        
        ObservableList<Produit> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        
           
        
         inputdisponibilite.getItems().add("Disponible");
                inputdisponibilite.getItems().add("non disponible");
 type_produit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix_produit.setCellValueFactory(new PropertyValueFactory<>("prix"));
        date_expo.setCellValueFactory(new PropertyValueFactory<>("date_expo"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
         disponibilite.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
   user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
      type.setCellValueFactory(new PropertyValueFactory<>("id_typeproduit"));

            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllProduit()
            );        
        
        
   FilteredList<Produit> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Produit>) Produits -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Produits.getNom().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Produit> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
  public void resetTableData() throws SQLDataException, SQLException {
      ProduitService cs = new ProduitService();
      List<Produit> listevents = new ArrayList<>();
        listevents = cs.AfficherAllProduit();
        ObservableList<Produit> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }   
   
    @FXML
    private void supp(ActionEvent event) throws SQLException {
           if (event.getSource() == supp) {
            Produit e = new Produit();
            e.setId(tableview.getSelectionModel().getSelectedItem().getId());  
          ProduitService cs = new ProduitService();
            cs.supp2(e);
            resetTableData();  
        
        }
        
        
    }

    @FXML
    private void Modif(ActionEvent event) {
        ProduitService ps = new ProduitService();
           

                           inputuser.setValue(  tableview.getSelectionModel().getSelectedItem().getId_user());
                           inputtype2.setValue(  tableview.getSelectionModel().getSelectedItem().getId_typeproduit());

            labelid.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getId()));
         
            inputtype_produit.setText(tableview.getSelectionModel().getSelectedItem().getNom());
            
            inputprix_produit.setText( Float.toString( tableview.getSelectionModel().getSelectedItem().getPrix()));
  inputdisponibilite.setValue(tableview.getSelectionModel().getSelectedItem().getDisponibilite());
           java.sql.Date r;
        r = new java.sql.Date(tableview.getSelectionModel().getSelectedItem().getDate_expo().getTime());
        LocalDate date = r.toLocalDate();
      java.sql.Date r2;
        r = new java.sql.Date(tableview.getSelectionModel().getSelectedItem().getDate_fin().getTime());
        LocalDate date2 = r.toLocalDate();
inputdate_expo.setValue(  date);
      inputdatedispo.setValue(date2);
           Confirmer.setVisible(true);   
        
        
    }

    
    
    
    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
 ProduitService productService = new ProduitService();
         java.util.Date date2
                = java.util.Date.from(this.inputdatedispo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
                 
                   java.util.Date date3
                = java.util.Date.from(this.inputdate_expo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate3 = new java.sql.Date(date2.getTime());

        if (inputtype_produit.getText().equals("")
                || inputprix_produit.getText().equals("") 
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputtype_produit.getText().equals("")
                || inputprix_produit.getText().equals("") 
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
    
        
        
        else {

    Produit c = new Produit(inputtype_produit.getText(),
                   Float.parseFloat(inputprix_produit.getText()),sqlDate2,sqlDate3,             
                  inputdisponibilite.getValue(),  imgpathttt.getText()   ,inputuser.getValue(),inputtype2.getValue());                   
           

   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
                  productService.ajouterProduit(c);
             resetTableData();
     

          
      
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
      

          

        }; 
        
        
           resetTableData();
        
        
        
    }
@FXML
    private void addimgcours(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgajoutincours.setImage(image);
            imgajoutincours.setFitWidth(200);
            imgajoutincours.setFitHeight(200);
            imgajoutincours.scaleXProperty();
            imgajoutincours.scaleYProperty();
            imgajoutincours.setSmooth(true);
            imgajoutincours.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] person_image = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
        imgpathttt.setText(file.getAbsolutePath());
    }
    @FXML
    private void Confirmer(ActionEvent event) throws NoSuchAlgorithmException, SQLException {
       ProduitService productService = new ProduitService();
         java.util.Date date2
                = java.util.Date.from(this.inputdatedispo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
                 
                   java.util.Date date3
                = java.util.Date.from(this.inputdate_expo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate3 = new java.sql.Date(date2.getTime());

        if (inputtype_produit.getText().equals("")
                || inputprix_produit.getText().equals("") 
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputtype_produit.getText().equals("")
                || inputprix_produit.getText().equals("") 
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
    
        
        
        else {  
   
    Produit c = new Produit(Integer.parseInt(labelid.getText()),inputtype_produit.getText(),
                   Float.parseFloat(inputprix_produit.getText()),sqlDate2,sqlDate3,  inputdisponibilite.getValue(), 
            imgpathttt.getText()   ,inputuser.getValue() ,inputtype2.getValue() );   
   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer ");
      alert.setHeaderText("Confirmer");
      alert.setContentText(" ");
      
         Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
       
      } else if (option.get() == ButtonType.OK) {
                  productService.modifierProduit(c);
             resetTableData();
     

          
      
      } else if (option.get() == ButtonType.CANCEL) {
      
      } else {
         this.label.setText("-");
      }
        }; 
        
        
           resetTableData();
        
        
        
        
    }

    private void Front(ActionEvent event) throws IOException {
      
        
        
    }

    @FXML
    private void Commande(ActionEvent event) throws IOException {
             Parent page1 = FXMLLoader.load(getClass().getResource("CommandeGestion.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();        
    }

    @FXML
    private void GoTypeProduit(ActionEvent event) throws IOException {
                Parent page1 = FXMLLoader.load(getClass().getResource("TypeProduitGestion.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();       
    }

  
}

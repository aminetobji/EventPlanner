/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.MyDB;
import Entities.Commande;
import Entities.Produit;
import Services.CommandeService;
import Services.ProduitService;
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
import java.util.Properties;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
public class CommandeGestionController implements Initializable {

      private Label label;
    @FXML
    private TableView<Commande> tableview;
    @FXML
    private TableColumn<?, ?> produit_id;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> id_user;
    @FXML
    private TableColumn<?, ?> numero;
    @FXML
    private TableColumn<?, ?> date;
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
    private TextField inputnumero;
    private Button Confirmer;
    @FXML
    private ComboBox<Integer> inputProduit;
public ObservableList<Commande> list;
  
     Connection connexion;   
    @FXML
    private ComboBox<Integer> inputuser;
    @FXML
    private Button confirmer;
    @FXML
    private Label labelprix;
    @FXML
    private Hyperlink Produits;
    @FXML
    private Button afficherpardate;
    @FXML
    private TextField mail_tf;
    @FXML
    private Button envoyer;
  public CommandeGestionController() {
        connexion = MyDB.getInstance().getConnection();
    }/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String req = "select * from produit";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
                
                Integer xx = rst.getInt("id");
                inputProduit.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         try {
            String req = "select * from user";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
                
                Integer xx = rst.getInt("id");
                inputuser.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        CommandeService pss = new CommandeService();
        ArrayList<Commande> c = new ArrayList<>();
        try {
            c = (ArrayList<Commande>) pss.AfficherAllCommandeByDate();
        } catch (SQLException ex) {
        }
        
        ObservableList<Commande> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        
           
        
        
                
                
 produit_id.setCellValueFactory(new PropertyValueFactory<>("produit_id"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
         numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
  
   
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllCommandeByDate()
            );        
        
  
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
  public void resetTableData() throws SQLDataException, SQLException {
      CommandeService cs = new CommandeService();
      List<Commande> listevents = new ArrayList<>();
        listevents = cs.AfficherAllCommandeByDate();
        ObservableList<Commande> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }   
    @FXML
    private void supp(ActionEvent event) throws SQLException {
         CommandeService cs = new CommandeService();  
        if (event.getSource() == supp) {
            Commande e = new Commande();
            e.setId(tableview.getSelectionModel().getSelectedItem().getId());  
         
            cs.SupprimerCommande(e);
            
                   TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Supprimer avec succés");
            tray.setMessage("Supprimer avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
             resetTableData(); 
        
        }
        
        
    }

    @FXML
    private void Modif(ActionEvent event) {
         CommandeService ps = new CommandeService();
          

 
 
 
        Commande c = new Commande(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getProduit_id(),
               tableview.getSelectionModel().getSelectedItem().getPrix(),
                 tableview.getSelectionModel().getSelectedItem().getId_user(),
                tableview.getSelectionModel().getSelectedItem().getNumero(),
                tableview.getSelectionModel().getSelectedItem().getDate()   );
               
              
                
                
               
        
           
            labelid.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getId()));
         labelprix.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getPrix()));
            inputProduit.setValue(tableview.getSelectionModel().getSelectedItem().getProduit_id());
            
            inputuser.setValue(  tableview.getSelectionModel().getSelectedItem().getId_user());
  inputnumero.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getNumero()));
        
        confirmer.setVisible(true);
        
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
      
         CommandeService productService = new CommandeService();
   Date date = new Date(System.currentTimeMillis());
         
                 java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());
        if (inputnumero.getText().equals("")
                
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        }
        else if  (!inputnumero.getText().matches("\\d{8}")){
        
             Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("le numero doit etre de 8 ");
            a.setHeaderText(null);
            a.showAndWait();
       
        }
       
            else{

            Commande c = new Commande(inputProduit.getValue(), (int) productService.findProduit(inputProduit.getValue()).getPrix(),inputuser.getValue()
                    ,Integer.parseInt(inputnumero.getText())
                    ,sqlDate2);
        try {
            productService.ajouterCommande(c);
                       TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Ajouter avec succés");
            tray.setMessage("Ajouter avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
             resetTableData();
            
        } catch (SQLException ex) {
           
        }
        
        }
        
        
    }
   
    

 

    @FXML
    private void confirmer(ActionEvent event) throws NoSuchAlgorithmException, SQLException {
     
        CommandeService pss = new CommandeService();
 Date date = new Date(System.currentTimeMillis());
         
                 java.sql.Date sqlDate2 = new java.sql.Date(date.getTime());
		//  
    Commande c = new Commande(Integer.parseInt(labelid.getText()),inputProduit.getValue(), (int) pss.findProduit(inputProduit.getValue()).getPrix(),inputuser.getValue()
                    ,Integer.parseInt(inputnumero.getText())
                    ,sqlDate2);
        
        try {
            pss.modifierCommande(c);
                      TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Modifier avec succés");
            tray.setMessage("Modifier avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
             
             resetTableData();
        } catch (SQLException ex) {
           
        } 
        
        
        
    }
    

    @FXML
    private void Produits(ActionEvent event) throws IOException {
                Parent page1 = FXMLLoader.load(getClass().getResource("ProduitGestion.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }
    
   
    
    
    public static void sendMail(String recipient,String Subject,String Text) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "rjeibi.wael@esprit.tn";
        String password = "leuirfakmijtnhbn";
        Session session = Session.getInstance(properties, new Authenticator() {
             @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            
        Message message = prepareMessage(session, myAccountEmail, recipient,Subject,Text);

        javax.mail.Transport.send(message);
        System.out.println("Message sent successfully");
              TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Mail envoyée avec succés");
            tray.setMessage("Mail envoyée avec succés");
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));
    }  
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient,String Subject,String Text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(Subject);
            message.setText(Text);
            return message;
        } catch (MessagingException ex) {
          
        }
        return null;} 
    
    
    @FXML
    

      private void Send_mail(ActionEvent event) throws IOException, MessagingException {
                sendMail(mail_tf.getText(), "Commande confirmée", "Commande confirmée");
    }
   
}

    


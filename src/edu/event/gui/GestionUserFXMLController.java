/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.gui;

import edu.event.entities.Admin;
import edu.event.entities.Client;
import edu.event.entities.EventManager;
import edu.event.entities.User;
import edu.event.services.ServiceUser;
import edu.event.utils.Sessions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class GestionUserFXMLController implements Initializable {

    static int IdOfUser;

    @FXML
    private Label MenuClose;
    @FXML
    private Label UserSing1;
    @FXML
    private Button btMood;
    @FXML
    private ImageView ImgMood;
    @FXML
    private Button BtLogin;
    @FXML
    private Button btInscrire;
    @FXML
    private GridPane pninscrire;
    @FXML
    private TextField TxtNomInscri;
    @FXML
    private TextField TxtPasswordIscri;
    @FXML
    private TextField TxtAdresseInsci;
    @FXML
    private TextField TxtPrenomInsc;
    @FXML
    private Button participer;
    @FXML
    private TextField TxtNumTelIscri;
    @FXML
    private Button Inscrire;
    @FXML
    private Label error_nom;
    @FXML
    private Label error_prenom;
    @FXML
    private Label error_adresse;
    @FXML
    private Label error_password;
    @FXML
    private Label error_num;
    @FXML
    private Label error_role;
    @FXML
    private GridPane pnLogin;
    @FXML
    private TextField email;
    @FXML
    private TextField psw;
    @FXML
    private Hyperlink ForgotPassword;
    public Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ChangeMood(ActionEvent event) {
    }

    @FXML
    private void handleClick(ActionEvent event) {
        if (event.getSource() == BtLogin) {
            pnLogin.toFront();
        }
        if (event.getSource() == btInscrire) {
            pninscrire.toFront();
        }

    }

    @FXML
    private void participer(ActionEvent event) {
    }

    @FXML
    private void inscrire(ActionEvent event) {
        ServiceUser Cc = new ServiceUser();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        boolean isNomEmpty = Validation.TextFieldValidations.isTextFieldNoEmpty(TxtNomInscri, error_nom, "Nom est vide");
        boolean isPrenomEmpty = Validation.TextFieldValidations.isTextFieldNoEmpty(TxtPrenomInsc, error_prenom, "Prenom est vide");
        boolean isPasswordEmpty = Validation.TextFieldValidations.isTextFieldNoEmpty(TxtPasswordIscri, error_password, "Password est vide");
        boolean isNumTelEmpty = Validation.TextFieldValidations.isTextFieldNoEmpty(TxtNumTelIscri, error_num, "NumTel est vide");
        boolean isAdresseEmpty = Validation.TextFieldValidations.isTextFieldNoEmpty(TxtAdresseInsci, error_adresse, "NO Adresse Found !!");
        if (!(isNomEmpty && isPrenomEmpty && isPasswordEmpty && isNumTelEmpty && isAdresseEmpty)) {
            alert.setTitle("WARNING !!");
            alert.setHeaderText("Can't add empty field ");
            alert.setContentText("invalid field ");
            alert.showAndWait();
        } else {
            String pass = TxtPasswordIscri.getText();
            pass = BCrypt.hashpw(pass, BCrypt.gensalt(13));
            pass = pass.replaceFirst("a", "y");
            User user = new Client(TxtNomInscri.getText(), TxtPrenomInsc.getText(), TxtAdresseInsci.getText(),
                    pass, "Client", Integer.parseInt(TxtNumTelIscri.getText()));
            Cc.ajouter(user);

        }

    }

    @FXML
    private void Login(ActionEvent event) {

        System.out.println(email.getText());

        if (email.getText().equals("") || psw.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Complete vos cordnner", ButtonType.OK);
            alert.showAndWait();
        } else {

            ServiceUser daoU = new ServiceUser();
            User result = daoU.Login(email.getText());
            System.out.println(result);
            if (result == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR, "vérifier vos cordoe", ButtonType.OK);

                alert.showAndWait();
            } else {
                //       System.out.println(result.getBanned());

                if (BCrypt.checkpw(psw.getText(), result.getPassword().replaceFirst("y", "a"))) {

                    System.out.println("edu.event.gui.GestionUserFXMLController.Login()" + result.getRole().equals("Client"));
                    if (result.getRole().equals("Client")) {
                        Sessions.setLoggedInUser(result);
                        System.out.println(result.getRole());
                        // TODO: Proceed to other page
                        Parent root;
                        IdOfUser = result.getId();
                        try {
                            root = FXMLLoader.load(getClass().getResource("AcceuilleClient.fxml"));
                            Stage myWindow = (Stage) email.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(GestionUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (result.getRole().equals("Admin")) {
                        Sessions.setLoggedInUser(result);
                        // TODO: Proceed to other page
                        Parent root;
                        IdOfUser = 0;
                        try {
                            root = FXMLLoader.load(getClass().getResource("labo.fxml"));
                            Stage myWindow = (Stage) email.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(GestionUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (result.getRole().equals("Agent")) {
                        Sessions.setLoggedInUser(result);
                        System.out.println(result.getRole());
                        // TODO: Proceed to other page
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("AcceuilleManager.fxml"));
                            Stage myWindow = (Stage) email.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("Admin");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(GestionUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (result.getRole().equals("Admin")) {
                        Sessions.setLoggedInUser(result);
                        System.out.println(result.getRole());
                        // TODO: Proceed to other page
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("labo.fxml"));
                            Stage myWindow = (Stage) email.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("Admin");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(GestionUserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {

                    Alert alert22 = new Alert(Alert.AlertType.ERROR, "vérifier vos cordoe", ButtonType.OK);
                    alert22.showAndWait();

                }

            }
        }

    }

    @FXML
    private void ForgotPassword(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.gui;

import edu.event.entities.User;
import edu.event.services.ServiceUser;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class LaboController implements Initializable {

    @FXML
    private Label MenuClose;
    @FXML
    private Label UserSing1;
    @FXML
    private Button btMood;
    @FXML
    private ImageView ImgMood;
    @FXML
    private Button BtConsulterDemande;
    @FXML
    private Button btListUsers;
    @FXML
    private TableView<User> tableListeDemande;
    @FXML
    private TableColumn<User, Integer> tid1;
    @FXML
    private TableColumn<User, String> tnom1;
    @FXML
    private TableColumn<User, String> tprenom;
    @FXML
    private TableColumn<User, String> adresse;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableColumn<User, Integer> tnum_telph;
    @FXML
    private TableColumn<User, String> tRole;
    @FXML
    private Button participer;
    @FXML
    private GridPane pnListDemande;
    @FXML
    private GridPane pnListTraiter;
    @FXML
    private TextField TxtNomTr;
    @FXML
    private TextField TxtPasswordTrai;
    @FXML
    private TextField TxtAdresseTrai;
    @FXML
    private TextField TxtPrenomTr;
    @FXML
    private TextField TxtNumTelTrai;
    @FXML
    private TextField TxtRoleTrai;
    int myIndex;
    int txtiduser;
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
    private TableView<?> tableListUser;
    @FXML
    private TableColumn<?, ?> IdTraiter;
    @FXML
    private TableColumn<?, ?> nomTraiter;
    @FXML
    private TableColumn<?, ?> prenomTraiter;
    @FXML
    private TableColumn<?, ?> AdresseTraiter1;
    @FXML
    private TableColumn<?, ?> passwordTraiter;
    @FXML
    private TableColumn<?, ?> num_telphTraiter;
    @FXML
    private TableColumn<?, ?> RoleTraiter;
    @FXML
    private Button Actualiser;
    @FXML
    private Button SuppUser;
    @FXML
    private Button UpdateUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableMonUser();
    }

    @FXML
    private void handleClick(ActionEvent event) {
        if (event.getSource() == btListUsers) {
            pnListTraiter.toFront();
        }
        if (event.getSource() == BtConsulterDemande) {
            pnListDemande.toFront();
        }
    }

    @FXML
    private void ChangeMood(ActionEvent event) {
    }

    @FXML
    private void participer(ActionEvent event) {
    }

    //Affiche seulement mon Challenge  publier +interface ajouter+modif+supp
    public void tableMonUser() {
        ServiceUser Cc = new ServiceUser();
        ArrayList<User> Userss = new ArrayList<>();

        Userss = (ArrayList<User>) Cc.getAll();

        ObservableList<User> obsl = FXCollections.observableArrayList(Userss);

        tableListeDemande.setItems(obsl);

        tid1.setCellValueFactory(new PropertyValueFactory<>("id"));
        tnom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tnum_telph.setCellValueFactory(new PropertyValueFactory<>("num_telephone"));
        tRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        tableListeDemande.setRowFactory(tv -> {
            TableRow<User> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    int myIndex = tableListeDemande.getSelectionModel().getSelectedIndex();

                    int id = Integer.parseInt(String.valueOf(tableListeDemande.getItems().get(myIndex).getId()));
                    int numTel = Integer.parseInt(String.valueOf(tableListeDemande.getItems().get(myIndex).getNum_telephone()));
                    String num = Integer.toString(numTel);
                    TxtNumTelTrai.setText(num);
                    TxtAdresseTrai.setText(tableListeDemande.getItems().get(myIndex).getAdresse());
                    TxtPrenomTr.setText(tableListeDemande.getItems().get(myIndex).getPrenom());
                    TxtNomTr.setText(tableListeDemande.getItems().get(myIndex).getNom());
                    TxtPasswordTrai.setText(tableListeDemande.getItems().get(myIndex).getPassword());
                    TxtRoleTrai.setText(tableListeDemande.getItems().get(myIndex).getRole());

                }
            });
            return myRow;
        });
    }

    @FXML
    private void SuppUser(ActionEvent event) {
        ServiceUser Cc = new ServiceUser();

        myIndex = tableListeDemande.getSelectionModel().getSelectedIndex();
        txtiduser = Integer.parseInt(String.valueOf(tableListeDemande.getItems().get(myIndex).getId()));

        Cc.supprimer(txtiduser);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Delete");

        alert.setHeaderText("U Want Deleted User ");
        alert.setContentText("SUPPRIMER!");

        alert.showAndWait();
        tableMonUser();
    }

    @FXML
    private void UpdateUser(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        ServiceUser Cc = new ServiceUser();

        String nom, prenom, adresse, password, role;
        int num_telephone;

        myIndex = tableListeDemande.getSelectionModel().getSelectedIndex();
        txtiduser = Integer.parseInt(String.valueOf(tableListeDemande.getItems().get(myIndex).getId()));

        boolean isNomEmpty = Validation.TextFieldValidations.isTextFieldNoEmpty(TxtNomTr, error_nom, "Nom est vide");
        boolean isPrenomEmpty = Validation.TextFieldValidations.isTextFieldNoEmpty(TxtPrenomTr, error_prenom, "Prenom est vide");
        boolean isPasswordEmpty = Validation.TextFieldValidations.isTextFieldNoEmpty(TxtPasswordTrai, error_password, "Password est vide");
        boolean isNumTelEmpty = Validation.TextFieldValidations.isTextFieldNoEmpty(TxtNumTelTrai, error_num, "NumTel est vide");
        boolean isRoleEmpty = Validation.TextFieldValidations.isTextFieldNoEmpty(TxtRoleTrai, error_role, "NO ROLE Found !!");
        boolean isAdresseEmpty = Validation.TextFieldValidations.isTextFieldNoEmpty(TxtAdresseTrai, error_adresse, "NO Adresse Found !!");
        if (!(isNomEmpty && isPrenomEmpty && isPasswordEmpty && isNumTelEmpty && isRoleEmpty && isAdresseEmpty)) {
            alert.setTitle("WARNING !!");
            alert.setHeaderText("Can't be Updated Like that ");
            alert.setContentText("invalid field ");
            alert.showAndWait();
        } else {
            System.out.println("nom =" + TxtNomTr.getText());
            nom = TxtNomTr.getText();
            System.out.println("prenom =" + TxtPrenomTr.getText());
            prenom = TxtPrenomTr.getText();
            password = TxtPasswordTrai.getText();
            adresse = TxtPasswordTrai.getText();
            role = TxtRoleTrai.getText();
            num_telephone = parseInt(TxtNumTelTrai.getText());
            System.out.println("ID =" + txtiduser);
            User u = new User(txtiduser, nom, prenom, adresse, password, role, num_telephone) {
            };
            System.out.println("ID =" + txtiduser);
            Cc.modifier(u);

            alert.setTitle("USER Registationn");

            alert.setHeaderText("U Want the change ");
            alert.setContentText("Updateddd!");

            alert.showAndWait();
            tableMonUser();
        }

    }

    @FXML
    private void Actualiser(ActionEvent event) {
        tableMonUser();
    }

}

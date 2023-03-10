/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgfinal.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import pkgfinal.entities.Category;
import pkgfinal.entities.Event;
import pkgfinal.service.ServiceCategory;
import pkgfinal.service.ServiceEvent;
import pkgfinal.util.Data;

/**
 *
 * @author Cardinal
 */
public class ModifyEventController implements Initializable {

    private ServiceEvent cnx;
    
   @FXML
    private TextField userId;
    @FXML
    private TextField categoryId;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField ville;
    @FXML
    private TextField dateEvent;
    
    @FXML
    private Label feedback;
    
    @FXML
    private Label id;
    
            
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cnx = new ServiceEvent();
        Data data = new Data();
        int idc = data.getId();
        if (idc!=-1){
            id.setText(String.valueOf(idc));
            data.deletePassedId();
        }
    }
    
    
    @FXML
    public void modifyEvent(){
        String strUserId = userId.getText();
        String strCategoryId = categoryId.getText();
        String strNom = nom.getText();
        String strDescription = description.getText();
        String strVille = ville.getText();
        String strDateEvent = dateEvent.getText();
        
        if ("".equals(strUserId) || "".equals(strCategoryId) || "".equals(strNom) || "".equals(strDescription) || "".equals(strVille) || "".equals(strDateEvent) ){
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Blank Value!");
        } else if (cnx.modify(new Event(Integer.parseInt(strUserId),
                strNom,
                strDescription,
                strVille,
                Integer.parseInt(strCategoryId),
                strDateEvent)
                )
            ){
            feedback.setTextFill(Color.color(0, 1, 0));
            feedback.setText("Added Successfully");
        } else {
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Problem While Adding Value");
        } 
    
    }
}

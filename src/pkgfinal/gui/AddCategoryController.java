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
import pkgfinal.service.ServiceCategory;

/**
 *
 * @author Cardinal
 */
public class AddCategoryController implements Initializable {
    private ServiceCategory cnx;
    
    @FXML
    private TextField nom;
    
    @FXML
    private Label feedback;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cnx = new ServiceCategory();
    }
    
    @FXML
    public void addCategory(){
        String str = nom.getText();
        if ("".equals(str)){
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Blank Value!");
        }
        else if (str.matches(".*\\d.*")){
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Digits Not Allowed!");
        }
        else if (cnx.add(new Category(nom.getText()))){
            feedback.setTextFill(Color.color(0, 1, 0));
            feedback.setText("Added Successfully");
        } else {
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Problem While Adding Value");
        } 
    }
    
    
    
   
    
}

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
import pkgfinal.util.Data;

/**
 *
 * @author Cardinal
 */
public class ModifyCategoryController implements Initializable {

    private ServiceCategory cnx;
    
    @FXML
    private TextField nom;
    
    @FXML
    private Label feedback;
    
    @FXML
    private Label id;
    
            
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cnx = new ServiceCategory();
        Data data = new Data();
        int idc = data.getId();
        if (idc!=-1){
            id.setText(String.valueOf(idc));
            data.deletePassedId();
        }
        
    }
    
    
    @FXML
    public void modifyCategory(){
        String str = nom.getText();
        Category c = new Category(Integer.parseInt(id.getText()),nom.getText());
        System.out.println(c);
        if ("".equals(str)){
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Blank Value!");
        }
        else if (str.matches(".*\\d.*")){
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Digits Not Allowed!");
        }
        else if (cnx.modify(c)){
            feedback.setTextFill(Color.color(0, 1, 0));
            feedback.setText("Updated Successfully");
        } else {
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Problem While Updating Value");
        } 
    }
    
}

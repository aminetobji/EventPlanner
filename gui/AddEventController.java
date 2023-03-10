package pkgfinal.gui;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import pkgfinal.entities.Event;
import pkgfinal.service.ServiceEvent;
import pkgfinal.service.ServiceMedia;
import pkgfinal.service.ServiceUser;
import pkgfinal.util.MailModule;

/**
 *
 * @author Cardinal
 */
public class AddEventController implements Initializable {
    private ServiceEvent cnx;
    private ServiceMedia sg;
    
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
    private ImageView country;
    
    @FXML
    private ImageView event;
    
    @FXML 
    private Hyperlink tlink; 
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cnx = new ServiceEvent();

    }
    
    @FXML
    public void addEvent() throws InterruptedException{
        String strUserId = userId.getText();
        String strCategoryId = categoryId.getText();
        String strNom = nom.getText();
        String strDescription = description.getText();
        String strVille = ville.getText();
        String strDateEvent = dateEvent.getText();
        
        if ("".equals(strUserId) || "".equals(strCategoryId) || "".equals(strNom) || "".equals(strDescription) || "".equals(strVille) || "".equals(strDateEvent) ){
            feedback.setTextFill(Color.color(1, 0, 0));
            feedback.setText("Blank Value!");
        } else if (cnx.add(new Event(Integer.parseInt(strUserId),
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
         
        MailModule m = new MailModule();
        ServiceUser su = new ServiceUser();
        List<String> emails = su.getAllEmail();
        for(String email :emails){
            m.send(email);
        }
    }

    @FXML
    public void loadPicture(){
        String strVille = ville.getText();
        String strNom = nom.getText();
        sg = new ServiceMedia(strVille,strNom);
        if (sg.getFlag() && sg.getEventPicture()){
            File file = new File("./media/"+strVille+".png");
            Image image = new Image(file.toURI().toString());
            country.setImage(image);
            file = new File("./media/"+nom+".png");
            image = new Image(file.toURI().toString());
            event.setImage(image);
        } else country.setImage(null);
    }
    
    @FXML
    private void handleHyperlinkClick() {
        try {
            URI uri = new URI("https://www.twitter.com/intent/tweet?text="+
                    "We%20have%20an%20upcoming%20event%20In%20"+
                    ville.getText()+
                    "On " + dateEvent.getText() +
                    "%20Be%20There%20!"
                    );
            Desktop.getDesktop().browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

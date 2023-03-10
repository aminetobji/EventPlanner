/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgfinal.test;

import java.io.IOException;
import java.sql.SQLException;
import pkgfinal.service.ServiceMedia;
import pkgfinal.util.MailModule;


public class DataTest {
    public static void main(String[] args) throws SQLException, IOException{
        ServiceMedia sm = new ServiceMedia("Bizerte","Event");
        sm.getEventPicture();
    }
}

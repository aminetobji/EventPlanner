package pkgfinal.service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pkgfinal.service.IUser;
import pkgfinal.util.DataSource;

public class ServiceUser implements IUser {
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public boolean checkUserById(int id) {
        int data = 0;
        try {
            String req = "Select * from user where id="+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) data+=1;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return data>0;
    }

    @Override
    public List<String> getAllEmail() {
        List<String> list  = new ArrayList<String>();
       try {
            String req = "Select * from user ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) list.add(rs.getString(8));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
}

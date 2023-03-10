package pkgfinal.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pkgfinal.entities.Event;
import pkgfinal.util.DataSource;


public class ServiceEvent implements IEvent{
    Connection cnx = DataSource.getInstance().getCnx();
    
    @Override
    public boolean add(Event e){
      //DataSource conn=new DataSource();
    
          try {
            String req = "INSERT INTO evenement(id_event,id_user,nom,description,ville,date_event,id_categorie) VALUES ('" 
                    + e.getId()
                    + "' , '" + e.getUserId()
                    + "' , '" +e.getNom()
                    + "' , '" +e.getDescription()
                    + "', '" +e.getVille()
                    + "', '" +e.getDate_event()
                    + "', '" +e.getCategoryId()
                    + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Added Event!");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }    
    }

      @Override
        public List<Event> getAll(){
        List<Event> list = new ArrayList<>();
        try {
            String req = "Select * from evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Event C = new Event(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7));
                list.add(C);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;    
    }

    @Override
    public boolean modify(Event e){
       try {
           String req = "UPDATE evenement "
                   + "SET id_user=?, "
                   + "nom=?, "
                   + "description=?,"
                   + "ville=?,"	
                   + "date_event=?,"
                   + "id_categorie=?"
                   + "WHERE id_categorie =?";
           PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.setInt(2,e.getUserId());
            ps.setString(3,e.getNom());
            ps.setString(4,e.getDescription());
            ps.setString(5,e.getVille());
            ps.setString(6,e.getDate_event());
            ps.setInt(7,e.getCategoryId());
            ps.executeUpdate(); 
            System.out.println("Category is Modified!");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false; 
        } 
    }
    
  @Override
    public boolean delete(Event e) {
        try {
            Statement st = cnx.createStatement();
            String req = "DELETE FROM evenement WHERE id_event=?";
               PreparedStatement ps =  cnx.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
            System.out.println("Deleted Event!");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Event getEventById(int id) {
        Event event = null;
        try {
            String req = "Select * from evenement where id="+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                event = new Event(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return event;    
    }
    
}

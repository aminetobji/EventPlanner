package services;


import db.Db;
import entities.tickets.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class TicketService {
    private Connection connection;
    public TicketService() {
        try {
            connection=Db.getInstance().getConnection();
            System.out.println(connection);
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public Ticket rawToObj(ResultSet rs) throws SQLException {
        return new Ticket(
                rs.getInt(1),
                rs.getFloat(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getInt(6));
    }
    public Ticket select(int id) {
    Ticket result = new Ticket();
    String sql="select * from `ticket` where id=?";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1,id);
      ResultSet rs = stmt.executeQuery();
      while(rs.next()) {
        try {
          result=rawToObj(rs);
        } catch(SQLException e) {
          e.printStackTrace();
        }
      }
      rs.close();
    } catch(Exception e) {
      System.err.println(e);
    }
        return result;
    }
    
    public ArrayList<Ticket> select() {
        ArrayList<Ticket> result = new ArrayList();
        
    String sql="select * from `ticket`";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      while(rs.next()) {
        try {
          result.add(rawToObj(rs));
        } catch(SQLException e) {
          e.printStackTrace();
        }
      }
      rs.close();
    } catch(Exception e) {
      System.err.println(e);
    }
        return result;
    }
    public Ticket select(Ticket ticket) {
        return this.select(ticket.getId());
    }
    
    public Ticket insert(Ticket ticket) {
        
    String sql="insert into ticket (description,price, image, user_id, event_id) "+
            "values (?,?,?,?,?)";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1,ticket.getDescription());
      stmt.setFloat(2,ticket.getPrice());
      stmt.setString(3,ticket.getImage());
      stmt.setInt(4,ticket.getUser_id());
      stmt.setInt(5,ticket.getEvent_id());
      stmt.executeUpdate();
      ResultSet rs = stmt.getGeneratedKeys();
      rs.next();
      return this.select(rs.getInt(1));
    } catch(SQLException e) {
      System.err.println(e);
    }
      return new Ticket(); 
    }
    
    
    public void update(Ticket ticket) {
       String sql="update ticket set "+
               "description=?, "+
               "price=?, "+
               "image=?, "+
               "user_id=?, "+
               "event_id=? "+
               "where id=?";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1,ticket.getDescription());
      stmt.setFloat(2,ticket.getPrice());
      stmt.setString(3,ticket.getImage());
      stmt.setInt(4,ticket.getUser_id());
      stmt.setInt(5,ticket.getEvent_id());
      stmt.setInt(6, ticket.getId());
      stmt.executeUpdate();

    } catch(Exception e) {
      System.err.println(e);
    }
    }
    
    public void delete(int id) {
        String sql="delete from ticket where id=?";
    try {
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1,id);
      stmt.executeUpdate();
    } catch(Exception e) {
      System.err.println(e);
    }
    }
    public void delete(Ticket ticket) {
        this.delete(ticket.getId());
    }
}

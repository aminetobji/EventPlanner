/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pkgfinal.entities.Category;
import pkgfinal.util.DataSource;

/**
 *
 * @author jasse
 */
public class ServiceCategory implements ICategory{

    Connection cnx = DataSource.getInstance().getCnx();
    
    @Override
    public boolean add(Category c) {
        try{
            String req = "INSERT INTO categorieevent(nomcategorie) VALUES ('"+c.getNom()+"')"; 
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Added Categery!");
            return true;
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
            
    }

      @Override
    public List<Category> getAll(){
        List<Category> list = new ArrayList<>();
        try {
            String req = "Select * from categorieevent";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Category C = new Category(rs.getInt(1),rs.getString(2));
                list.add(C);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;    
    }

    @Override
    public boolean modify(Category e){
       try {
           String req = "UPDATE categorieevent SET nomcategorie='"+e.getNom()+"' WHERE id_categorie ="+e.getId(); 
           Statement st = cnx.createStatement();
           st.executeUpdate(req);
           System.out.println("Modified Category!");
           return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
@Override
    public boolean delete(Category e){
        try {
            Statement st = cnx.createStatement();
            String req = "DELETE FROM categorieevent WHERE id_categorie=?";
            PreparedStatement ps =  cnx.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
            System.out.println("Category Deleted!");
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Category getById(int id) {
        Category category = null;
        try {
            String req = "Select * from categorieevent where id="+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                category = new Category(rs.getInt(1),rs.getString(2));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return category;
    }
    
}

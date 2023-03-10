package edu.event.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Data {

    protected Connection cnx = DataSource.getIstance().getConx();
    private int id;

    public Data() {
    }

    public Data(int id) {
        this.id = id;
    }

    public int getId() {
        try {
            String req = "Select * from currentid";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    public boolean passId() {
        try {

            String req = "INSERT INTO currentid (id) VALUES ('" + id + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean deletePassedId() {
        try {
            Statement st = cnx.createStatement();
            String req = "DELETE FROM currentid WHERE NOT id = -1";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.event.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Amine
 */
public class DataSource {

    String url = "jdbc:mysql://localhost:3306/eventplanner";
    String user = "root";
    String pwd = "";
    private Connection conx;
    public static DataSource instance;

    private DataSource() {
        try {
            conx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Coonnexion Jawha Bhi !!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DataSource getIstance() {//tnajem taaytelha maghir ma taamel instance llclasse just kan be esm lclasse
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getConx() {
        return conx;
    }

}

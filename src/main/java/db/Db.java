/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Db {
  static final String url = "jdbc:mysql://localhost:3306/eventplanner";
  static final String username = "root";
  static final String password = "";
  private Connection con;
  public static Db db;
  public Db() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con=DriverManager.getConnection(url, username, password);
      System.out.println("Connection Established successfully");
    } catch (SQLException|ClassNotFoundException e) {
      System.err.println(e.getMessage());
    }
  }

  public static Db getInstance() throws Exception {
    if(db == null) {
      db = new Db();
    }
    return db;
  };

  public Connection getConnection() { return this.con; }
}
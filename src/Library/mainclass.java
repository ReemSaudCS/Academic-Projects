/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author End-user
 */
public class mainclass {
  private static String URL="jdbc:mysql://localhost:3306/library";
  private static String username="root";
  private static String password="Reemsaud123_";
  public static Connection con=null;
public static void main(String[] args) {
        // TODO code application logic here

    
    }
public static Connection createConnection(){
    try{
        con=DriverManager.getConnection(URL,username,password);
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null,e.getMessage());
    }
    return con;
}
}
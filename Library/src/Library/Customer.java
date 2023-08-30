/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
/**
 *
 * @author End-user
 */
public class Customer {
     
    Connection con1;
    Connection con2;

    ResultSet rs1;
    Statement st1;
    PreparedStatement add;
    PreparedStatement c;
    PreparedStatement ins;
    ResultSet rs;
    PreparedStatement ps;


    public void newcustomer(JTextField fname,JTextField lname,JTextField email,JTextField number,JTextField untext,JPasswordField ptext,JComboBox combocity,JComboBox combocountry){


       try{
           con1=mainclass.createConnection();
        PreparedStatement add=con1.prepareStatement("INSERT INTO address_table (City , Country) VALUES (?,?)");
        String city = String.valueOf(combocity.getSelectedItem());
        add.setString(1,city );
        String country= String.valueOf(combocountry.getSelectedItem());
        add.setString(2,country);
        add.executeUpdate();
        PreparedStatement c=con1.prepareStatement("SELECT last_insert_id() FROM address_table");
         rs1=c.executeQuery();
         rs1.next();
         int id=Integer.valueOf(rs1.getString(1));
        String addr = String.valueOf(id);
        PreparedStatement ins=con1.prepareStatement("INSERT INTO customer_table (Fname,Lname,Email,Phone,Username,Cust_pass,Address_id) VALUES (?,?,?,?,?,?,?)");
        ins.setString(1,fname.getText());
        ins.setString(2,lname.getText());
        ins.setString(3,email.getText());
        ins.setString(4,number.getText());
        ins.setString(5,untext.getText());
        ins.setString(6,ptext.getText());
        ins.setString(7,addr );
       
        ins.executeUpdate();
     
        JOptionPane.showMessageDialog(null,"welcome  "+fname.getText()+"  to our library,  we hope you enjoy shopping with us, your provided id is:  "+id);
         
    }
    catch(SQLException e){
        JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    }finally{if (add!=null) 
             try{add.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
             if (rs1!=null) 
             try{rs1.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (c!=null) 
             try{c.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (ins!=null) 
             try{ins.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (con1!=null) 
             try{con1.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
       }
       
     }
    public int is_username_exist(String username) {
            
            String query = "SELECT * FROM customer_table WHERE username  = '" + username + "'";
            try {
                con2=mainclass.createConnection();
                ps = con2.prepareStatement(query);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return 1; // it exists
                } else {
                    return 0; // does not exist
            }

            } catch (SQLException ex) {
                                        JOptionPane.showMessageDialog(null , ex);
            } 
            finally{if (rs!=null) 
             try{rs.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
             if (ps!=null) 
             try{ps.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (con2!=null) 
             try{con2.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}}
            return -2;
         
        }
}


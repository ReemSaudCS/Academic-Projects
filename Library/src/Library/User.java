/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import net.proteanit.sql.DbUtils;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;import net.proteanit.sql.DbUtils;

/**
 *
 * @author End-user
 */
public class User {
    private String id;
    private String fname;
    private String lname;
    private String phone;
    private String Email;
    private String username;
    private String pass;
    Connection conn;
    Connection con;
    PreparedStatement st;
    Statement s;
    ResultSet rs;
    PreparedStatement st2;
    ResultSet rs2;
    User(){
        this.id="";
        this.fname="";
        this.lname="";
        this.phone="";
        this.Email="";
        this.username="";
        this.pass="";
    }
    User(String id,String fname,String lname,String phone,String Email,String username,String pass){
        this.id=id;
        this.fname=fname;
        this.lname=lname;
        this.phone=phone;
        this.Email=Email;
        this.username=username;
        this.pass=pass;
    }
    public void setID(String id){
        this.id=id;
    }
    public String getID(){
        return id;
    }
    public void setfname(String fname){
        this.fname=fname;
    }
    public String getfname(){
        return fname;
    }
    public void setlname(String lname){
        this.lname=lname;
    }
    public String getlname(){
        return lname;
    }
    public void setphone(String phone){
        this.phone=phone;
    }
    public String getphone(){
        return phone;
    }
    public void setEmail(String Email){
        this.Email=Email;
    }
    public String getEmail(){
        return Email;
    }
    public void setusername(String username){
        this.username=username;
    }
    public String getusername(){
        return username;
    }
    public void setpass(String pass){
        this.pass=pass;
    }
    public String getpass(){
        return pass;
    }
    
    public void checkCust(String username,String pass,String role,JFrame login){
        try{
        if(role.equals("Customer")){
        String query="select Username from customer_table";
        Boolean found=false;
        con=mainclass.createConnection();
            s=con.createStatement();
            rs=s.executeQuery(query);
            while(rs.next()){
            if(username.equals(rs.getString("Username"))){
            found=true;
            
            String q="select Username,Cust_pass from customer_table where Username='"+username+"'";
            st=con.prepareStatement(q);
            rs2=st.executeQuery();
            while(rs2.next()){
            if(pass.equals(rs2.getString("Cust_pass"))){
                    JOptionPane.showMessageDialog(null,"Welcome Back Customer:"+username);
                    CustomerView v=new CustomerView();
                    v.setusername(username);
                    v.setVisible(true);
                    login.setVisible(false);
            }
            else if(!pass.equals(rs2.getString("Cust_pass"))){
                JOptionPane.showMessageDialog(null,"You've entered wrong information, please try again","Failed to login",JOptionPane.ERROR_MESSAGE);
                }} break;}
            if(!username.equals(rs.getString("Username"))){
                found=false;

            }}
            if(!found){
            JOptionPane.showMessageDialog(null,"You've entered wrong information, please try again","Failed to login",JOptionPane.ERROR_MESSAGE);

            }}}
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }finally{
         if (rs!=null) 
             try{rs.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (s!=null) 
             try{s.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (rs2!=null) 
             try{rs2.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (st!=null) 
             try{st.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (con!=null) 
             try{con.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
   }
       
    
    }}


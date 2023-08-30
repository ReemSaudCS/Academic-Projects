/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author End-user
 */
public class Admin extends User {
    Connection con=mainclass.createConnection();
    Statement st;
    ResultSet rs;
    public static JFileChooser chooser;
    public static File file;
    public static String path;
    Admin(){
        super();
    }
    Admin(String id,String fname,String lname,String phone,String Email,String username,String pass){
        super(id,fname,lname,phone,Email,username,pass);
    }
    public String addCover(JLabel cc){
        chooser=new JFileChooser();
        chooser.showOpenDialog(null);
         file=chooser.getSelectedFile();
        path=file.getAbsolutePath();

        Image im=Toolkit.getDefaultToolkit().createImage(path);
        im=im.getScaledInstance(61, 100, Image.SCALE_SMOOTH);
        ImageIcon i=new ImageIcon(im);
        cc.setIcon(i);
     return path;
    }
    public void addBook(JTextField id,JTextField title,JTextField author,JTextField price,JComboBox type,JTextField section,JTextField quantity,JTextField admin_id,JLabel cc,JTable bookstable,String path){
         try{
        PreparedStatement ins=con.prepareStatement("INSERT INTO Book_table (Book_id,Title,Author,Price,Type_of_book,Section,quantity,Admin_id,cover) VALUES (?,?,?,?,?,?,?,?,?)");
        ins.setInt(1,Integer.valueOf(id.getText()));
        ins.setString(2,title.getText());
        ins.setString(3,author.getText());
        ins.setDouble(4,Double.valueOf(price.getText()));
        ins.setString(5,type.getSelectedItem().toString());
        ins.setString(6,section.getText());
        ins.setDouble(7,Double.valueOf(quantity.getText()));
        ins.setString(8,admin_id.getText());
        ins.setString(9,path);
        ins.executeUpdate();
        JOptionPane.showMessageDialog(null,"Book Added Successfuly");
        fillTable(bookstable);
    }
    catch(SQLException e){
        JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    }
    catch(NumberFormatException e){
        JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    }
    }
    public void deleteBook(JTextField id,JTextField title,JTextField author,JTextField price,JComboBox type,JTextField section,JTextField quantity,JTextField admin_id,JLabel cc,JTable bookstable){
        try{
        int confirming=JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?");
        if(confirming==JOptionPane.YES_OPTION){
            String sql="delete from book_table where Book_id="+id.getText();
            int rows=st.executeUpdate(sql);
            if (rows==1)
                JOptionPane.showMessageDialog(null,"The book has been deleted successfully");
            id.setText("");
            title.setText("");
            author.setText("");
            price.setText("");
            type.setSelectedItem(null);
            section.setText("");
            quantity.setText("");
            cc.setIcon(null);
            fillTable(bookstable);
        }}
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
                }
    }
    public void updateBook(JTextField id,JTextField title,JTextField author,JTextField price,JComboBox type,JTextField section,JTextField quantity,JTextField admin_id,JLabel cc,JTable bookstable,String path){
        try{
            PreparedStatement up;
            if(path==null){
         up=con.prepareStatement("update book_table set Title=?,Author=?,Price=?,Type_of_book=?,Section=?,quantity=?,Admin_id=? where Book_id=?");
            up.setInt(8,Integer.valueOf(id.getText()));
        up.setString(1,title.getText());
        up.setString(2,author.getText());
        up.setDouble(3,Double.valueOf(price.getText()));
        up.setString(4,type.getSelectedItem().toString());
        up.setString(5,section.getText());
        up.setDouble(6,Double.valueOf(quantity.getText()));
        up.setString(7,admin_id.getText());}
            else{
                up=con.prepareStatement("update book_table set Title=?,Author=?,Price=?,Type_of_book=?,Section=?,quantity=?,Admin_id=?,cover=? where Book_id=?");
        up.setInt(9,Integer.valueOf(id.getText()));
        up.setString(1,title.getText());
        up.setString(2,author.getText());
        up.setDouble(3,Double.parseDouble(price.getText()));
        up.setString(4,type.getSelectedItem().toString());
        up.setString(5,section.getText());
        up.setDouble(6,Double.valueOf(quantity.getText()));
        up.setString(7,admin_id.getText());
        up.setString(8,path);
            }
        fillTable(bookstable);
        int updater=up.executeUpdate();
        if(updater>0){
            JOptionPane.showMessageDialog(null, "The book has been updated successfully");
        }
        else{
       JOptionPane.showMessageDialog(null, "the proccess of updating failed");
        }
        fillTable(bookstable);
    }
       catch(SQLException e){
                  JOptionPane.showMessageDialog(null, e.getMessage());
                }
    }
    public void fillTable(JTable bookstable){
        try{
            st=con.createStatement();
            rs=st.executeQuery("select * from book_table");
            bookstable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }}
    public void booksinfo(JTextField id,JTextField title,JTextField author,JTextField price,JComboBox type,JTextField section,JTextField quantity,JTextField admin_id,JLabel cc,JTable bookstable){
        try{
            int row=bookstable.getSelectedRow();
            String clickbid=bookstable.getModel().getValueAt(row,0).toString();    
            String sql="select * from book_table where Book_id='"+clickbid+"'";
            rs=st.executeQuery(sql);
            rs.next();
            id.setText(rs.getString("Book_id"));
            title.setText(rs.getString("Title"));
            author.setText(rs.getString("Author"));
            price.setText(rs.getString("Price"));
            type.setSelectedItem(rs.getString("Type_of_book"));
            section.setText(rs.getString("Section"));
            quantity.setText(rs.getString("quantity"));
            admin_id.setText(rs.getString("Admin_id"));
            ImageIcon i;
            i = new javax.swing.ImageIcon(getClass().getResource(rs.getString("cover")));
            cc.setIcon(i);}
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());}}
    
     public void checkAdmin(String username,String password){
       
        try {
            PreparedStatement s;
            String q="select username,Passwords from admin where username='"+username+"'";
            s=con.prepareStatement(q);
            rs=s.executeQuery();
            while(rs.next()){
                if(password.equals(rs.getString("Passwords"))){
                    JOptionPane.showMessageDialog(null,"Welcome Back Admin:"+username);
                    new AdminView().setVisible(true);
                    new Login().setVisible(false);}
                else if(!password.equals(rs.getString("Passwords"))) {
                JOptionPane.showMessageDialog(null,"You've entered the wrong Password, please try again","Failed to login",JOptionPane.ERROR_MESSAGE);
                }
             
        }}
       
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    }


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
    Connection con;
    Connection con2;
    Connection con3;
    Statement st;
    Statement s1;
    PreparedStatement s;
    PreparedStatement pst;
    PreparedStatement ps;
    ResultSet rs3;

    ResultSet rs;
    PreparedStatement ins;
    PreparedStatement up;
    public static JFileChooser chooser;
    public static File file;
    public static String path;
    public static String id;
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
    public void addBook(JTextField id,JTextField title,JTextField author,JTextField price,JComboBox type,JComboBox section,JTextField quantity,int admin_id,JLabel cc,JTable bookstable,String path){
         try{
             con=mainclass.createConnection();
         ins=con.prepareStatement("INSERT INTO Book_table (Book_id,Title,Author,Price,Type_of_book,Section,quantity,Admin_id,cover) VALUES (?,?,?,?,?,?,?,?,?)");
        ins.setInt(1,Integer.valueOf(id.getText()));
        ins.setString(2,title.getText());
        ins.setString(3,author.getText());
        ins.setDouble(4,Double.valueOf(price.getText()));
        ins.setString(5,type.getSelectedItem().toString());
        ins.setString(6,section.getSelectedItem().toString());
         ins.setDouble(7,Double.valueOf(quantity.getText()));
        ins.setInt(8,admin_id);
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
    }finally{
         if (ins!=null) 
             try{ins.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (con!=null) 
             try{con.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}}
    }
    public void deleteBook(JTextField id,JTextField title,JTextField author,JTextField price,JComboBox type,JComboBox section,JTextField quantity,int admin_id,JLabel cc,JTable bookstable){
        try{
        int confirming=JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?");
        if(confirming==JOptionPane.YES_OPTION){
            String sql="delete from book_table where Book_id="+id.getText();
            con=mainclass.createConnection();
            st=con.createStatement();
            int rows=st.executeUpdate(sql);
            if (rows==1)
                JOptionPane.showMessageDialog(null,"The book has been deleted successfully");
            id.setText("");
            title.setText("");
            author.setText("");
            price.setText("");
            type.setSelectedItem(null);
            section.setSelectedItem(null);
            quantity.setText("");
            cc.setIcon(null);
            fillTable(bookstable);
        }}
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
                }finally{
         if (rs!=null) 
             try{rs.close();
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
    }}
    public void updateBook(JTextField id,JTextField title,JTextField author,JTextField price,JComboBox type,JComboBox section,JTextField quantity,int admin_id,JTable bookstable){
        try{
                con2=mainclass.createConnection();
         up=con2.prepareStatement("update book_table set Title=?,Author=?,Price=?,Type_of_book=?,Section=?,quantity=?,Admin_id=? where Book_id=?");
            up.setInt(8,Integer.valueOf(id.getText()));
        up.setString(1,title.getText());
        up.setString(2,author.getText());
        up.setDouble(3,Double.valueOf(price.getText()));
        up.setString(4,type.getSelectedItem().toString());
        up.setString(5,section.getSelectedItem().toString());
        up.setDouble(6,Double.valueOf(quantity.getText()));
        up.setInt(7,admin_id);
          
        fillTable(bookstable);
        int updater=up.executeUpdate();
        if(updater>0){
            JOptionPane.showMessageDialog(null, "The book has been updated successfully");
        }
        else{
       JOptionPane.showMessageDialog(null, "Book id cannot be updated");
        }
        fillTable(bookstable);
    }
       catch(SQLException e){
                  JOptionPane.showMessageDialog(null, e.getMessage());
                }finally{
         if (up!=null) 
             try{up.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (con!=null) 
             try{con.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
    }}
    public void fillTable(JTable bookstable){
        try{
            con=mainclass.createConnection();
            st=con.createStatement();
            rs=st.executeQuery("select * from book_table");
            bookstable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }finally{
         if (rs!=null) 
             try{rs.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (st!=null) 
             try{st.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (con!=null) 
             try{con.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}}}
    public void booksinfo(JTextField id,JTextField title,JTextField author,JTextField price,JComboBox type,JComboBox section,JTextField quantity,int admin_id,JLabel cc,JTable bookstable){
        try{
            con=mainclass.createConnection();
            int row=bookstable.getSelectedRow();
            String clickbid=bookstable.getModel().getValueAt(row,0).toString();    
            String sql="select * from book_table where Book_id='"+clickbid+"'";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            rs.next();
            id.setText(rs.getString("Book_id"));
            title.setText(rs.getString("Title"));
            author.setText(rs.getString("Author"));
            price.setText(rs.getString("Price"));
            type.setSelectedItem(rs.getString("Type_of_book"));
            section.setSelectedItem(rs.getString("Section"));
            quantity.setText(rs.getString("quantity"));
            ImageIcon i;
            i = new javax.swing.ImageIcon(getClass().getResource(rs.getString("cover")));
            cc.setIcon(i);}
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());}finally{
         if (rs!=null) 
             try{rs.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (st!=null) 
             try{st.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (con!=null) 
             try{con.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}}}
    
     public void checkAdmin(String username,String password){
       
        try {
            con=mainclass.createConnection();
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
        }finally{
         if (rs!=null) 
             try{rs.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (s!=null) 
             try{s.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (con!=null) 
             try{con.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
    }
    }public void checkAdmin(String username,String pass,String role,JFrame login){
    try{
        Connection con1;
        Statement st1;
        ResultSet rst;
        PreparedStatement pst1;
        ResultSet rst2;
         String URL="jdbc:mysql://localhost:3306/library";
         String usernamee="root";
         String password="Reemsaud123_";
                     con1=DriverManager.getConnection(URL,usernamee,password);

        if(role.equals("Admin")){
        String query="select Username from admin_table";
                    String q="select Username,Admin_pass from admin_table where Username='"+username+"'";

        Boolean found=false;
            con1=DriverManager.getConnection(URL,usernamee,password);
            st1=con1.createStatement();
            rst=st1.executeQuery(query);
            pst1=con1.prepareStatement(q);
            rst2=pst1.executeQuery();
            while(rst.next()){
            if(username.equals(rst.getString("Username"))){
            found=true;
            
            pst1=con1.prepareStatement(q);
            rst2=pst1.executeQuery();
            while(rst2.next()){
            if(pass.equals(rst2.getString("Admin_pass"))){
                    JOptionPane.showMessageDialog(null,"Welcome Back Admin:"+username);
                    new AdminView().setVisible(true);
                    login.setVisible(false);
                                
            }
            else if(!pass.equals(rst2.getString("Admin_pass"))){
                JOptionPane.showMessageDialog(null,"You've entered wrong information, please try again","Failed to login",JOptionPane.ERROR_MESSAGE);
                                 

                }} break;
            }
            if(!username.equals(rst.getString("Username"))){
                found=false;

            }}
            if(!found){
            JOptionPane.showMessageDialog(null,"You've entered wrong information, please try again","Failed to login",JOptionPane.ERROR_MESSAGE);

            }

rst2.close();
                                pst1.close();
                    rst.close();
                    st1.close();
                    con1.close();
        }}catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }}
    public int ifbookexist(String id) {
            
            String query = "SELECT * FROM book_table WHERE Book_id  = '" + id + "'";
            try {
                con3=mainclass.createConnection();
                ps = con3.prepareStatement(query);
                rs3 = ps.executeQuery();
                if (rs3.next()) {
                    return 1; // it exists
                } else {
                    return 0; // does not exist
            }

            } catch (SQLException ex) {
                                        JOptionPane.showMessageDialog(null , ex);
            } 
            finally{if (rs3!=null) 
             try{rs3.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
             if (ps!=null) 
             try{ps.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (con3!=null) 
             try{con3.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}}
            return -2;
         
        }
}


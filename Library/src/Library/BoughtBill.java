/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Library;


import java.awt.Component;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
/**
 *
 * @author End-user
 */

public class BoughtBill extends javax.swing.JFrame {
    private int Bill_no;
    private String AddressLine, Type;
    private Date date;
    private String book;
    Connection con;
    Connection con1;
    Statement st;
    Connection con4;
    Statement st4;
        Statement st5;
    ResultSet rs4;
    ResultSet rs5;

    ResultSet rs;
    Statement st2;
    ResultSet rs2;
    PreparedStatement pst;
    PreparedStatement pst3;
    CustomerView cv=new CustomerView();

    /**
     * Creates new form Bill
     */


    public BoughtBill() {
        initComponents();
       // updateCompo();
             
    }
    
     DefaultListModel mod=new DefaultListModel();
   public  void BoughtBill(int Bill_no) {
       try{
           finala.setVisible(false);
           dis.setVisible(false);
                pp.removeAll();
                pp.revalidate();
                pp.repaint();
                pp.setLayout(new java.awt.FlowLayout());
           StringBuilder builder=new StringBuilder();
           con1=mainclass.createConnection();
       st=con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       String query="Select * from billcontents where Bill_no='"+Bill_no+"'";
              rs=st.executeQuery(query);
              rs.last();
              int rows=rs.getRow();
              rs.beforeFirst();
              String[]books=new String[rows];
              ArrayList<String> b=new ArrayList<String>();
       while(rs.next()){
//                         builder.append(rs.getInt("Book_id"));

            for(int i=0;i<1;i++){
                
           books[i]=(rs.getString("Book_id"));
               System.out.println(books[i]);
               b.add(books[i]);
                }
//               pp.add(books[i]);
            }
           
         // bor.setText(String.valueOf(builder));
       
       for(int i=0;i<b.size();i++){
                         booktitles(b.get(i));
                         decreaseq(b.get(i));
       }
         st2=con1.createStatement();
       rs2=st2.executeQuery("Select * from buy_table where Bill_no='"+Bill_no+"'");
              while(rs2.next()){

              lbl_Amount.setText("Amount:"+rs2.getString("Price"));

   lbl_Billno.setText("#"+String.valueOf(Bill_no));
   lbldate.setText(rs2.getString("purchase_date"));}}
       catch(SQLException e){
        JOptionPane.showMessageDialog(null,e.getMessage());
       }finally{
         if (rs!=null) 
             try{rs.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (st!=null) 
             try{st.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (rs2!=null) 
             try{rs2.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (st2!=null) 
             try{st2.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (con!=null) 
             try{con.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
}}
   public  void BoughtBill(int Bill_no,Double amount,Double discount) {
       try{
                pp.removeAll();
                pp.revalidate();
                pp.repaint();
                pp.setLayout(new java.awt.FlowLayout());
           StringBuilder builder=new StringBuilder();
           con4=mainclass.createConnection();
       st4=con4.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       String query="Select * from billcontents where Bill_no='"+Bill_no+"'";
              rs4=st4.executeQuery(query);
              rs4.last();
              int rows=rs4.getRow();
              rs4.beforeFirst();
              String[]books=new String[rows];
              ArrayList<String> b=new ArrayList<String>();
       while(rs4.next()){
//                         builder.append(rs.getInt("Book_id"));

            for(int i=0;i<1;i++){
                
           books[i]=(rs4.getString("Book_id"));
               System.out.println(books[i]);
               b.add(books[i]);
                }
//               pp.add(books[i]);
            }
           
         // bor.setText(String.valueOf(builder));
       
       for(int i=0;i<b.size();i++){
                         booktitles(b.get(i));
                         decreaseq(b.get(i));
       }
         st5=con4.createStatement();
       rs5=st5.executeQuery("Select * from buy_table where Bill_no='"+Bill_no+"'");
              while(rs5.next()){

              lbl_Amount.setText("Total:"+rs5.getString("Price"));

   lbl_Billno.setText("#"+String.valueOf(Bill_no));
   lbldate.setText(rs5.getString("purchase_date"));
   finala.setText("Final Amount:"+amount.toString());
      dis.setText("Discount: -"+discount.toString());

              }
       finala.setVisible(true);
           dis.setVisible(true);
       }
       catch(SQLException e){
        JOptionPane.showMessageDialog(null,e.getMessage());
       }finally{
         if (rs4!=null) 
             try{rs4.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (st4!=null) 
             try{st4.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (rs5!=null) 
             try{rs5.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (st5!=null) 
             try{st5.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (con4!=null) 
             try{con4.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
}}
   public void booktitles(String book_id){
       try{
           con=mainclass.createConnection();
       st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       String query="Select * from book_table where Book_id='"+book_id+"'";
              rs=st.executeQuery(query);
              while(rs.next()){
                  pp.add(new JLabel(rs.getString("Title")));
                  jj.add(new JLabel(rs.getString("Price")));
              }
       }catch(SQLException e){
                   JOptionPane.showMessageDialog(null,e.getMessage());

}finally{if (rs!=null) 
             try{rs.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (st!=null) 
             try{st.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if(con!=null){
             try{con.close();
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null, e.getMessage());
             }
         }
       }}
   public void decreaseq(String book_id){
       try{
           con=mainclass.createConnection();
       String queryq="select * from book_table where Book_id='"+Integer.valueOf(book_id)+"'";
        int quantity=0;
        con=mainclass.createConnection();
         st=con.createStatement();
         rs=st.executeQuery(queryq);
            rs.next();
            quantity=rs.getInt("quantity")-1;
         pst3=con.prepareStatement("update book_table set quantity=? where Book_id='"+Integer.valueOf(book_id)+"'");
        pst3.setInt(1, quantity);
       }catch(SQLException e){
                   JOptionPane.showMessageDialog(null,e.getMessage());

   }finally{if (rs!=null) 
             try{rs.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (st!=null) 
             try{st.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if (pst3!=null) 
             try{pst3.close();
             }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());}
         if(con!=null){
             try{con.close();
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null, e.getMessage());
             }
         }}}
   
   public BoughtBill(int Bill_no,int Amount,String AddressLine, String Type, Date date) {
   this.Bill_no=Bill_no;
   this.AddressLine=AddressLine;
   this.Type=Type;
   this.date=date;
   }
   
      
   public int getBillNo () {
           return Bill_no; 
   }      

    public String getAddressLine() {
           return AddressLine; 
   } 
    
    public Date getDate() {
           return date; 
   }  
   
    @Override
   public String toString(){
       return "\n\tBill details:"+"\n\tBill ID: "+ Bill_no +"\n\tOrder Type: "+ Type +"\n\tAddress Line"+ AddressLine; 
     }
  
   
//      private void updateCompo() {
//          try {
//             String query = "select Bill_no from bill_table";
//             pst = con.prepareStatement(query);
//            rs = pst.executeQuery();
//            while (rs.next())
//                {      
//                    jComboBox1.addItem(rs.getString("Bill_no"));
//               }
////            con.close();
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null,e.getMessage());
//                        }
//          
//    }
      
  
  
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbl_Itemname = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        rr = new javax.swing.JLabel();
        lbl_Amount = new javax.swing.JLabel();
        lbl_Type = new javax.swing.JLabel();
        lbl_Billno = new javax.swing.JLabel();
        pp = new java.awt.Panel();
        lbl_AddressLine = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jj = new java.awt.Panel();
        save = new javax.swing.JButton();
        back = new javax.swing.JButton();
        eexit = new javax.swing.JButton();
        dis = new javax.swing.JLabel();
        finala = new javax.swing.JLabel();
        lla = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("----------------------------------------------------");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 252, -1));

        jLabel2.setText("----------------------------------------------------");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 121, 252, 5));

        jLabel3.setText("----------------------------------------------------");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 150, 252, 10));

        jLabel5.setText("****************************************************");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 396, 252, -1));

        jLabel7.setText("----------------------------------------------------");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 252, -1));

        jLabel8.setText("----------------------------------------------------");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 264, 252, -1));

        jLabel9.setText("****************************************************");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 437, 252, -1));

        jLabel10.setText("IthraLibrary.com");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 56, -1, -1));

        jLabel12.setText(" AlRakah- Khobar");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 110, -1));

        lbl_Itemname.setText("Item Name                                     Price");
        jPanel1.add(lbl_Itemname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel16.setText("THANK YOU FOR VISITING US");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 415, -1, -1));
        jPanel1.add(rr, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 377, 168, 13));

        lbl_Amount.setText("Total amount:");
        jPanel1.add(lbl_Amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 131, -1));

        lbl_Type.setText("PURCHASE BILL");
        jPanel1.add(lbl_Type, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 140, -1));

        lbl_Billno.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        lbl_Billno.setText("Bill#");
        jPanel1.add(lbl_Billno, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 73, -1));

        pp.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(pp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 130, 100));

        lbl_AddressLine.setText("ComputerScience College");
        jPanel1.add(lbl_AddressLine, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 198, -1));
        jPanel1.add(lbldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 348, 176, 23));
        jPanel1.add(jj, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 40, 100));

        save.setFont(new java.awt.Font("SimSun", 0, 12)); // NOI18N
        save.setText("Save");
        save.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel1.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 60, 20));

        back.setFont(new java.awt.Font("SimSun", 0, 12)); // NOI18N
        back.setText("Back");
        back.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 60, 20));

        eexit.setFont(new java.awt.Font("SimSun", 0, 12)); // NOI18N
        eexit.setText("Exit");
        eexit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        eexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eexitActionPerformed(evt);
            }
        });
        jPanel1.add(eexit, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 60, 20));

        dis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dis.setText("discount:");
        jPanel1.add(dis, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        finala.setText("Final Amount:");
        jPanel1.add(finala, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 244, 510));

        lla.setText("                      ");
        jPanel2.add(lla, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 839, 239, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        String Bill_Type="Borrow bill";
        String Billnum=String.valueOf(Bill_no);
        String Address_Line="ComputerScience College, AlRakah-Alkhobar";
        String totalAmount=lbl_Amount.getText();
        try {
            FileWriter Writer = new FileWriter("BoughtBills.txt",true);
            Writer.write(Bill_Type+Billnum+" \n"+Address_Line+" \n"+totalAmount+"\n-----------------------------\n");
            Writer.close();
            JOptionPane.showMessageDialog(null, "A file with your borrowed bills has been created");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cant export a file with your bills");}

    }//GEN-LAST:event_saveActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        cv.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void eexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eexitActionPerformed
        // TODO add your handling code here:
        JFrame frame=new JFrame("Exit");
        if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to Exit?","Exit",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        {
            System.exit(0);
        }

    }//GEN-LAST:event_eexitActionPerformed
    
        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //</editor-fold>

        /* Create and display the form */
        
         try {
      File billsFile = new File("BoughtBills.txt");
      if (billsFile.createNewFile()) {
        System.out.println("File created: " + billsFile.getName());
      } 
    }   
         catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
 
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() {
              new BoughtBill().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel dis;
    private javax.swing.JButton eexit;
    private javax.swing.JLabel finala;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Panel jj;
    private javax.swing.JLabel lbl_AddressLine;
    private javax.swing.JLabel lbl_Amount;
    private javax.swing.JLabel lbl_Billno;
    private javax.swing.JLabel lbl_Itemname;
    private javax.swing.JLabel lbl_Type;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lla;
    private java.awt.Panel pp;
    private javax.swing.JLabel rr;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}

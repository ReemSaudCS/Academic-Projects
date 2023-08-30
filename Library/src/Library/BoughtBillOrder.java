/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;
import Library.BorrowBill;
import java.util.Date;

/** 
 *
 * @author mareo
 */

public class BoughtBillOrder extends BorrowBill {
   private String paymentMethod, holderName; 
   int id;
   private int amount,amountPaid,cardNumber, cvc;
   private Date expMonth, expYear;

   
   public BoughtBillOrder () {}
   public void setBillNo(int x){
       this.id=x;
   }
  public int getBillNo(){
      return id;
  }
   public BoughtBillOrder (String paymentMethod, int amount) {
   this.amount=amount;
   this.paymentMethod=paymentMethod;
   }
   
   public void setPaymentMethod (String paymentMethod) {
       this.paymentMethod=paymentMethod;
   }
   
   public String getPaymentMethod () {
   
   return paymentMethod; 
   }  
   public void setAmount(int amount) {
           this.amount=amount;
   } 
    public int getAmount() {
           return amount; 
   } 
   
   public void setCardInfo (String holderName, int cardNumber, int cvc, Date expMonth, Date expYear ) {
       this.holderName=holderName;
       this.cardNumber=cardNumber;
       this.cvc=cvc;
       this.expMonth=expMonth;
       this.expYear=expYear;   
   }
   
   public double calcChange(int amountPaid,int amount){

        int change = (amountPaid-amount);
        return change;
    }

    public void setAmountPaid(int amountPaid){

        this.amountPaid = amountPaid;

    }

    public int getAmountPaid(){

        return amountPaid;

    }
    
   
}

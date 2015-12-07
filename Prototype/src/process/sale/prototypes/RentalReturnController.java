/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Pikachu
 */
public class RentalReturnController extends TransactionController{
    private RentalReturn rentalReturn;
    private Scanner scanner;
    
    /**
     *
     * @param rental
     */
    public RentalReturnController(Rental rental){
        rentalReturn = new RentalReturn(rental);
    }
    
    /**
     *
     * @param rentalReturn
     */
    public RentalReturnController(RentalReturn rentalReturn){
        this.rentalReturn = rentalReturn;
        display();
    }
    
    /**
     *
     */
    @Override
    public void processSuspend(){
       //RentalManager.getInstance().addSuspendedRental(rentalReturn);
    }
    
    /**
     *
     */
    public String close() {
        rentalReturn.getRental().addRentalreturn(rentalReturn);
        return printReceipt();
    }
    
        /**
     * Create a cash payment
     */
    public CashPayment processCashPayment(int payment){
        CashPayment cash;
        if(payment > leftToPay){
            //System.out.printf("Your change is $%.2f\n", payment - leftToPay);
            rentalReturn.addPayment(cash = new CashPayment(payment, leftToPay));
            leftToPay = 0;
        }
        else if(payment == leftToPay){
            rentalReturn.addPayment(cash = new CashPayment(payment, payment));
            leftToPay-=payment;
        }
        else{
            rentalReturn.addPayment(cash = new CashPayment(payment, payment));
            leftToPay-=payment;
        }
        return cash;
    }
    
    /**
     * Create a Credit payment
     */
    public CreditPayment processCreditPayment(String cardNum, String secNum, float payment){
        if(payment > leftToPay)
            JOptionPane.showMessageDialog (null, "Payment is more than total.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        
        CreditPayment credit = new CreditPayment(cardNum, secNum, payment);
        if(processCreditPayment(credit)){
            rentalReturn.addPayment(credit);
            leftToPay -= payment;
            return credit;
        }
        else{
            return null;
        }
    }
   
    /**
     * Make a debit payment
     */
    public DebitPayment processDebitPayment(String cardNum, int pin, float payment){
       if(payment > leftToPay)
           JOptionPane.showMessageDialog (null, "Payment is more than total.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
               
        DebitPayment debit = new DebitPayment(cardNum, pin, payment);
        if(processDebitPayment(debit)){
            rentalReturn.addPayment(debit);
            leftToPay-=payment;
            return debit;
        }
        else{
            return null;
        }
    }
    
    /**
     *
     */
    public void processVoid(int code, int quantity, int days){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            JOptionPane.showMessageDialog (null, "Invalid product code: "+ code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for(int i = 0; i < quantity; i++)
            if(!rentalReturn.removeItem(product, days, true))
                break;
    }
    
    /**
     *
     * @param code
     */
    public void processProduct(int code, int amount, int days){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        LineItem retLineItem=rentalReturn.getLineItemByCodeAndDaysRented(code, days);
        if(product == null){ //product does not exist
            JOptionPane.showMessageDialog (null, "Invalid product code: " + code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }else if(!product.getIsRentable()){
            JOptionPane.showMessageDialog (null, "Item " + code + " is not rentable.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }else if(rentalReturn.getRental().getLineItemByCodeAndDaysRented(code, days) == null){
            JOptionPane.showMessageDialog (null, "Item " + code + " is not in this rental.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }else if(retLineItem !=null && 
                 retLineItem.getQuantity()== rentalReturn.getRental().getLineItemByCodeAndDaysRented(code, days).getQuantity()){
            JOptionPane.showMessageDialog (null, "Item " + code + " has already been returned.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }else{
            for(int i = 0; i < amount; i++)
                rentalReturn.addItem(product, days, true);
        }
    }
    
    
    /**
     *
     */
    @Override
    public final String display(){
        return rentalReturn.toString();
    }
    
    public String getTotals(){
        String output = rentalReturn.printTotals();
        leftToPay = rentalReturn.getTotal();
        return output;
    }
    
    /**
     *
     */
    @Override
    public String printReceipt(){
        String output = rentalReturn.printTotals();
        ArrayList<Payment> payments = rentalReturn.getPayments();
        for(int i = 0; i < payments.size(); i++){
            output += payments.get(i).toString();
        }
        return output;
    }
}

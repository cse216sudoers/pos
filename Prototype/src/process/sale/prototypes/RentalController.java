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
 * Keeps track of all of the operations for a Rental domain object
 * @author Pikachu
 */
public class RentalController extends TransactionController{
    private Rental rental;
    private Scanner scanner;
    
    /**
     * Initialize a rentalController and corresponding rental
     */
    public RentalController(){
        rental = new Rental();
    }
    
    /**
     * Initialize a rentalController for a suspended rental
     * @param rental
     */
    public RentalController(Rental rental){
        this.rental = rental;
        display();
    }
    
    /**
     *Suspend the rental
     */
    @Override
    public void processSuspend(){
        RentalManager.getInstance().addSuspendedRental(rental);
    }
    
    /**
     * Close the sale
     */
    public String close() {
        RentalManager.getInstance().addRental(rental);
        return printReceipt();
    }
    
        /**
     * Create a cash payment
     */
    public CashPayment processCashPayment(int payment){
        CashPayment cash;
        if(payment > leftToPay){
            //System.out.printf("Your change is $%.2f\n", payment - leftToPay);
            rental.addPayment(cash = new CashPayment(payment, leftToPay));
            leftToPay = 0;
        }
        else if(payment == leftToPay){
            rental.addPayment(cash = new CashPayment(payment, payment));
            leftToPay-=payment;
        }
        else{
            rental.addPayment(cash = new CashPayment(payment, payment));
            leftToPay-=payment;
        }
        return cash;
    }
    
    /**
     * Create a Credit payment
     */
    public CreditPayment processCreditPayment(String cardNum, String secNum, float payment){
        if(payment > leftToPay)
            System.out.println("Payment is more than total.");
        
        CreditPayment credit = new CreditPayment(cardNum, secNum, payment);
        if(processCreditPayment(credit)){
            rental.addPayment(credit);
            leftToPay -= payment;
            return credit;
        }
        else{
            return null;
        }
    }
    
    //check if payment is valid
    private boolean processCreditPayment(CreditPayment payment){
        String cardNum = payment.getCardNum();
        String secNum = payment.getSecurityCode();
        if(cardNum.length() == 16 && secNum.length() == 3)
            return true;
        return false;
    }
    
    /**
     * Make a debit payment
     */
    public DebitPayment processDebitPayment(String cardNum, int pin, float payment){
       if(payment > leftToPay)
           System.out.println("Payment is more than total.");
               
        DebitPayment debit = new DebitPayment(cardNum, pin, payment);
        if(processDebitPayment(debit)){
            rental.addPayment(debit);
            leftToPay-=payment;
            return debit;
        }
        else{
            return null;
        }
    }
    
    //Check if debit payment is valid
    private boolean processDebitPayment(DebitPayment payment){
        String cardNum = "" + payment.getCardNum();
        String pin = "" + payment.getPin();
        if(cardNum.length() == 16 && pin.length() == 4)
            return true;
        return false;
    }
    
    /**
     *Remove item from rental
     */
    public void processVoid(int code, int days, int quantity){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        for(int i = 0; i < quantity; i++)
            if(!rental.removeItem(product, days, true))
                break;
    }
    
    /**
     * Add item to rental
     * @param code product code
     */
    public void processProduct(int code, int quantity, int days){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
  
        if(product == null){ //product does not exist
            JOptionPane.showMessageDialog (null, "Invalid product code: " + code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
            System.out.println("Invalid product code: " + code);
        }else if(!product.getIsRentable()){
            JOptionPane.showMessageDialog (null, "Item can not be rented: " + code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
            System.out.println("Item cannot be rented: " + code);
        }else if(!product.productLeft()){
            JOptionPane.showMessageDialog (null, "Item out of stock: " + code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
            System.out.println("Item out of stock: " + code);
        }else{
            for(int i = 0; i < quantity; i++)
                rental.addItem(product, days, true);
        }
    }
    
    public String getTotals(){
        String output = rental.printTotals();
        leftToPay = rental.getTotal();
        return output;
    }
    
    /**
     * display rental
     */
    @Override
    public final String display(){
        return rental.toString();
    }
    
    /**
     * Print receipt
     */
    @Override
    public String printReceipt(){
        String output = rental.printTotals();
        ArrayList<Payment> payments = rental.getPayments();
        for(int i = 0; i < payments.size(); i++){
            output += payments.get(i).toString();
        }
        return output;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
     * Create a Credit payment
     */
    public CreditPayment processCreditPayment(String cardNum, String secNum, float payment){
        if(payment > leftToPay){
            float amount=leftToPay;
            JOptionPane.showMessageDialog (null, "Payment is more than total.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
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

    
    /**
     *Remove item from rental
     */
    public void processVoid(int code, int days, int quantity){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            JOptionPane.showMessageDialog (null, "Invalid product code: "+ code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
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
        }else if(!product.getIsRentable()){
            JOptionPane.showMessageDialog (null, "Item can not be rented: " + code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }else if(!product.productLeft()){
            JOptionPane.showMessageDialog (null, "Item out of stock: " + code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }else{
            for(int i = 0; i < quantity; i++)
                rental.addItem(product, days, true);
        }
    }
    
    public String getTotals(){
        String output = rental.printTotals();
        leftToPay = rental.getTotal();
        rental.startDate = new GregorianCalendar();
        //String pay=Float.toString(leftToPay);
        String pay=String.format("%.2f",leftToPay);
        leftToPay=Float.parseFloat(pay);
        float amount=leftToPay;
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

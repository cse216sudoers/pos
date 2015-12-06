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
            System.out.println("Payment is more than total.");
        
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
            rentalReturn.addPayment(debit);
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
     *
     */
    public void processVoid(int code, int quantity, int days){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
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
  
        if(product == null){ //product does not exist
            JOptionPane.showMessageDialog (null, "Invalid product code: " + code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
            System.out.println("Invalid product code: " + code);
        }else if(!product.getIsRentable()){
            System.out.println("Item cannot be rented: " + code);
        }else{
            for(int i = 0; i < amount; i++)
                rentalReturn.addItem(product, days, true);
        }
    }
    
    private void processCoupon(){
        String next = "";
        int code;
        int productCode;
        float amount;
        try{
            System.out.print("Please enter coupon code: ");
            next = scanner.next();
            code = Integer.parseInt(next);
        }catch(Exception e){
            System.out.println("Invalid code: " + next);
            return;
        }
        
        try{
            System.out.print("Please enter product code: ");
            next = scanner.next();
            productCode = Integer.parseInt(next);
        }catch(Exception e){
            System.out.println("Invalid code: " + next);
            return;
        }

        try{
            System.out.print("Please enter coupon amount: ");
            next = scanner.next();
            DecimalFormat myFormatter = new DecimalFormat("0.00");
            amount = Float.parseFloat(next);
            amount = Float.parseFloat(myFormatter.format(amount));
        }catch(Exception e){
            System.out.println("Invalid amount: " + next);
            return;
        }
        
        rentalReturn.addCoupon(new Coupon(code, productCode, amount));
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

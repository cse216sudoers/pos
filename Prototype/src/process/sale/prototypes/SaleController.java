/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package process.sale.prototypes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Keeps track of a single sale and does all basic operations for that sale domain object
 * @author Pikachu
 */
public class SaleController extends TransactionController{
    private Sale sale;
    private Scanner scanner;
    
    /**
     * Create new sale controller for a sale
     */
    public SaleController(){
        sale = new Sale();
    }
    
    /**
     * make a sale controller for a suspended sale
     * @param sale
     */
    public SaleController(Sale sale){
        this.sale = sale;
        display(); //display what is already in the sale
    }
    
    /**
     * Suspend the sale
     */
    @Override
    public void processSuspend(){
        SaleManager.getInstance().addSuspendedSale(sale);
    }

    /**
     * Create a cash payment
     */
    public CashPayment processCashPayment(int payment){
        CashPayment cash;
        if(payment > leftToPay){
            //System.out.printf("Your change is $%.2f\n", payment - leftToPay);
            sale.addPayment(cash = new CashPayment(payment, leftToPay));
            leftToPay = 0;
        }
        else if(payment == leftToPay){
            sale.addPayment(cash = new CashPayment(payment, payment));
            leftToPay-=payment;
        }
        else{
            sale.addPayment(cash = new CashPayment(payment, payment));
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
            sale.addPayment(credit);
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
            sale.addPayment(debit);
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
     * Remove an item from the sale
     */
    public void processVoid(int code, int quantity){  
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        for(int i = 0; i < quantity; i++)
            if(!sale.removeItem(product, true))
                break;
    }
    
    /**
     * Add a product to the sale
     * @param code product to add
     */
    @Override
    public void processProduct(int code, int amount){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        
        if(product.getQuantity()>=1&&amount>product.getQuantity())
            System.out.println("Not enough of item "+code+" in stock");
        else if(product.productLeft()) {
            for(int i=0; i<amount; i++)
                sale.addItem(product, true);
        } else
            System.out.println("Item out of stock: " + code);
    }
    
    //coupon to add to sale
    public void processCoupon(int code){
        sale.addCoupon(CouponCatalog.getCatalog().findCouponByCode(code));
    }
    
    //override an item's price
    private void processOverride(){
        String next = "";
        int productCode;
        float amount;
        
        try{
            System.out.print("Please enter product code: ");
            next = scanner.next();
            productCode = Integer.parseInt(next);
        }catch(Exception e){
            System.out.println("Invalid code: " + next);
            return;
        }
        
        if (sale.getLineItemByCode(productCode) == null) {
            System.out.println("Product not in current sale.");
            return;
        }

        try{
            System.out.print("Please enter override amount: ");
            next = scanner.next();
            DecimalFormat myFormatter = new DecimalFormat("0.00");
            amount = Float.parseFloat(next);
            amount = Float.parseFloat(myFormatter.format(amount));
        }catch(Exception e){
            System.out.println("Invalid amount: " + next);
            return;
        }
        //float pre = sale.getLineItemByCode(productCode).getPrice();
        sale.getLineItemByCode(productCode).setPrice(amount);
        //sale.subtotal-= sale.getLineItemByCode(productCode).getQuantity()*(pre-sale.getLineItemByCode(productCode).getPrice());
    }
    
    public String close(){
        SaleManager.getInstance().addSale(sale);
        return printReceipt();
    }
    
    /**
     * Display the sale as it would be displayed on a monitor
     */
    @Override
    public final String display(){
        return sale.toString();
    }
    
    public String getTotals(){
        String output = sale.printTotals();
        leftToPay = sale.getTotal();
        return output;
    }
    /**
     * Print the receipt
     */
    @Override
    public String printReceipt(){
        String output = sale.printTotals();
        ArrayList<Payment> payments = sale.getPayments();
        for(int i = 0; i < payments.size(); i++){
            output += payments.get(i).toString();
        }
        return output;
    }
}
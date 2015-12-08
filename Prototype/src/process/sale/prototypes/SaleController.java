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
    public CashPayment processCashPayment(float payment){
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
        if(payment > leftToPay){
            JOptionPane.showMessageDialog (null, "Payment is more than total.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        CreditPayment credit = new CreditPayment(cardNum, secNum, payment);
        if(processCreditPayment(credit)){
            sale.addPayment(credit);
            leftToPay -= payment;
            String pay=String.format("%.2f",leftToPay);
            leftToPay=Float.parseFloat(pay);
            System.out.println(leftToPay);
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
       if(payment > leftToPay){
            JOptionPane.showMessageDialog (null, "Payment is more than total.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return null;
        }    
        DebitPayment debit = new DebitPayment(cardNum, pin, payment);
        if(processDebitPayment(debit)){
            sale.addPayment(debit);
            leftToPay-=payment;
            String pay=String.format("%.2f",leftToPay);
            leftToPay=Float.parseFloat(pay);
            return debit;
        }
        else{
            return null;
        }
    }
    
    /**
     * Remove an item from the sale
     */
    public void processVoid(int code, int quantity){  
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            JOptionPane.showMessageDialog (null, "Invalid product code: "+code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(quantity>sale.getLineItemByCode(product.getCode()).getQuantity()){
            JOptionPane.showMessageDialog (null, "There are only "+ sale.getLineItemByCode(product.getCode()).getQuantity()+" of "+ code+" in this sale", "Invalid Input", JOptionPane.ERROR_MESSAGE);
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
    public void processProduct(int code, int amount){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        if(product == null){ //product does not exist
            JOptionPane.showMessageDialog (null, "Invalid product code: " + code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(product.getQuantity()>=1&&amount>product.getQuantity()){
            JOptionPane.showMessageDialog (null, "Only "+ product.getQuantity() +" left in stock: " + code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }else if(product.productLeft()) {
            for(int i=0; i<amount; i++)
                sale.addItem(product, true);
        }else{
            JOptionPane.showMessageDialog (null, "Item out of stock: " + code, "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //coupon to add to sale
    public void processCoupon(int code){
        sale.addCoupon(CouponCatalog.getCatalog().findCouponByCode(code));
    }
    
    //override an item's price
    public void processOverride(int productCode, float amount){
        DecimalFormat myFormatter = new DecimalFormat("0.00");
        amount = Float.parseFloat(myFormatter.format(amount));
        if(sale.getLineItemByCode(productCode) == null){
            JOptionPane.showMessageDialog (null, "Invalid product code: " + productCode, "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(amount > sale.getLineItemByCode(productCode).getPrice()){
            JOptionPane.showMessageDialog (null, "Cannot override to high price.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
            sale.getLineItemByCode(productCode).setPrice(amount);
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
        String pay=String.format("%.2f",leftToPay);
        leftToPay=Float.parseFloat(pay);
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
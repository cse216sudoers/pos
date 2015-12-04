/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Pikachu
 */
public class ReturnController extends TransactionController{
    private Return ret;
    private Scanner scanner;
    
    /**
     *
     * @param saleId
     */
    public ReturnController(int saleId){
        ret = new Return(saleId);
    }
    
    /**
     *
     * @param ret
     */
    public ReturnController(Return ret){
        this.ret = ret;
        display();
    }
    
    /**
     *
     */
    @Override
    public void processSuspend(){
        ReturnManager.getInstance().addSuspendedReturn(ret);
    }
    
    /**
     *
     */
    public void close() {
        ArrayList<Payment> payments = SaleManager.getInstance().getSaleById(ret.getSaleId()).getPayments();
        ret.printTotals();
        leftToPay = ret.getTotal();
        int i = 0;
        while(leftToPay > 0){
            Payment.PaymentType paymentType = payments.get(i).type;
            if(paymentType == Payment.PaymentType.CASH)
                processCashPayment(payments.get(i));
            else if(paymentType == Payment.PaymentType.CREDIT)
                processCreditPayment(payments.get(i));
            else if(paymentType == Payment.PaymentType.DEBIT)
                processDebitPayment(payments.get(i));
            i++;
        }
        ReturnManager.getInstance().addReturn(ret);
    }
    
    private void processCashPayment(Payment payment){
        if(payment.amount >= leftToPay){
            ret.addPayment(new CashPayment(leftToPay, leftToPay));
            leftToPay = 0;
        }
        else if(payment.amount < leftToPay){
            ret.addPayment(new CashPayment(leftToPay, payment.amount));
            leftToPay-=payment.amount;
        }
    }
    
    private void processCreditPayment(Payment payment){
       CreditPayment p = (CreditPayment)payment;
       if(payment.amount >= leftToPay){
            ret.addPayment(new CreditPayment(p.getCardNum(), p.getSecurityCode(),leftToPay));
            leftToPay = 0;
        }
        else if(payment.amount < leftToPay){
            ret.addPayment(new CreditPayment(p.getCardNum(), p.getSecurityCode(),p.amount));
            leftToPay-=p.amount;
        }
    }
    
    private void processDebitPayment(Payment payment){
       DebitPayment p = (DebitPayment)payment;
       if(payment.amount >= leftToPay){
            ret.addPayment(new DebitPayment(p.getCardNum(), p.getPin(),leftToPay));
            leftToPay = 0;
        }
        else if(payment.amount < leftToPay){
            ret.addPayment(new DebitPayment(p.getCardNum(), p.getPin(),p.amount));
            leftToPay-=p.amount;
        }
    }
    
    /**
     *
     */
    public void processVoid(int code, int quantity){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        for(int i = 0; i < quantity; i++)
            if(!ret.removeItem(product))
                break;
    }
    
    /**
     *
     * @param code
     */
    @Override
    public void processProduct(int code, int amount){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        if(!ret.addItem(product)){
            System.out.println("Item does not exist on this receipt.");
        }
        
    }
    
    /**
     *
     */
    @Override
    public String display(){
        return ret.toString();
    }
    
    public String getTotals(){
        String output = ret.printTotals();
        leftToPay = ret.getTotal();
        return output;
    }
    /**
     *
     */
    @Override
    public String printReceipt(){
        String output = ret.printTotals();
        ArrayList<Payment> payments = ret.getPayments();
        for(int i = 0; i < payments.size(); i++){
            output += payments.get(i).toString();
        }
        return output;
    }
}

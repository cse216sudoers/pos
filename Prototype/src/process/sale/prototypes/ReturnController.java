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
    public void start(){
        
        //continuous return loop
        while(true){
            try{
                System.out.print("Please enter 'void', <code>, 'override', 'suspend', or 'close': ");
                scanner = new Scanner(System.in);
                input = scanner.next();
                //void item
                if(input.equalsIgnoreCase("void")){
                    processVoid();
                    display();
                }
                //override
                else if(input.equalsIgnoreCase("override")){
                    //manager override
                }
                //add item to return
                else if (input.charAt(0) >= '0' && input.charAt(0) <= '9'){
                    processProduct(Integer.parseInt(input));
                    display();
                }
                //end return
                else if (input.equalsIgnoreCase("close")){
                    // Close return and get payment
                    close();
                    break;
                }
                //suspend Return
                else if (input.equalsIgnoreCase("suspend")){
                    // Close Sale and get payment
                    processSuspend();
                    break;
                }
                else{
                    System.out.println("Invalid input: " + input);
                }
            }catch(Exception e){
                System.out.println(e.toString());
                System.out.println("Invalid input");
            }
        }
    }
    
    /**
     *
     */
    @Override
    protected void processSuspend(){
        ReturnManager.getInstance().addSuspendedReturn(ret);
    }
    
    /**
     *
     */
    @Override
    protected void close() {
        ArrayList<Payment> payments = SaleManager.getInstance().getSaleById(ret.getSaleId()).getPayments();
        ret.printTotals();
        leftToPay = ret.getTotal();
        int i = 0;
        while(leftToPay > 0){
            System.out.println("in loop " + leftToPay);
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
        // Thank customer, and close
        printReceipt();
        System.out.println("\nThank for you shopping with us. Have a nice day!");
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
    @Override
    protected void processVoid(){
        System.out.print("Please enter a product code: ");
        int code = scanner.nextInt();
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        ret.removeItem(product);
    }
    
    /**
     *
     * @param code
     */
    @Override
    protected void processProduct(int code){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        ret.addItem(product);
    }
    
    /**
     *
     */
    @Override
    protected void display(){
        System.out.println(ret);
    }
    /**
     *
     */
    @Override
    protected void printReceipt(){
        System.out.print("******************************************");
        ret.printTotals();
        ArrayList<Payment> payments = ret.getPayments();
        for(int i = 0; i < payments.size(); i++){
            System.out.print(payments.get(i));
        }
        System.out.print("\n******************************************");
    }
}

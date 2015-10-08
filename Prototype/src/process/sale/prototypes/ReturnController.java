/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Pikachu
 */
public class ReturnController {
    private Return ret;
    private float leftToReturn; //so we can do multiple tupes of payments
    private Scanner scanner;
    private String input;
    
    public ReturnController(int saleId){
        ret = new Return(saleId);
    }
    public void startReturn(){
        
        //continuous return loop
        while(true){
            try{
                System.out.print("Please enter 'void', <code>, 'override', or 'close': ");
                scanner = new Scanner(System.in);
                input = scanner.next();
                //void item
                if(input.equalsIgnoreCase("void")){
                    processVoid();
                    displayReturn();
                }
                //override
                else if(input.equalsIgnoreCase("override")){
                    //manager override
                }
                //add item to return
                else if (input.charAt(0) >= '0' && input.charAt(0) <= '9'){
                    processProduct(Integer.parseInt(input));
                    displayReturn();
                }
                //end return
                else if (input.equalsIgnoreCase("close")){
                    // Close return and get payment
                    closeReturn();
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
    
    private void closeReturn() {
        ArrayList<Payment> payments = SaleManager.getInstance().getSaleById(ret.getSaleId()).getPayments();
        ret.printTotals();
        leftToReturn = ret.getReturnTotal();
        int i = 0;
        while(leftToReturn > 0){
            System.out.println("in loop " + leftToReturn);
            Payment.PaymentType paymentType = payments.get(i).type;
            if(paymentType == Payment.PaymentType.CASH)
                processCashReturn(payments.get(i));
            else if(paymentType == Payment.PaymentType.CREDIT)
                processCreditReturn(payments.get(i));
            else if(paymentType == Payment.PaymentType.DEBIT)
                processDebitReturn(payments.get(i));
            i++;
        }
        ReturnManager.getInstance().addReturn(ret);
        // Thank customer, and close
        printReceipt();
        System.out.println("\nThank for you shopping with us. Have a nice day!");
    }
    
    private void processCashReturn(Payment payment){
        if(payment.amount >= leftToReturn){
            ret.addReturnPayment(new CashPayment(leftToReturn, leftToReturn));
            leftToReturn = 0;
        }
        else if(payment.amount < leftToReturn){
            ret.addReturnPayment(new CashPayment(leftToReturn, payment.amount));
            leftToReturn-=payment.amount;
        }
    }
    
    private void processCreditReturn(Payment payment){
       CreditPayment p = (CreditPayment)payment;
       if(payment.amount >= leftToReturn){
            ret.addReturnPayment(new CreditPayment(p.getCardNum(), p.getSecurityCode(),leftToReturn));
            leftToReturn = 0;
        }
        else if(payment.amount < leftToReturn){
            ret.addReturnPayment(new CreditPayment(p.getCardNum(), p.getSecurityCode(),p.amount));
            leftToReturn-=p.amount;
        }
    }
    
    private void processDebitReturn(Payment payment){
       DebitPayment p = (DebitPayment)payment;
       if(payment.amount >= leftToReturn){
            ret.addReturnPayment(new DebitPayment(p.getCardNum(), p.getPin(),leftToReturn));
            leftToReturn = 0;
        }
        else if(payment.amount < leftToReturn){
            ret.addReturnPayment(new DebitPayment(p.getCardNum(), p.getPin(),p.amount));
            leftToReturn-=p.amount;
        }
    }
    
    private void processVoid(){
        System.out.print("Please enter a product code: ");
        int code = scanner.nextInt();
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        ret.removeItem(product);
    }
    
    private void processProduct(int code){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        ret.addItem(product);
    }
    
    private void displayReturn(){
        System.out.println(ret);
    }
    private void printReceipt(){
        System.out.print("******************************************");
        ret.printTotals();
        ArrayList<Payment> payments = ret.getPayments();
        for(int i = 0; i < payments.size(); i++){
            System.out.print(payments.get(i));
        }
        System.out.print("\n******************************************");
    }
}

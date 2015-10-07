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
public class SaleController {
    private Sale sale;
    private float leftToPay; //so we can do multiple tupes of payments
    private Scanner scanner;
    private String input;
    
    public SaleController(){
        sale = new Sale();
    }
    public void startSale(){
        
        //continuous sale loop
        while(true){
            try{
                System.out.print("Please enter 'void', 'coupon', <code>, 'override', or 'close': ");
                scanner = new Scanner(System.in);
                input = scanner.next();
                //void item
                if(input.equalsIgnoreCase("void")){
                    processVoid();
                }
                //coupon
                else if(input.equalsIgnoreCase("coupon")){
                    processCoupon();
                }
                //override
                else if(input.equalsIgnoreCase("override")){
                    //manager override
                }
                //add item to sale
                else if (input.charAt(0) >= '0' && input.charAt(0) <= '9'){
                    processProduct(Integer.parseInt(input));
                }
                //end sale
                else if (input.equalsIgnoreCase("close")){
                    // Close sale and get payment
                    closeSale();
                    break;
                }
                else{
                    System.out.println("Invalid input: " + input);
                }
                displaySale();
            }catch(Exception e){
                System.out.println(e.toString());
                System.out.println("Invalid input");
            }
        }
    }
    
    private void closeSale() {
        String paymentType;
        boolean validType;
        
        // Give total price (subtotal, tax, and total)
        sale.printTotals();
        leftToPay = sale.getSaleTotal();
        while(leftToPay > 0){
            validType= false;
            System.out.println("Please enter a form of payment (cash, credit, or debit): ");
            paymentType = scanner.next();

            if(paymentType.equalsIgnoreCase("credit")||paymentType.equalsIgnoreCase("debit")||paymentType.equalsIgnoreCase("cash"))
                validType = true;
            while(!validType){
                System.out.println("Invalid payment type. \nPlease enter a form of payment (cash, credit, or debit): ");
                paymentType = scanner.next();
                if(paymentType.equalsIgnoreCase("credit")||paymentType.equalsIgnoreCase("debit")||paymentType.equalsIgnoreCase("cash"))
                    validType = true;
            }

            if(paymentType.equalsIgnoreCase("cash"))
                processCashPayment();
            else if(paymentType.equalsIgnoreCase("credit"))
                processCreditPayment();
            else if(paymentType.equalsIgnoreCase("debit"))
                processDebitPayment();
            if(leftToPay > 0)
                System.out.printf("Total: $%7.2f \n", leftToPay);
        }
        SaleManager.getInstance().addSale(sale);
        // Thank customer, and close
        printReceipt();
        System.out.println("\nThank for you shopping with us. Have a nice day!");
        System.exit(0);
    }
    
    private void processCashPayment(){
        float payment = 0;
        System.out.println("Please enter total cash payment: ");
        try{
            payment = scanner.nextFloat();
        }catch(Exception e){
            System.out.println("Invalid payment.");
        }
        if(payment > leftToPay){
            System.out.printf("Your change is $%.2f\n", payment - leftToPay);
            sale.addPayment(new CashPayment(payment, leftToPay));
            leftToPay = 0;
        }
        else if(payment == leftToPay){
            sale.addPayment(new CashPayment(payment, leftToPay));
            leftToPay-=payment;
        }
        else{
            sale.addPayment(new CashPayment(payment, leftToPay-payment));
            leftToPay-=payment;
        }
        System.out.println("done");
    }
    
    private void processCreditPayment(){
        float payment = 0;
        boolean invalid = true;
        boolean accepted; //for payment
        String cardNum = "";
        String secNum = "";
        
        System.out.println("Please enter total credit payment or enter 'total' to pay the whole balance: of type cancel");
        do{
            try{
                input = scanner.next();
                if(input.equalsIgnoreCase("total"))
                    payment = sale.getSaleTotal();
                else                                                                                                             if(input.equals("cancel"))
                    return;
                else{
                    payment = Float.parseFloat(input);
                }
            }catch(Exception e){
                System.out.println("Invalid payment.");
            }
        }while(payment > leftToPay);
        
        try{
            System.out.println("Please enter card number or type cancel: ");
            cardNum = scanner.next();
            if(input.equals("cancel"))
                return;

            System.out.println("Please enter security code or type cancel: ");
            input = scanner.next();
            if(input.equals("cancel"))
                return;
            secNum = input;
        }catch(Exception e){
            System.out.println("Invalid input.");
        }
        
        CreditPayment credit = new CreditPayment(cardNum, secNum, payment);
        accepted = processCreditPayment(credit);
        if(accepted){
            sale.addPayment(credit);
            leftToPay -= payment;
        }
        else{
            System.out.println("Card rejected.");
        }
    }
    
    private boolean processCreditPayment(CreditPayment payment){
        String cardNum = payment.getCardNum();
        String secNum = payment.getSecurityCode();
        if(cardNum.length() == 16 && secNum.length() == 3)
            return true;
        return false;
    }
    private void processDebitPayment(){
                float payment = 0;
        boolean invalid = true;
        boolean accepted; //for payment
        String cardNum = "";
        int pin = 0;
        
        System.out.println("Please enter total credit payment or enter 'total' to pay the whole balance: ");
        
        try{
            input = scanner.next();
            if(input.equalsIgnoreCase("total"))
                payment = sale.getSaleTotal();
            else{
                payment = Float.parseFloat(input);
            }
            invalid = false;
        }catch(Exception e){
            System.out.println("Invalid payment.");
        }
        
        try{
            System.out.println("Please enter card number or type cancel: ");
            cardNum = scanner.next();
            if(input.equals("cancel"))
                return;

            System.out.println("Please enter pin or type cancel: ");
            input = scanner.next();
            if(input.equals("cancel"))
                return;
            pin = Integer.parseInt(input);                
        }catch(Exception e){
            System.out.println(e.getStackTrace() + "/nInvalid input.");
        }
        
        DebitPayment debit = new DebitPayment(cardNum, pin, payment);
        accepted = processDebitPayment(debit);
        if(accepted){
            sale.addPayment(debit);
            leftToPay-=payment;
        }
        else{
            System.out.println("Card rejected.");
        }
    }
    private boolean processDebitPayment(DebitPayment payment){
        String cardNum = "" + payment.getCardNum();
        String pin = "" + payment.getPin();
        if(cardNum.length() == 16 && pin.length() == 4)
            return true;
        return false;
    }
    
    private void processVoid(){
        System.out.print("Please enter a product code: ");
        int code = scanner.nextInt();
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        sale.removeItem(product);
    }
    
    private void processProduct(int code){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        sale.addItem(product);
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
        
        sale.addCoupon(new Coupon(code, productCode, amount));
    }
    
    private void displaySale(){
        System.out.println(sale);
    }
    private void printReceipt(){
        displaySale();
        sale.printTotals();
        ArrayList<Payment> payments = sale.getPayments();
        for(int i = 0; i < payments.size(); i++){
            System.out.print(payments.get(i));
        }
    }
}
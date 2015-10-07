/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package process.sale.prototypes;

import java.text.DecimalFormat;
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
        }
        // Thank customer, and close
        System.out.println("\nThank for you shopping with us. Have a nice day!");
        System.exit(0);
    }
    
    private void processCashPayment(){
        float payment = 0;
        boolean invalid = true;
        while(invalid){
            System.out.println("Please enter total cash payment: ");
            try{
                payment = scanner.nextFloat();
                invalid = false;
            }catch(Exception e){
                System.out.println("Invalid payment.");
            }
        }
        if(payment > sale.getSaleTotal()){
            System.out.printf("Your change is $%.2d", sale.getSaleTotal());
            sale.addPayment(new CashPayment(payment, sale.getSaleTotal()));
            leftToPay-=sale.getSaleTotal();
        }
        else if(payment == sale.getSaleTotal()){
            sale.addPayment(new CashPayment(payment, sale.getSaleTotal()));
            leftToPay-=sale.getSaleTotal();
        }
        else{
            sale.addPayment(new CashPayment(payment, sale.getSaleTotal()-payment));
            leftToPay-=payment;
        }
    }
    
    private void processCreditPayment(){
        float payment = 0;
        boolean invalid = true;
        boolean accepted; //for payment
        String cardNum = "";
        String secNum = "";
        
        while(invalid){
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
        }
        
        invalid = true;
        while(invalid){
            try{
                System.out.println("Please enter card number or type cancel: ");
                input = scanner.next();
                if(input.equals("cancel"))
                    return;
                cardNum = input; // Integer.parseInt(input);
                
                System.out.println("Please enter security code or type cancel: ");
                input = scanner.next();
                if(input.equals("cancel"))
                    return;
                secNum = input;// Integer.parseInt(input);   
                
                // TODO: Put in conditions for a valid credit card number
                // Assuming everything is good to go...
                invalid = false;
            }catch(Exception e){
                System.out.println("Invalid input.");
            }
        }
        
        CreditPayment credit = new CreditPayment(cardNum, secNum, payment);
        accepted = processCreditPayment(credit);
        if(accepted){
            sale.addPayment(credit);
            leftToPay-=payment;
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
        int cardNum = 0;
        int pin = 0;
        
        while(invalid){
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
        }
        
        invalid = true;
        while(invalid){
            try{
                System.out.println("Please enter card number or type cancel: ");
                input = scanner.next();
                if(input.equals("cancel"))
                    return;
                cardNum = Integer.parseInt(input);
                
                System.out.println("Please enter pin or type cancel: ");
                input = scanner.next();
                if(input.equals("cancel"))
                    return;
                pin = Integer.parseInt(input);                
            }catch(Exception e){
                System.out.println(e.getStackTrace() + "/nInvalid input.");
            }
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
}
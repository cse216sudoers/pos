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
     * Present basic options to the user for a sale and start processing input
     */
    @Override
    public void start(){
        
        //continuous Sale loop
        boolean done = false;
        while(!done){
            try{
                System.out.print("Please enter 'void', 'coupon', <code>, 'override', 'suspend', or 'close': ");
                scanner = new Scanner(System.in);
                input = scanner.next();
                //void item
                if(input.equalsIgnoreCase("void")){
                    processVoid();
                    display();
                }
                //coupon
                else if(input.equalsIgnoreCase("coupon")){
                    processCoupon();
                    display();
                }
                //override
                else if(input.equalsIgnoreCase("override")){
                    // prompt for admin credentials
                    // this is terrible program design
                    System.out.print("Admin username: ");
                    input = scanner.next();
                    Cashier temp = CashierManager.getInstance().getCashierByUsername(input);
                    if (temp.getAccess() == Cashier.Access.Admin || temp.getAccess() == Cashier.Access.Manager) {
                        System.out.print("Password: ");
                        input = scanner.next();
                        if (temp.getPassword().equals(input)) {
                            processOverride();
                        }
                    }
                }
                //add item to Sale
                else if (input.charAt(0) >= '0' && input.charAt(0) <= '9'){
                    processProduct(Integer.parseInt(input));
                    display();
                }
                //end Sale
                else if (input.equalsIgnoreCase("close")){
                    // Close Sale and get payment
                    close();
                    done = true;
                }
                //suspend Sale
                else if (input.equalsIgnoreCase("suspend")){
                    // Close Sale and get payment
                    processSuspend();
                    done = true; //Need to quit sale after suspending
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
     * Suspend the sale
     */
    @Override
    protected void processSuspend(){
        SaleManager.getInstance().addSuspendedSale(sale);
    }
    
    /**
     * Close out the sale and take payments
     */
    @Override
    protected void close() {
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
    }
    
    /**
     * Create a cash payment
     */
    protected void processCashPayment(){
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
            sale.addPayment(new CashPayment(payment, payment));
            leftToPay-=payment;
        }
        else{
            sale.addPayment(new CashPayment(payment, payment));
            leftToPay-=payment;
        }
    }
    
    /**
     * Create a Credit payment
     */
    protected void processCreditPayment(){
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
                    payment = leftToPay;
                else if(input.equals("cancel"))
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
    protected void processDebitPayment(){
                float payment = 0;
        boolean invalid = true;
        boolean accepted; //for payment
        String cardNum = "";
        int pin = 0;
        
        System.out.println("Please enter total credit payment or enter 'total' to pay the whole balance: ");
        
        try{
            input = scanner.next();
            if(input.equalsIgnoreCase("total"))
                payment = leftToPay;
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
    @Override
    protected void processVoid(){
        System.out.print("Please enter a product code: ");
        int code;
        try{
            code = scanner.nextInt();
        }catch(Exception e){
            System.out.println("Invalid input");
            return;
        }
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        sale.removeItem(product);
    }
    
    /**
     * Add a product to the sale
     * @param code product to add
     */
    @Override
    protected void processProduct(int code){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        
        if(product == null){ //product does not exist
            System.out.println("Invalid product code: " + code);
            return;
        }
        sale.addItem(product);
    }
    
    //coupon to add to sale
    private void processCoupon(){
        String next = "";
        int code;
        try{
            System.out.print("Please enter coupon code: ");
            next = scanner.next();
            code = Integer.parseInt(next);
        }catch(Exception e){
            System.out.println("Invalid code: " + next);
            return;
        }
       System.out.println("monkey");
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
        
        sale.getLineItemByCode(productCode).setPrice(amount);
    }
    
    /**
     * Display the sale as it would be displayed on a monitor
     */
    @Override
    protected void display(){
        System.out.println(sale);
    }
    /**
     * Print the receipt
     */
    @Override
    protected void printReceipt(){
        System.out.print("******************************************");
        sale.printTotals();
        ArrayList<Payment> payments = sale.getPayments();
        for(int i = 0; i < payments.size(); i++){
            System.out.print(payments.get(i));
        }
        System.out.println("\n******************************************");
    }
}
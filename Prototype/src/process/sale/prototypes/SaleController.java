/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package process.sale.prototypes;

import java.util.Scanner;

/**
 *
 * @author Pikachu
 */
public class SaleController {
    private Sale sale;
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
                if(input.equals("void")){
                    processVoid();
                }
                //coupon
                else if(input.equals("coupon")){
                    processCoupon();
                }
                //override
                else if(input.equals("override")){
                    //manager override
                }
                //add item to sale
                else if (input.charAt(0) >= '0' && input.charAt(0) <= '9'){
                    processProduct(Integer.parseInt(input));
                }
                //end sale
                else if (input.equals("close")){
                    break;
                }
                else{
                    System.out.println("Invalid input: " + input);
                }
                displaySale();
            }catch(Exception e){
                System.out.println(e.toString());
                System.out.println("Invalid input: " + input);
            }
            //TODO ending sale and payment stuffs
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
        System.out.print("Please enter coupon amount: ");
        String next = scanner.next();
        int code;
        float amount;
        //System.out.println("In coupon ");
        if(next.contains(".")){
            amount = Float.parseFloat(next);
        }
        else{
            System.out.println("Invalid amount: " + next);
            return;
        }
        try{
            System.out.print("Please enter item code: ");
            next = scanner.next();
            code = Integer.parseInt(next);
        }catch(Exception e){
            System.out.println("Invalid code: " + next);
            return;
        }
        sale.addCoupon(new Coupon(code, amount));
    }
    
    private void displaySale(){
        System.out.println(sale);
    }
}

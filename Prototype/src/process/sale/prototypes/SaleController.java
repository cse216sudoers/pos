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
    
    public SaleController(){
        sale = new Sale();
    }
    
    public void startSale(){
        while(true){
            try{
                scanner = new Scanner(System.in);
                String input = scanner.next();
                input = input.trim();
                if(input.equals("void")){
                    processVoid(input);
                }
                else if (input.charAt(0) >= '0' && input.charAt(0) <= '9'){
                    processProduct(Integer.parseInt(input));
                }
                else{
                    System.out.println("Invalid input");
                }
            }catch(Exception e){
                System.out.println("Invalid input");
            }
            //TODO ending sale and payment stuffs
        }
    }
    
    private void processVoid(String input){
      int code = scanner.nextInt();
      ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
      sale.removeItem(product);
    }
    private void processProduct(int code){
        ProductDescription product = ProductCatalog.getCatalog().findProductByCode(code);
        sale.addItem(product);
    }
}

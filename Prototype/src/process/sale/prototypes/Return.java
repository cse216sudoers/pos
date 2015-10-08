/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.ArrayList;

/**
 *
 * @author Pikachu
 */
public class Return {
    private float total;
    private float returnTotal;
    private int saleId;
    private ArrayList<LineItem> lines;
    private ArrayList<Payment> returnPayments; //for when we need to do returns
    private int id;
    
    public Return(int saleId){
        total = 0;
        this.saleId = saleId;
        id = RentalManager.getInstance().getNextId();
        returnPayments = new ArrayList<Payment>();
        lines = new ArrayList<LineItem>();
    }
    
    public int getSaleId(){
        return saleId;
    }
    public float getTotal(){
        return total;
    }
    public float getReturnTotal(){
        return returnTotal;
    }
    public void addReturnPayment(Payment payment){
        returnPayments.add(payment);
    }
    public ArrayList<LineItem> getLines(){
        return lines;
    }
    
    public boolean addItem(ProductDescription product){
        LineItem item = SaleManager.getInstance().getSaleById(saleId).getLineItemByCode(product.getCode());
        
        if(item == null){
            return false; //means can't return item becasue not found
        }
        LineItem retItem = getLineItemByCode(product.getCode());
        if(retItem == null){
            retItem = (new LineItem(product));
            
            if(item.getQuantity() == 1 && item.getCoupon() != null){
                retItem.setCoupon(item.getCoupon());
                total += item.getPriceWithCoupons();                
            }
            else{
                total += product.getPrice();
            }
            
            lines.add(retItem);
            return true;
        }
        if(retItem.getQuantity() < item.getQuantity())
        {
            retItem.increaseQuantity();
             if(item.getQuantity() == retItem.getQuantity() && item.getCoupon() != null){
                 retItem.setCoupon(item.getCoupon());
                total += item.getPriceWithCoupons();
            }
             else{
                total += product.getPrice();
             }
            return true;
        }
        return false; //already returned all of that item
    }
    
    public void removeItem(ProductDescription product){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == product.getCode()){
                if(lines.get(i).getQuantity() == 1){
                    lines.remove(i);
                }
                else{
                    lines.get(i).decreaseQuantity();
                }
                total-=product.getPrice();
                found = true;
                break;
            }
        }
        if(!found){//item not in return
            System.out.println("item not found");
        }
    }
    
    public ArrayList<Payment> getPayments(){
        return returnPayments;
    }
    
    public int getId(){
        return id;
    }
    public LineItem getLineItemByCode(int code){
        for(int i = 0; i< lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == code)
                return lines.get(i);
        }
        return null;
    }
    public void printTotals() {
        // Calculate tax and total
        float tax = TaxCalculator.getTax(getTotal());
        returnTotal = getTotal() + tax;
        
        // Set up ability to format print statements right so everything aligns
        int digits = ((Float) returnTotal).toString().length();
        String format = "%" + digits + ".2f";
        
        System.out.println("\n" + toString());
        System.out.printf("Subtotal: $" + format + "\n", getTotal());
        System.out.printf("Tax:      $" + format + "\n", tax);
        System.out.printf("Total:    $" + format + "\n", returnTotal);
    }
    
    @Override
    public String toString(){
        String output = "******Return******* \nSale ID: " + saleId + "\nReturn ID: " + id + "\n";
        for(int i = 0; i < lines.size(); i++){
            output += lines.get(i).toString();
        }
        return output;
    }
}

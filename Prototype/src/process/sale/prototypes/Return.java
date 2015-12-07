/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Return
 * @author Pikachu
 */
public class Return extends Transaction{
    private int saleId;
    
    /**
     *
     * @param saleId
     */
    public Return(int saleId){
        total = 0;
        subTotal = 0;
        this.saleId = saleId;
        id = RentalManager.getInstance().getNextId();
        payments = new ArrayList<>();
        lines = new ArrayList<>();
    }
    
    /**
     *
     * @return
     */
    public int getSaleId(){
        return saleId;
    }

    /**
     *
     * @param product
     * @return
     */
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
                subTotal += item.getPriceWithCoupons();                
            }
            else{
                subTotal += product.getPrice();
            }
            product.increaseQuantity();
            
            lines.add(retItem);
            return true;
        }
        if(retItem.getQuantity() < item.getQuantity())
        {
            retItem.increaseQuantity();
             if(item.getQuantity() == retItem.getQuantity() && item.getCoupon() != null){
                 retItem.setCoupon(item.getCoupon());
                subTotal += item.getPriceWithCoupons();
            }
             else{
                subTotal += product.getPrice();
             }
            return true;
        }
        return false; //already returned all of that item
    }
    
    /**
     *
     * @param product
     */
    public boolean removeItem(ProductDescription product){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == product.getCode()){
                if(lines.get(i).getQuantity() == 1){
                    lines.remove(i);
                }
                else{
                    lines.get(i).decreaseQuantity();
                }
                subTotal-=product.getPrice();
                product.decreaseQuantity();
                found = true;
                break;
            }
        }
        if(!found){//item not in Sale
            JOptionPane.showMessageDialog (null, "Item not found", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            System.out.println("item not found");
            return false;
        }
        return true;
    }
    
    /**
     *
     * @param code
     * @return
     */
    public LineItem getLineItemByCode(int code){
        for(int i = 0; i< lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == code)
                return lines.get(i);
        }
        return null;
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

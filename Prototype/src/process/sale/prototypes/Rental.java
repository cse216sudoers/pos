/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 * Rental Domain object 
 * @author Pikachu
 */
public class Rental extends Transaction{
    ArrayList<RentalReturn> returns;
    GregorianCalendar startDate;
    
    /**
     *Initialize rental
     */
    public Rental(){
        total = 0;
        id = RentalManager.getInstance().getNextId();
        payments = new ArrayList<>();
        lines = new ArrayList<>();
        returns = new ArrayList<>();
    }
    public Rental(int id){
        total = 0;
        this.id = id;
        payments = new ArrayList<>();
        lines = new ArrayList<>();
        returns = new ArrayList<>();

    
    }
    
    /**
     * Add a RentalReturn
     * @param ret 
     */
    public void addRentalreturn(RentalReturn ret){
        returns.add(ret);
        for(int i = 0; i < lines.size(); i++){
            RentalReturnLineItem retItem = (RentalReturnLineItem)ret.getLines().get(i);
            int quantity = retItem.getQuantity();
            while(quantity > 0){
                removeItem(retItem.getProduct(), retItem.getDaysRented(), false);
                quantity--;
            }
        }
        RentalManager.getInstance().updateFile();
        ProductCatalog.getCatalog().updateFile();
    }
    public GregorianCalendar getRentalDate(){
        return startDate;
    }
    /**
     * Add item to rental
     * @param product product rented
     * @param daysRented days rented for
     */
    public void addItem(ProductDescription product, int daysRented, boolean affectQuantity){
        LineItem lineItem = getLineItemByCodeAndDaysRented(product.getCode(), daysRented);
        if(lineItem!=null){
            lineItem.increaseQuantity();
            subTotal += ((RentalLineItem)lineItem).getRentalPrice();
            product.decreaseQuantity();
        } 
        else{
            lines.add(new RentalLineItem(product, daysRented));
            product.decreaseQuantity();
            subTotal += ((RentalLineItem)lines.get(lines.size()-1)).getRentalPrice();
        }
    }
    
    /**
     * add a coupon to the rental
     * @param coupon
     */
    public void addCoupon(Coupon coupon){
        boolean found = false;
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == coupon.getProductCode()){
                lines.get(i).setCoupon(coupon);
                subTotal-=coupon.getAmount();
                found = true;
                break;
            }
        }
        if(!found){
            JOptionPane.showMessageDialog (null, "Invalid coupon "+ coupon.getCode(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public String toString(){
        String output = "******Rental******* \nRental ID: " + id + "\n";
        for(int i = 0; i < lines.size(); i++){
            output += lines.get(i).toString();
        }
        return output;
    }

    /**
     * Remove item from rental
     * @param product item to remove
     */
    public boolean removeItem(ProductDescription product, int days, boolean affectQuantity){
        LineItem lineItem = getLineItemByCodeAndDaysRented(product.getCode(), days);
        if(lineItem != null){
            if(lineItem.getQuantity() == 1){
                lines.remove(lineItem);
            }
            else{
                lineItem.decreaseQuantity();
            }
            total-=product.getRentalPrice();
        }else{//item not in Sale
            JOptionPane.showMessageDialog (null, "Item not found", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            System.out.println("item not found");
            return false;
        }
        return true;
    }
    public LineItem getLineItemByCodeAndDaysRented(int code, int daysRented){
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).getProduct().getCode() == code && ((RentalLineItem)lines.get(i)).getDaysRented() == daysRented){
                return lines.get(i);
            }
        }
        return null;
    }
}
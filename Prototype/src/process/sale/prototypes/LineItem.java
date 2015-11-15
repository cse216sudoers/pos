/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Jeff
 */
public class LineItem {
    /**
     * How much of the item is in the transaction
     */
    protected int quantity;
    /**
     * product
     */
    protected ProductDescription product;
    /**
     * total price of all these items
     */
    protected float price;
    /**
     * coupon applied 
     */
    protected Coupon coupon;
    
    /**
     * Create line item for product
     * @param product
     */
    public LineItem(ProductDescription product){
        quantity = 1;
        this.product = product;
        price = product.getPrice();
        coupon = null;
    }
    /**
     *
     * @return product
     */
    public ProductDescription getProduct(){
        return product;
    }
    /**
     * price without coupons
     * @return total 
     */
    public float getPrice() {
        return price;
    }
    /**
     * get price with coupons
     * @return
     */
    public float getPriceWithCoupons(){
        return price - coupon.apply();
    }
    /**
     * get number of items
     * @return
     */
    public int getQuantity(){
        return quantity;
    }
    /**
     * Increase quantity by one
     */
    public void increaseQuantity(){
        quantity++;
    }
    /**
     * Decrease quantity by one
     */
    public void decreaseQuantity(){
        quantity--;
    }
    /**
     * Increase quantity by multiple
     * @param increase number items to add
     */
    public void increaseQuantity(int increase){
        quantity -= increase;        
    }
    /**
     * decrease quantity by multiple
     * @param decrease number items to remove
     */
    public void decreaseQuantity(int decrease){
        quantity--;
        if(quantity < 0)
            quantity = 0;
    }
    /**
     * Set coupon applied
     * @param coupon
     */
    public void setCoupon(Coupon coupon){
        this.coupon = coupon;
    }
    /**
     * 
     * @return coupon
     */
    public Coupon getCoupon(){
        return coupon;
    }
    /**
     *
     * @param newPrice
     */
    public void setPrice(float newPrice) {
        price = newPrice;
    }
    
    @Override
    public String toString(){
        String output = product.toString() + "\n";
        if (price != product.getPrice())
            output += "*** Price overridden to " + price + "\n";
        if(quantity >1)
            output += String.format("%2s %-15.15s\t%7.2f\n", "", "X" + quantity, quantity * price);
        if(coupon != null)
            output += coupon.toString() + "\n";
        return output;
    }
}

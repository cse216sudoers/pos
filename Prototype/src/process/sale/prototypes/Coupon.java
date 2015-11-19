/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 * Keeps track of Coupon for a specific item
 * @author Pikachu
 */
public class Coupon {
    private int code; //coupon code
    private int productCode; //product to be applied to
    private float amount; //amount to take off product total
    private boolean percent; //true if amount off a percentage
    
    /**
     * Creates a new coupon
     * @param code
     * @param productCode
     * @param amount
     * @param percent
     */
    public Coupon(int code, int productCode, float amount,boolean percent){
        this.code =code;
        this.productCode = productCode;
        this.amount = amount;
        this.percent=percent;
    }
    /**
     * Creates a new coupon
     * @param code
     * @param productCode
     * @param amount
     */
    public Coupon(int code, int productCode, float amount){
        this.code =code;
        this.productCode = productCode;
        this.amount = amount;
        this.percent=false;
    }
    /**
     *
     * @return discount amount (either a percentage or dollar amount)
     */
    public float getAmount(){
        return amount;
    }
    /**
     * Set amount (either a percentage or dollar amount
     * @param amount
     */
    public void setAmount(float amount){
        this.amount = amount;
    }
    /**
     *
     * @return coupon code
     */
    public int getCode(){
        return code;
    }

    /**
     * Get discount for item
     * @return discount
     */
    public float apply(){
        float drop;
        if(percent){
            drop=ProductCatalog.getCatalog().findProductByCode(productCode).getPrice()*(amount/100);
        }
        else
            drop =  amount;
        return drop;
    }
    /**
     * Get product code
     * @return
     */
    public int getProductCode(){
        return productCode;
    }

    @Override
    public String toString(){
        if(!percent){
            return String.format("%2s %16d\t-%7.2f", " ", code, apply());
        }
        return String.format("%2s %16d\t-%7.2f\t%3.2f%%", " ", code, apply(), amount);
    }
}

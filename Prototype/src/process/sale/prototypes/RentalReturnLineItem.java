/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public class RentalReturnLineItem extends LineItem{
    private boolean onTime;
    private int daysLate;
    private float lateFee;

    /**
     *
     * @param product
     */
    public RentalReturnLineItem(ProductDescription product){
        super(product);
        onTime = true;
        lateFee = 0;
    }
    
    /**
     *
     * @param product
     * @param lateFee
     */
    public RentalReturnLineItem(ProductDescription product, int daysLate){
        super(product);
        onTime = false;
        this.daysLate = daysLate;
        lateFee = getOneDayLateFee()*daysLate;
    }
    
    /**
     *
     * @return
     */
    public boolean isOnTime() {
        return onTime;
    }

    /**
     *
     * @return
     */
    public int getDaysLate() {
        return daysLate;
    }
    
    /**
     *
     * @return
     */
    public float getLateFee(){
        return lateFee;
    }

    /**
     *
     * @return
     */
    private float getOneDayLateFee() {
        return product.getRentalPrice() * 2;
    }

    /**
     *
     * @param onTime
     */
    public void setOnTime(boolean onTime) {
        this.onTime = onTime;
    }
    
    @Override
    public String toString(){
       String output =  String.format("%2s %-15.15s", product.getCode(), product.getDescription());
        if(quantity >1)
            output += String.format("%3s", "", "X" + quantity);
        if(onTime)
            output += "\tOn time\n";
        else
            output += String.format("\t%5d Late \t Laenalty: $%4.2f\n", daysLate,lateFee);
        return output;
    }
}

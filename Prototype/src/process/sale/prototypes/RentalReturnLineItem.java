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

    public RentalReturnLineItem(ProductDescription product){
        super(product);
        onTime = true;
        lateFee = 0;
    }
    
    public RentalReturnLineItem(ProductDescription product, float lateFee){
        super(product);
        onTime = false;
        this.lateFee = lateFee;
    }
    
    public boolean isOnTime() {
        return onTime;
    }

    public int getDaysLate() {
        return daysLate;
    }

    public float getLateFee() {
        return product.getRentalPrice() * 2 * daysLate;
    }

    public void setOnTime(boolean onTime) {
        this.onTime = onTime;
    }

    public void setDaysLate(int daysLate) {
        this.daysLate = daysLate;
    }
    
    @Override
    public String toString(){
       String output =  String.format("%2s %-15.15sdays rented: %5d\t$%7.2f\n", product.getCode(), product.getDescription());
        if(quantity >1)
            output += String.format("%3s", "", "X" + quantity);
        if(onTime)
            output += "\tOn time";
        else
            output += String.format("\t%5d Late \t Late Penalty: $%4.2f", daysLate,lateFee);
        return output;
    }
}

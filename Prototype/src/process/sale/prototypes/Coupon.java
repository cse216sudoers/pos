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
public class Coupon {
    private int code;
    private float amount;
    
    public Coupon(int code, float amount){
        this.code =code;
        this.amount = amount;
    }
    
    public float getAmount(){
        return amount;
    }
    
    public void setAmount(float amount){
        this.amount = amount;
    }
    
    public int getCode(){
        return code;
    }
    
    public void setCode(int code){
        this.code = code;
    }
    
    @Override
    public String toString(){
        return "" + code + "\t -" + amount + "\n";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public class Coupon {
    private int code;
    private int productCode;
    private float amount;
    private boolean percent;
    
    public Coupon(int code, int productCode, float amount,boolean percent){
        this.code =code;
        this.productCode = productCode;
        this.amount = amount;
        this.percent=percent;
    }
    public Coupon(int code, int productCode, float amount){
        this.code =code;
        this.productCode = productCode;
        this.amount = amount;
        this.percent=false;
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
    public float apply(float total){
        float drop;
        //if(total>prereq){
          if(percent){
              drop=total*amount/100;
          }  
          drop =  amount;
          
        //}
        
        return drop;
    }
    public int getProductCode(){
        return productCode;
    }
    public void setProductCode(int productCode){
        this.productCode = productCode;
    }
    @Override
    public String toString(){
        return String.format("%2s %-16d\t-%7.2f", " ", code, amount);
    }
}

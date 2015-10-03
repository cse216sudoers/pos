/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public class Item {
    String productCode;
    float price;
    ProductDescription productDescription;
    
    public ProductDescription getProductDescription(){
        return productDescription;
    }
    public float getPrice(){
        return price;
    }
    public String getProductCode(){
        return productCode;
    }
}

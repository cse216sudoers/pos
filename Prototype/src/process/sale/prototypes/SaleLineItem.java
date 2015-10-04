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
public class SaleLineItem {
    private int quantity;
    private ProductDescription product;
    
    public SaleLineItem(ProductDescription product){
        quantity = 1;
        this.product = product;
    }
    public ProductDescription getProduct(){
        return product;
    }
    public int getQuantity(){
        return quantity;
    }
    public void increaseQuantity(){
        quantity++;
    }
    public void decreaseQuantity(){
        quantity--;
    }
}

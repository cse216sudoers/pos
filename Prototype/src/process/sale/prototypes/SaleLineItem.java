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
    private Item item;
    private int quantity;
    
    public SaleLineItem(Item item){
        quantity = 1;
        this.item = item;
    }
    public Item getItem(){
        return item;
    }
    public int getQuantity(){
        return quantity;
    }
    public void increaseQuantity(){
        quantity++;
    }
}

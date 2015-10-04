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
public class SaleManager {
    private ArrayList<Sale> sales;
    private static SaleManager instance;
    
    private SaleManager(){
    }
    
    public static synchronized SaleManager getInstance(){
        if (instance == null){
            instance = new SaleManager();
        }
        return instance;
    }
    
    public Sale getSaleById(int id){
        for(int i = 0; i < sales.size(); i++){
            if(sales.get(i).getId() == id)
                return sales.get(i);
        }
        return null;
    }
    
    public ArrayList<Sale> getSales(){
        return sales;
    }
    
    public void addSale(Sale sale){
        sales.add(sale);
    }
}

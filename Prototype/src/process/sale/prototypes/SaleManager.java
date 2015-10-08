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
    private ArrayList<Sale> suspendedSales;
    private int nextId = 0;
    private static SaleManager instance;
    
    private SaleManager(){
        sales = new ArrayList<Sale>();
        suspendedSales = new ArrayList<Sale>();
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
    
    public ArrayList<Sale> getSuspendedSales(){
        return suspendedSales;
    }
   
    
    public void addSale(Sale sale){
        sales.add(sale);
    } 
    
    public void addSuspendedSale(Sale sale){
        suspendedSales.add(sale);
    }
    public int getNextId(){
        return ++nextId;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.HashMap;

/**
 *
 * @author Pikachu
 */
public class SaleManager {
    private HashMap<Integer, Sale> sales;
    private HashMap<Integer, Sale> suspendedSales;
    private int nextId = 0;
    private static SaleManager instance;
    
    private SaleManager(){
        sales = new HashMap(89);
        suspendedSales = new HashMap(89);
    }
    
    public static synchronized SaleManager getInstance(){
        if (instance == null){
            instance = new SaleManager();
        }
        return instance;
    }
    
    public Sale getSaleById(int id){
        return sales.get(id);
    }
    
    public Sale getSuspendedSaleById(int id){
        return suspendedSales.get(id);
    }
    
    public HashMap<Integer, Sale> getSales(){
        return sales;
    }
    
    public HashMap<Integer, Sale> getSuspendedSales(){
        return suspendedSales;
    }
    
    public void addSale(Sale sale){
        sales.put(sale.getId(), sale);
    } 
    
    public void addSuspendedSale(Sale sale){
        suspendedSales.put(sale.getId(), sale);
    }
    public int getNextId(){
        return ++nextId;
    }
}

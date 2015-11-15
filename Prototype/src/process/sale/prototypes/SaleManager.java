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
    
    /**
     *
     * @return
     */
    public static synchronized SaleManager getInstance(){
        if (instance == null){
            instance = new SaleManager();
        }
        return instance;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Sale getSaleById(int id){
        return sales.get(id);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Sale getSuspendedSaleById(int id){
        return suspendedSales.get(id);
    }
    
    /**
     *
     * @return
     */
    public HashMap<Integer, Sale> getSales(){
        return sales;
    }
    
    /**
     *
     * @return
     */
    public HashMap<Integer, Sale> getSuspendedSales(){
        return suspendedSales;
    }
    
    /**
     *
     * @param sale
     */
    public void addSale(Sale sale){
        sales.put(sale.getId(), sale);
    } 
    
    /**
     *
     * @param sale
     */
    public void addSuspendedSale(Sale sale){
        suspendedSales.put(sale.getId(), sale);
    }
    /**
     *
     * @return
     */
    public int getNextId(){
        return ++nextId;
    }
}

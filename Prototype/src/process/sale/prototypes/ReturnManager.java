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
public class ReturnManager {
    private HashMap<Integer, Return> returns;
    private HashMap<Integer, Return> suspendedReturns;
    private int nextId = 0;
    private static ReturnManager instance;
    
    private ReturnManager(){
        returns = new HashMap(89);
        suspendedReturns = new HashMap(89);
    }
    
    /**
     *
     * @return
     */
    public static synchronized ReturnManager getInstance(){
        if (instance == null){
            instance = new ReturnManager();
        }
        return instance;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Return getReturnById(int id){
        return returns.get(id);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Return getSuspendedReturnById(int id){
        return suspendedReturns.get(id);
    }
    
    /**
     *
     * @return
     */
    public HashMap<Integer, Return> getReturns(){
        return returns;
    }
    
    /**
     *
     * @return
     */
    public HashMap<Integer, Return> getSuspendedreturns(){
        return suspendedReturns;
    }
   
    /**
     *We add the return to the return array and then subtract returned items from the sale
     * @param ret
     */
    public void addReturn(Return ret){
        returns.put(ret.getId(), ret);
        Sale sale = SaleManager.getInstance().getSaleById(ret.getSaleId());
        //update all item quantities in sale
        for(int i = 0; i < ret.getLines().size(); i++){
            LineItem retItem = ret.getLines().get(i);
            int quantity = retItem.getQuantity();
            while(quantity > 0){
                sale.removeItem(retItem.product, false);
                quantity--;
            }
        }
        //Time to check payments and update them
        for(int i = 0; i < ret.getPayments().size()-1; i++){
            sale.getPayments().remove(0);//remove payment because it was returned
        }
        
        //The last payment may only have been partially returned
        float amountReturned = ret.getPayments().get(ret.getPayments().size()-1).getAmount();
        float amountPaid = sale.getPayments().get(0).getAmount();
        if(amountReturned < amountPaid)
            sale.getPayments().get(0).setAmount(amountPaid-amountReturned);
        else
            sale.getPayments().remove(0);
    }
    
    /**
     *
     * @param ret
     */
    public void addSuspendedReturn(Return ret){
        suspendedReturns.put(ret.getId(),ret);
    }
    /**
     *
     * @return
     */
    public int getNextId(){
        return ++nextId;
    }
}

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
     *
     * @param ret
     */
    public void addReturn(Return ret){
        returns.put(ret.getId(), ret);
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

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
    
    public static synchronized ReturnManager getInstance(){
        if (instance == null){
            instance = new ReturnManager();
        }
        return instance;
    }
    
    public Return getReturnById(int id){
        return returns.get(id);
    }
    
    public Return getSuspendedReturnById(int id){
        return suspendedReturns.get(id);
    }
    
    public HashMap<Integer, Return> getReturns(){
        return returns;
    }
    
    public HashMap<Integer, Return> getSuspendedreturns(){
        return suspendedReturns;
    }
   
    public void addReturn(Return ret){
        returns.put(ret.getId(), ret);
    } 
    
    public void addSuspendedReturn(Return ret){
        suspendedReturns.put(ret.getId(),ret);
    }
    public int getNextId(){
        return ++nextId;
    }
}

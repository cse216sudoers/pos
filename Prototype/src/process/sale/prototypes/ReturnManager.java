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
public class ReturnManager {
        private ArrayList<Return> returns;
    private ArrayList<Return> suspendedReturns;
    private int nextId = 0;
    private static ReturnManager instance;
    
    private ReturnManager(){
        returns = new ArrayList<Return>();
        suspendedReturns = new ArrayList<Return>();
    }
    
    public static synchronized ReturnManager getInstance(){
        if (instance == null){
            instance = new ReturnManager();
        }
        return instance;
    }
    
    public Return getReturnById(int id){
        for(int i = 0; i < returns.size(); i++){
            if(returns.get(i).getId() == id)
                return returns.get(i);
        }
        return null;
    }
    
    public ArrayList<Return> getReturns(){
        return returns;
    }
    
    public ArrayList<Return> getSuspendedreturns(){
        return suspendedReturns;
    }
   
    
    public void addReturn(Return ret){
        returns.add(ret);
    } 
    
    public void addSuspendedReturn(Return ret){
        suspendedReturns.add(ret);
    }
    public int getNextId(){
        return ++nextId;
    }
}

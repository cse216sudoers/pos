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
public class CashierManager {
    private ArrayList<Cashier> cashiers;
    
    public CashierManager(ArrayList<Cashier> cashiers){
        this.cashiers = cashiers;
    }
    
    public void addCashier(Cashier cashier){
        cashiers.add(cashier);
    }
    
    public void removeCashier(Cashier cashier){
        cashiers.remove(cashier);
    }
    
    public ArrayList<Cashier> getCashiers(){
        return cashiers;
    }
    public Cashier getCashierByUsername(String username){
        for(int i = 0; i < cashiers.size(); i++){
            if(cashiers.get(i).getUsername().equals(username))
                return cashiers.get(i);
        }
        return null;
    }
}

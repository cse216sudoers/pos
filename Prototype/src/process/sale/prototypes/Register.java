
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public class Register {
    private int id;
    private Cashier currentCashier;
    
    /**
     * Create a register with a specific ID
     * @param id
     */
    public Register(int id){
        currentCashier = null;
        this.id = id;
    }
   
    /**
     * Log off of the register
     * @return
     */
    public boolean logOffRegister(){
        currentCashier = null;
        return true; //successful (idk if we will have unsuccessful cases later)
    }
    
    /**
     * get ID of register
     * @return
     */
    public int getId(){
        return id;
    }
    
    /**
     * Get cashier on register
     * @return
     */
    public Cashier getCashier(){
        return currentCashier;
    }
    
    /**
     * Set current cashier on register
     * @param cashier
     */
    public void setCashier(Cashier cashier){
        currentCashier = cashier;
    }
}

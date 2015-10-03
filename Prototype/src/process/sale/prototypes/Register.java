
package process.sale.prototypes;

public class Register {
    private int id;
    private Cashier currentCashier;
    
    public boolean logOffRegister(){
        currentCashier = null;
        return true; //successful (idk if we will have unsuccessful cases later)
    }
    public void logOnRegister(String username, String password) {
        
    }
}

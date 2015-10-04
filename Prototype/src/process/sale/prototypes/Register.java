
package process.sale.prototypes;

public class Register {
    private int id;
    private Cashier currentCashier;
    private CashierManager cashierManager;
    private Sale currentSale;
    
    public Register(){
        cashierManager = CashierManager.getInstance();
        currentCashier = null;
        currentSale = null;
    }
    public boolean logOffRegister(){
        currentCashier = null;
        return true; //successful (idk if we will have unsuccessful cases later)
    }
    public void logOnRegister(String username, String password) {
        Cashier cashier = cashierManager.getCashierByUsername(username);
        if(password.equals(cashier.getPassword())){
            currentCashier = cashier;
        }
    }
    public void startSale(){
        currentSale = new Sale();
    }
    
    public int getId(){
        return id;
    }
}

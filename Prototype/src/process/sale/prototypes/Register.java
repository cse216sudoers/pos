
package process.sale.prototypes;

public class Register {
    private int id;
    private Cashier currentCashier;
    
    public Register(int id){
        currentCashier = null;
        this.id = id;
    }
   
    public boolean logOffRegister(){
        currentCashier = null;
        return true; //successful (idk if we will have unsuccessful cases later)
    }
    
    public int getId(){
        return id;
    }
    
    public Cashier getCashier(){
        return currentCashier;
    }
    
    public void setCashier(Cashier cashier){
        currentCashier = cashier;
    }
}

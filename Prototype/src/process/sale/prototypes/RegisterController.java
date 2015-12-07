/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 * Does operations for a register domain object
 * @author Pikachu
 */
public class RegisterController{
    private RegisterManager registerManager;
    private CashierManager cashierManager;
    private TransactionController currentTransaction;
    private SaleManager saleManager;
    private Register register;
    
    /**
     * 
     * @return register
     */
    public Register getRegister(){
        return register;
    }
    
    /**
     * Register to make a controller for
     * @param register
     */
    public RegisterController(Register register){
        registerManager = RegisterManager.getInstance();
        saleManager = SaleManager.getInstance();
        cashierManager = CashierManager.getInstance();
        this.register = register;
        currentTransaction = null;
    }
    
    public TransactionController getCurrentTransaction(){
        return currentTransaction;
    }
    /**
     * Register ID to make controller for
     * @param registerId
     */
    public RegisterController(int registerId){
        registerManager = RegisterManager.getInstance();
        saleManager = SaleManager.getInstance();
        cashierManager = CashierManager.getInstance();
        currentTransaction = null;
        register = registerManager.getRegisterById(registerId);
    }
    
    //start a suspended sale
    public void processSuspendedSale(Sale sale){
        currentTransaction = new SaleController(sale);
    }
    
    //start a suspended return
    public void processSuspendedReturn(Return ret){
        currentTransaction = new ReturnController(ret);
    }
    
    //start a suspended rental
    public void processSuspendedRental(Rental rental){
        currentTransaction = new RentalController(rental);
    }
    
    //start a sale
    public void processSale(){
        currentTransaction = new SaleController();
        //currentTransaction.start();
    }
    
    //start a return
    public void processSaleReturn(Sale sale){
        currentTransaction = new ReturnController(sale.getId());
    }
    
    public void processRentalReturn(Rental rental){
        currentTransaction = new RentalReturnController(rental);
    }
    //start a rental
    public void processRental(){
        currentTransaction = new RentalController();
    }
}
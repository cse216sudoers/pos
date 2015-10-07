/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */

//This class if for assigning Id's to registers, cashiers, and sales so we don't have duplicates
public class IdManager {
    private int saleId;
    private int registerId;
    private int cashierId;
    private static IdManager instance;
    
    private IdManager(){
        saleId = 1;
        registerId = 1;
        cashierId = 1;
    }
    public static synchronized IdManager getInstance(){
        if(instance == null){
            instance = new IdManager();
        }
        return instance;
    }
    public int getNextSaleId(){
        return ++saleId;
    }
    public int getNextRegisterId(){
        return ++registerId;
    }
    public int getNextCashierId(){
        return ++cashierId;
    }
}

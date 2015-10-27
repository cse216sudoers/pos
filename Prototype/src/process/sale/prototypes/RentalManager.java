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
public class RentalManager {
    private HashMap<Integer, Rental> rentals;
    private HashMap<Integer, Rental> suspendedRentals;
    private int nextId = 0;
    private static RentalManager instance;
    
    private RentalManager(){
        rentals = new HashMap(89);
        suspendedRentals = new HashMap(89);
    }
    
    public static synchronized RentalManager getInstance(){
        if (instance == null){
            instance = new RentalManager();
        }
        return instance;
    }
    
    public Rental getRentalById(int id){
        return rentals.get(id);
    }
    
    public Rental getSuspendedRentalById(int id){
        return rentals.get(id);
    }
    
    public HashMap<Integer, Rental> getRentals(){
        return rentals;
    }
    
    public HashMap<Integer, Rental> getSuspendedRentals(){
        return suspendedRentals;
    }
    
    public void addRental(Rental rental){
        rentals.put(rental.getId(), rental);
    } 
    
    public void addSuspendedRental(Rental rental){
        suspendedRentals.put(rental.getId(), rental);
    }
    public int getNextId(){
        return ++nextId;
    }
}

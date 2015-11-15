/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.HashMap;

/**
 * Keeps track of all rentals
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
    
    /**
     * Get singleton
     * @return singleton
     */
    public static synchronized RentalManager getInstance(){
        if (instance == null){
            instance = new RentalManager();
        }
        return instance;
    }
    
    /**
     *
     * @param id unique id
     * @return rental
     */
    public Rental getRentalById(int id){
        return rentals.get(id);
    }
    
    /**
     *
     * @param id unique id
     * @return suspended rental
     */
    public Rental getSuspendedRentalById(int id){
        return suspendedRentals.get(id);
    }
    
    /**
     * return rentals
     * @return
     */
    public HashMap<Integer, Rental> getRentals(){
        return rentals;
    }
    
    /**
     * return suspended rentals
     * @return  suspended rentals
     */
    public HashMap<Integer, Rental> getSuspendedRentals(){
        return suspendedRentals;
    }
    
    /**
     * add rental
     * @param rental
     */
    public void addRental(Rental rental){
        rentals.put(rental.getId(), rental);
    } 
    
    /**
     * add suspended rental
     * @param rental suspended rental
     */
    public void addSuspendedRental(Rental rental){
        suspendedRentals.put(rental.getId(), rental);
    }
    /**
     * get next unique id
     * @return next unique id
     */
    public int getNextId(){
        return ++nextId;
    }
}

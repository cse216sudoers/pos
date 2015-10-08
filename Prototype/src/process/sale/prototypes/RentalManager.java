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
public class RentalManager {
    private ArrayList<Rental> rental;
    private ArrayList<Rental> suspendedRentals;
    private int nextId = 0;
    private static RentalManager instance;
    
    private RentalManager(){
        rental = new ArrayList<Rental>();
        suspendedRentals = new ArrayList<Rental>();
    }
    
    public static synchronized RentalManager getInstance(){
        if (instance == null){
            instance = new RentalManager();
        }
        return instance;
    }
    
    public Rental getRentalById(int id){
        for(int i = 0; i < rental.size(); i++){
            if(rental.get(i).getId() == id)
                return rental.get(i);
        }
        return null;
    }
    
    public ArrayList<Rental> getRentals(){
        return rental;
    }
    
    public ArrayList<Rental> getSuspendedRentals(){
        return suspendedRentals;
    }
   
    
    public void addRental(Rental ret){
        rental.add(ret);
    } 
    
    public void addSuspendedSale(Rental ret){
        suspendedRentals.add(ret);
    }
    public int getNextId(){
        return ++nextId;
    }
}

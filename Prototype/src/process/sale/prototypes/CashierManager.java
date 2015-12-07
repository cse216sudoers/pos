/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import DB.User;
import javax.swing.JOptionPane;

/**
 * Keep track of all cashiers in POS system
 * @author Pikachu
 */
public class CashierManager {
    private ArrayList<Cashier> cashiers; //All users
    private ArrayList<Cashier> currentCashiers; //All users currently loggin in
    private static CashierManager instance; //singleton
    private int nextId = 0; //next unique id for use in creating new cashier
    //private static String path=Path.getPath()+""
    
    private CashierManager(){
        currentCashiers = new ArrayList<>();
        cashiers = new ArrayList<>();
        try{
            //read in all users
            Scanner read = new Scanner(new File(Resources.getPath("Users.txt")));
            read.useDelimiter("\\|");
            while(read.hasNext()){
                String name = read.next();
                int uid = Integer.parseInt(read.next());
                int access = Integer.parseInt(read.next());
                String username = read.next();
                String password = read.next();
                Cashier.Access type;
                if(access == 0)
                    type = Cashier.Access.Admin;
                else if(access == 1)
                    type = Cashier.Access.Manager;
                else
                    type = Cashier.Access.Cashier;
                cashiers.add(new Cashier( name, username, password, uid, type));
                if(uid >= nextId)
                    nextId = uid+1;
                read.nextLine();
            }
        }
        catch(Exception e){
            //System.out.println(e.toString());
        }
    }

    /**
     *
     * @return singleton
     */
    public static synchronized CashierManager getInstance(){
        if(instance == null){
            instance = new CashierManager();
        }
        return instance;
    }
    
    /**
     * Set new list of cashiers
     * @param cashiers list of all cashiers
     */
    public CashierManager(ArrayList<Cashier> cashiers){
        this.cashiers = cashiers;
    }
    
    /**
     * Add a new cashier
     * @param cashier
     */
    public boolean addCashier(Cashier cashier){
        cashiers.add(cashier);
        int access;
        if(cashier.getAccess() == Cashier.Access.Admin)
            access = 0;
        else if(cashier.getAccess() == Cashier.Access.Manager)
            access = 1;
        else
            access = 2;
        DB.User.insert(cashier.getName(), access, cashier.getUsername(), cashier.getPassword());
        return true;
    }
    
    /**
     * Remove a cashier - successful if the cashier is not currently logged on
     * @param username cashier to remove
     * @return true if successful
     */
    public boolean removeCashier(String username){
        int id = 0;
        for(int i = 0; i < currentCashiers.size(); i++){
            if(cashiers.get(i).getUsername().equals(username)){
                return false; // return false if the cashier is currently logged on somewhere
            }
        }
        
        for(int i = 0; i < cashiers.size(); i++){
            if(cashiers.get(i).getUsername().equals(username)){
                DB.User.delete(cashiers.get(i).getId());
                cashiers.remove(i);
            }
        }
        
        return true; //return true if doesn't exist
    }
    
    /**
     * Add a cashier to list of currently logged on cashiers
     * @param cashier cashier that logged on
     */
    public void addCurrentCashier(Cashier cashier){
        currentCashiers.add(cashier);
    }
    
    /**
     * remove cashier that is not longer currently logged on
     * @param cashier cashier to remove from current cashier list
     */
    public void removeCurrentCashier(Cashier cashier){
        currentCashiers.remove(cashier);
    }
    
    /**
     * get all cashiers
     * @return list of all cashiers
     */
    public ArrayList<Cashier> getCashiers(){
        return cashiers;
    }
    
    /**
     * get all currently logged on cashiers
     * @return list of all currently logged on cashiers
     */
    public ArrayList<Cashier> getCurrentCashiers(){
        return currentCashiers;
    }
    
    /**
     * get cashier by username
     * @param username username
     * @return cashier with username
     */
    public Cashier getCashierByUsername(String username){
        for(int i = 0; i < cashiers.size(); i++){
            if(cashiers.get(i).getUsername().equals(username))
                return cashiers.get(i);
        }
        return null;
    }
    public boolean verifyUsername(String username){
        if(getCashierByUsername(username) != null)
                return true;
        return false;
    }
    
    /**
     * Check if cashier password is correct
     * @param username
     * @param password
     * @return
     */
    public Cashier verifyPassword(String username, String password){
        Cashier cashier = getCashierByUsername(username);
        if(password.equals(cashier.getPassword()))
            return cashier;
        return null;
    }
    
    public boolean changePassword(String username, String oldPassword, String newPassword1, String newPassword2){
        Cashier cashier;
        if((cashier = verifyPassword(username, oldPassword)) != null){
            if(newPassword1.equals("")){
                JOptionPane.showMessageDialog (null, "Invalid new Password.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }else if(newPassword1.equals(newPassword2)){
                cashier.setPassword(newPassword1);
                return true;
            }else{
                JOptionPane.showMessageDialog (null, "New passwords do not match.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog (null, "Old password is incorrect.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    /**
     *
     * @param id unique id for cashier
     * @return cashier with unique id
     */
    public Cashier getCashierById(int id){
        for(int i = 0; i < cashiers.size(); i++){
            if(cashiers.get(i).getId() == id)
                return cashiers.get(i);
        }
        return null;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Cashier getCurrentCashierById(int id){
        for(int i = 0; i < currentCashiers.size(); i++){
            if(currentCashiers.get(i).getId() == id)
                return currentCashiers.get(i);
        }
        return null;
    }
    
    /**
     *
     * @return
     */
    public int getNextId(){
        return ++nextId;
    }
    
    @Override
    public String toString(){
        String output = String.format("%3s \t %15s \t %10s \t %10s\n", "ID", "Name", "Username", "Access");
        for(int i = 0; i < cashiers.size(); i++)
            output += cashiers.get(i).toString();
        return output;
    }
}

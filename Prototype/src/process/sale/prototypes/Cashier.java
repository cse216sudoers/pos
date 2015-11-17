/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

/**
 *
 * @author Pikachu
 */
public class Cashier {
    private String username; //log in username
    private String name; //first and last name
    private String password; //login password
    /**
     *Access level
     */
    public static enum Access{
        /**
         *no special access- default
         */
        Cashier,
        /**
         *can provide overrides
         */
        Manager,
        /**
         *can provide overrides and access user management screen
         */
        Admin
    }
    private Access access;
    private int id;
    
    /**
     *Creates a cashier with the next default ID
     * @param name first and last name
     * @param username username for log in
     * @param password password for login
     * @param access access level
     */
    public Cashier(String name, String username, String password, Access access){
        this.username = username;
        this.name = name;
        this.password = password;
        id = CashierManager.getInstance().getNextId();
        this.access = access;
    }
    /**
     *Creates a cashier with a specific ID
     * @param name first and last name
     * @param username username for log in
     * @param password password for login
     * @param uid id number
     * @param access access level
     */
    public Cashier(String name, String username, String password, int uid, Access access){
        this.username = username;
        this.name = name;
        this.password = password;
        this.id= uid;
        this.access = access;
    }
    
    /**
     * @return access level
     */
    public Access getAccess(){
        return access;
    }
    
    /**
     * @param access access level
     */
    public void setAccess(Access access){
        this.access = access;
    }
    
    /**
     *
     * @return username
     */
    public String getUsername(){
        return username;
    }
    
    /**
     *
     * @return first and last name
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return log in password
     */
    public String getPassword(){
        return password;
    }
    
    /**
     * @param username username
     */
    public void setUsername(String username){
        this.username = username;
    }
    
    /**
     * @param name first and last name
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     *
     * @param password login password
     */
    public void setPassword(String password){
        this.password = password;
    }
    
    /**
     *
     * @return identification id
     */
    public int getId(){
        return id;
    }
    
    @Override
    public String toString(){
        return String.format("%3d \t %20s \t %10s \t %10s\n", id, name, username, access.toString());
    }
}

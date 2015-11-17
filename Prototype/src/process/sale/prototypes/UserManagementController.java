/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.util.Scanner;

/**
 * Controls all users on the system. Only admin level can access user management
 * @author Pikachu
 */
public class UserManagementController {
    CashierManager cashierManager;
    
    /**
      Get singleton
     */
    public UserManagementController(){
         cashierManager = CashierManager.getInstance();
    }
    
    /**
     * Start asking for input
     */
    public void start(){
        Scanner scan = new Scanner(System.in);
        String input;
        do{
            System.out.println("Enter 'add', 'remove', 'display' or 'q' to quit: ");
            input = scan.next();
            if(input.equalsIgnoreCase("q"))
                break;
            else if(input.equalsIgnoreCase("add")){
                processAdd();
            }else if(input.equalsIgnoreCase("remove")){
                processRemove();
            }else if(input.equalsIgnoreCase("display")){
                processDisplay();
            }else{
                System.out.println("Invalid Input.");
            }
        }while(!input.equalsIgnoreCase("q"));
    }
    
    private void processAdd(){
        Scanner scan = new Scanner(System.in);
        String input;
        System.out.println("Enter full name or 'q' to quit: ");
        input = scan.next();
        if(input.equalsIgnoreCase("q"))
            return;
        String name = scan.next();

        System.out.println("Enter userId or 'q' to quit: ");
        input = scan.next();
        if(input.equalsIgnoreCase("q"))
            return;
        String username = scan.next();

        do{
            System.out.println("Enter access status: 'cashier', 'manager', 'admin' or 'q' to quit: ");
            input = scan.next();
            if(input.equalsIgnoreCase("q"))
                return;
        }while(!input.equalsIgnoreCase("cashier") && !input.equalsIgnoreCase("manager") && !input.equalsIgnoreCase("admin"));

        Cashier.Access access;
        if(input.equalsIgnoreCase("admin")){
            access = Cashier.Access.Admin;
        } else if(input.equalsIgnoreCase("manager")){
            access = Cashier.Access.Manager;
        } else{
            access = Cashier.Access.Cashier;
        }          
        cashierManager.addCashier(new Cashier(name, username, "1234", cashierManager.getNextId(), access));
    }
    
    private void processRemove(){
        Scanner scan = new Scanner(System.in);
        String input;
        System.out.println("Enter userName or 'q' to quit: ");
        input = scan.next();
        if(input.equalsIgnoreCase("q"))
            return;
        String name = scan.next();
        
        if(cashierManager.removeCashier(name)){
            System.out.println("Cashier successfully removed.");
        }
        else{
            System.out.println("Cashier cannot be removed.");
        }
    }
    private void processDisplay(){
        //display cashiers
    }
}

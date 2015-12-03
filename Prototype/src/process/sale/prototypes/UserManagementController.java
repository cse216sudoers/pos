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
        input = scan.nextLine();
        if(input.equalsIgnoreCase("q"))
            return;
        String name = input;
        do{
        System.out.println("Enter username or 'q' to quit: ");
        input = scan.next();
        if(input.equalsIgnoreCase("q"))
            return;
        if(cashierManager.getCashierByUsername(input)!=null)
            System.out.println("Username already exists.");
        else
            break;
        }while(true);
        
        String username = input;

        do{
            System.out.println("Enter access status: 'cashier', 'manager', 'admin' or 'q' to quit: ");
            input = scan.next();
            if(input.equalsIgnoreCase("q"))
                return;
            if(!input.equalsIgnoreCase("cashier") && !input.equalsIgnoreCase("manager") && !input.equalsIgnoreCase("admin"))
                System.out.println("Invalid access status.");
            else
                break;
        }while(true);

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
        System.out.println("Enter username or 'q' to quit: ");
        input = scan.next();
        if(input.equalsIgnoreCase("q"));        
        else if(cashierManager.removeCashier(input)){
            System.out.println("Cashier successfully removed.");
        }
        else{
            System.out.println("Cashier cannot be removed.");
        }
    }
    private void processDisplay(){
        System.out.println(cashierManager);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import process.sale.prototypes.Resources;

/**
 *
 * @author shua
 */
public class User extends DBClass{
    static String path = Resources.getPath("Users.txt");
    
    /**
     *
     * @param id --> The id of the person you want to remove
     */
    public static void delete(int id){
            BufferedWriter writer = null;
        try {
            File inputFile = new File(path);
            File tempFile = new File("myTempFile.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;
            
            while((currentLine = reader.readLine()) != null) {
                String[] columns = currentLine.split("\\|");
                if (columns.length < 2)
                    continue; 
                
                if (Integer.parseInt(columns[1]) == id)
                    continue;
                
                writer.write(currentLine + System.getProperty("line.separator"));
            }   writer.close();
            reader.close();
            boolean successful = tempFile.renameTo(inputFile);
        } catch (IOException ex) {
            Logger.getLogger(DBClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(DBClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void insert (String name, int permission, String username, String password){
        int id = nextId(path);
        
        String newTuple = name + "|" + id + "|" + permission + "|" + username + "|" + password + "|";
        
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
            out.println(newTuple);
            out.close();
        }catch (IOException e) {
        }
    }
}

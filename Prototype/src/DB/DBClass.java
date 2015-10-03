/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shua
 * All of the DB files should be pipe (|) separated. 
 */
public class DBClass {
    public static BufferedReader open(String path){
        BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        return br;
    } 
    
    public static void insert(String path, String row){

    }

    public static void delete(String path, int id){

    }

    public static void update(String path, int id){

    }
    
    public static int nextId(String path){
        int id = 0;
        String line;
        BufferedReader table = open(path);
    
        try {
            while ((line = table.readLine()) != null) {
                String[] columns = line.split("|");
                id = Integer.parseInt(columns[0]);
            }
        } catch (IOException ex) {
            Logger.getLogger(DBClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

}

package process.sale.prototypes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author noahreifsnyder
 */
public class Resources {
    public static void Instantiate(){
        String files[]=new String[]{"Users.txt","Product_description.txt","Coupons.txt","Sales.txt","Rentals.txt"};
        for (String path:files){
            File file=new File(getPath(path));
            try {
                if (file.createNewFile()){
                    System.out.println("Writing"+path);
                    fill(getPath(path),path);
                }
            } catch (IOException ex) {
                Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public static String getPath(String file){
        String path=Store.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path+=file;
        path = path.replaceAll("%20", " ");
        return path;
    }
    private static void fill(String path,String Path) throws IOException{
        InputStream is = Store.class.getResourceAsStream(Path);
        System.out.println(Store.class.getResource(Path).getPath());
        System.out.println(path);
        FileOutputStream fos;
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter(path));
            int data = is.read();
            String input="";
            while (data != -1) {
                input+=(char)data;
                data=is.read();
            }
            bw.write(input);
            bw.close();
            System.out.println(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

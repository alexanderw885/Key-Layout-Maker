/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows
 */
public class TestMain {
    
    public static void main(String[] args){
        System.out.println("working!");
        
        File newTest = new File("temp.txt");
        
        try {
            
            if(newTest.createNewFile()){
                System.out.println("File made!");
            } else {
                System.out.println("File aleady exists");
            }
            
            
            File test = new File("data.txt");
            Scanner scan = new Scanner(test);
            while (scan.hasNextLine()){
                String out = scan.nextLine();
                System.out.println(out);
            }

        } catch (IOException ex ) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        
    }
    
}

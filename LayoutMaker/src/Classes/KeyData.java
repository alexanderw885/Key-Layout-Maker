/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author Windows
 */
public class KeyData {
    Key[] keys;
    
    public KeyData(String path){
        keys = new Key[30];
        try{
            File data = new File(path);
            Scanner scan = new Scanner(data);
            int loop = 0;
            while(scan.hasNextLine()){
                keys[loop] = new Key(scan.nextLine());
                loop++;
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found, ensure "+path+" exists.");
            System.exit(1);
        } 
    }


}

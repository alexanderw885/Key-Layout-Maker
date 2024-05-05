/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Windows
 */
public class Utils {
    
         protected static int ChartoInt(char c){
        int val = (char) c;
        
        if(val>=97 && val<=122){
           return val-97; 
        }
        
        switch (val){
            case 59:
                return 26;
            case 39:
                return 27;
            case 44:
                return 28;
            case 46:
                return 29;
        }
        return -1;
    }
     
     protected static char InttoChar(int val){
         
         if(val>=0 && val<=25){
           return (char)(val+97); 
        }
        
        switch (val){
            case 26:
                return ';';
            case 27:
                return '\'';
            case 28:
                return ',';
            case 29:
                return '.';
        }
        return '?';
         
     }
    
}

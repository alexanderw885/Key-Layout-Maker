/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataReader;

/**
 *
 * @author Windows
 */
public class Utils {
    
    public static int ChartoInt(char c){
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
     
     public static char InttoChar(int val){
         
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
     
    /**
    * Reverses order of array
    * @param array array to be reversed
    */
    public static void Reverse(Comparable[] array){
        for(int i=0; i<array.length/2; i++){
            Comparable temp = array[i];
            array[i] = array[array.length-i-1];
            array[array.length-i-1] = temp;
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.StringTokenizer;

/**
 *
 * @author Windows
 */
class Key {
    int[] keyData;
    Key(String data){
        
        keyData = new int[31];
        StringTokenizer tokens = new StringTokenizer(data, ",");
        int loop = 0;
        while(tokens.hasMoreTokens()){
            keyData[loop] = Integer.valueOf(tokens.nextToken());
        }
    }
    
     private int ChartoInt(char c){
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
}

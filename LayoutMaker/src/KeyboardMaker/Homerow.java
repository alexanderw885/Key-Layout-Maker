/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KeyboardMaker;

import DataReader.*;

/**
 * Creates home of keys for provided keyboard
 * @author Windows
 */
public class Homerow {
    
    public static void MakeHomerow(KeyData data, Keyboard board){
        
        // State is used to track the state of each key in key array
        //0 means side undetermined
        // +-1 means left/right side, undetermined place
        //+-2 means left/right side, in place
        int[] state = new int[10];
        
        Key[] key = data.MostPressed();
        // Keys 0-9 are most pressed, so they are top candidates for home row
        // Most pressed keys go on middle finger, I arbitrarily chose most pressed on left
        Key[] home = board.getHomeRow();
        
        //State should always be updated when keys are placed into the home row
        home[2] = key[0];
        state[0] = -2;
        home[7] = key[1];
        state[1] = 2;
        SideRollover(key, home);
        BalanceHands(key, home);
        BalanceFingers(key, home);
        
       
        
    }
    
    /**
     * Looks at how often mid-finger keys come after other keys, if it happens a lot than they are put together.
     * @param key
     * @param home 
     */
    private static void SideRollover(Key[] key, Key[] home){
        Key leftM = home[2];
        Key rightM = home[7];
        
        for(int i=2; i<10; i++){
            
        }
        
    }

    /**
     * Looks at all home row keys not yet assigned hands, and assigns them to keep hands
     * as balanced as possible
     * @param key
     * @param home 
     */
    private static void BalanceHands(Key[] key, Key[] home) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     *  Orders all keys without positions, putting less frequent keys on weaker fingers
     * @param key
     * @param home 
     */
    private static void BalanceFingers(Key[] key, Key[] home) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

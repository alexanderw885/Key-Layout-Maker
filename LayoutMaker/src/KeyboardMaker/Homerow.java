/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KeyboardMaker;

import DataReader.*;
import java.util.Arrays;

/**
 * Creates home of keys for provided keyboard
 *
 * @author Windows
 */
public class Homerow {

    public static void MakeHomerow(KeyData data, Keyboard board) {

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
        SideRollover(key, home, state, data);
        BalanceHands(key, home, state);
        BalanceFingers(key, home, state);

    }

    /**
     * Decides index finger home row keys to be keys with most lpairs with 10
     * other keys
     *
     * @param key
     * @param home
     */
    private static void SideRollover(Key[] key, Key[] home, int[] state, KeyData data) {

        // First, sort all keys by lpair10
        Key[] lpairs = new Key[key.length];
        for (int i = 0; i < key.length; i++) {
            lpairs[i] = key[i];
        }
        Arrays.sort(lpairs, new LPairComparator());
        Utils.Reverse(lpairs);

        // Now, choose 2 keys with highest lpair10 that AREN'T the two most pressed keys
        Key top1 = new Key();
        Key top2 = new Key();

        for (int i = 0; i < 4; i++) {
            if (lpairs[i].compareTo(home[2]) != 0 && lpairs[i].compareTo(home[7]) != 0) {

                if (top1.getName() == '-') {
                    top1 = lpairs[i];
                    continue;
                }
                if (top2.getName() == '-') {
                    top2 = lpairs[i];
                    break;
                }
                System.out.println("ERROR in SideRollover loop;");
                System.exit(1);
            }
        }
        // Now that we have the two highest lpair10 keys, decide which hand they go on
        // Do this by seeing which hand gives the most lpairs for each key

        int top1L = 0;
        int top1R = 0;
        int top2L = 0;
        int top2R = 0;
        for (int i = 0; i < 2; i++) {
            if (state[i] < 0) {
                top1L = top1.getLPair(key[i].getName()).getFreq();
                top2L = top2.getLPair(key[i].getName()).getFreq();
            } else if (state[i] > 0) {
                top1R = top1.getLPair(key[i].getName()).getFreq();
                top2R = top2.getLPair(key[i].getName()).getFreq();
            }
        }

        if (top1L + top2R > top1R + top2L) {
            home[3] = top1;
            home[6] = top2;
        } else{
            home[3] = top2;
            home[6] = top1;
        }
        
        // Finally, we have to set the state value for the newly added keys
        for(int i=0; i<key.length; i++){
            if       (home[3].compareTo(key[i])== 0){
                state[i] = -2;
            } else if(home[7].compareTo(key[i])== 0){
                state[i] = 2;
            }
        }
    }

    /**
     * Looks at all home row keys not yet assigned hands, and assigns them to
     * keep hands as balanced as possible
     *
     * @param key
     * @param home
     */
    private static void BalanceHands(Key[] key, Key[] home, int[] status) {

        int leftTotal = 0;
        int rightTotal = 0;
        // Gets number of presses on each hand so far
        for (int i = 0; i < 10; i++) {
            if (status[i] < 0) {
                leftTotal += key[i].getPresses();
            } else if (status[i] > 0) {
                rightTotal += key[i].getPresses();
            }
        }

        System.out.println("Left total: " + leftTotal);
        System.out.println("Right total: " + rightTotal);

    }

    /**
     * Orders all keys without positions, putting less frequent keys on weaker
     * fingers
     *
     * @param key
     * @param home
     */
    private static void BalanceFingers(Key[] key, Key[] home, int[] status) {
        System.out.println("TODO: BALANCEFINGER FUNCTION IN HOMEROW");
    }
}

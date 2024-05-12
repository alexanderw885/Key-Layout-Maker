/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataReader;

import java.util.Comparator;

/**
 *
 * @author Windows
 */
public class LPairComparator implements Comparator<Key>{

    @Override
    public int compare(Key o1, Key o2) {
    return o1.getLPair10()-o2.getLPair10();
    }
    
}

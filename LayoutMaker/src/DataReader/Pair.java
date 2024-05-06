/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataReader;

/**
 * Class containing frequency of first-last char pairs
 * @author Alex
 */
public class Pair implements Comparable<Pair>{
    
    char first;
    char last;
    int freq;
    
    public Pair(int frequency, int i, int j){
        first = Utils.InttoChar(i);
        last = Utils.InttoChar(j);
        freq = frequency;
    
    }
    
    @Override
    public String toString(){
        String out = String.format("%c%c: Pressed %d times", first, last, freq);
        
        
        return out;
    }

    @Override
    public int compareTo(Pair o) {
        if(this.freq-o.freq != 0){
            return this.freq-o.freq;
        }
        if(Utils.ChartoInt(o.first)-Utils.ChartoInt(this.first) != 0){
            return Utils.ChartoInt(o.first)-Utils.ChartoInt(this.first);
        }
        
        return Utils.ChartoInt(o.last)-Utils.ChartoInt(this.last);
    }
    
    
}

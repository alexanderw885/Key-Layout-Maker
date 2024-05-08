/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataReader;

import java.util.StringTokenizer;

/**
 * Stores key presses for each key, along with all character pairs containing that key
 * @author Alex
 */
public class Key implements Comparable<Key>{
    
    private char name;
    private int presses;
    private Pair[] fpairs;
    private Pair[] lpairs;
    
    Key(int[][] origin, char c){
        int index =Utils.ChartoInt(c);
        if(index==-1){
            System.out.println("invalid character:"+c);
            System.exit(1);
        }
        name = c;
        presses = origin[index][30];
        MakeFPairs(origin, index);
        MakeLPairs(origin, index);
    }
    
    Key(int[][] origin, int index){
        char c = Utils.InttoChar(index);
        if(index==-1){
            System.out.println("invalid character:"+c);
            System.exit(1);
        }
        name = c;
        presses = origin[index][30];
        MakeFPairs(origin, index);
        MakeLPairs(origin, index);
    }
    
    /**
     * Used for making blank keys
     */
    Key(){
        name = '-';
        presses = 0;
    }
    
    /**
     * 
     * @return name of key
     */
    public char getName(){
        return name;
    }
    
    /**
     * 
     * @return number of times key has been pressed
     */
    public int getPresses(){
        return presses;
    }
    
    /**
     * Returns specified pair starting with this key, ending with char c
     * @param c last character in the pair
     * @return this-c pair
     */
    public Pair getFPair(char c){
        return fpairs[Utils.ChartoInt(c)];
    }
    
    /**
     * Returns specified pair starting with this c, ending with this key
     * @param c first character in the pair
     * @return c-this pair
     */
    public Pair getLPair(char c){
        return fpairs[Utils.ChartoInt(c)];
    }
    
    /**
     * Returns all pairs starting with this key, sorted alphabetically
     * @return 
     */
    public Pair[] getFPairs(){
        return fpairs;
    }
    
    /**
     * Returns all pair ending with this key, sorted alphabetically
     * @return 
     */
    public Pair[] getLPairs(){
        return lpairs;
    }

    /**
     * Called when Key is constructed, creates list of all pairs starting with this key
     * in alphabetical order
     * @param origin 30x31 integer array taken from data.txt
     * @param index index of last char in pair in the array
     */
    private void MakeFPairs(int[][] origin, int index){
        fpairs = new Pair[30];
        
        for(int i=0; i<30; i++){
            fpairs[i] = new Pair(origin[index][i], index, i);
        }
        
        return;
    }
    
    /**
     * Called when Key is constructed, creates list of all pairs ending with this key
     * in alphabetical order
     * @param origin 30x31 integer array taken from data.txt
     * @param index index of first char in pair in the array
     */
    private void MakeLPairs(int[][] origin, int index){
        lpairs = new Pair[30];
        
        for(int i=0; i<30; i++){
            lpairs[i] = new Pair(origin[i][index], i, index);
        }
        
        return;
    }
    
    @Override
    public String toString(){
        String out =String.format("%c: Pressed %d times\n", name, presses);
        out += "Pairs starting with "+name+":\n";
        for(int i=0; i<30; i++){
            out +=fpairs[i].toString()+"\n";
        }
        out += "Pairs ending with "+name+":\n";
        for(int i=0; i<30; i++){
            out +=lpairs[i].toString()+"\n";
        }
        
        return out;
    }

    @Override
    public int compareTo(Key o) {
        if(this.presses - o.presses !=0){
            return this.presses - o.presses;
        }
            return Utils.ChartoInt(this.name)- Utils.ChartoInt(o.name);
    }

}

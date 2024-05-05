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
    
    public char GetName(){
        return name;
    }
    
    public int GetPresses(){
        return presses;
    }
    
    public Pair[] GetFPairs(){
        return fpairs;
    }
    
    public Pair[] GetLPairs(){
        return lpairs;
    }

    private void MakeFPairs(int[][] origin, int index){
        fpairs = new Pair[30];
        
        for(int i=0; i<30; i++){
            fpairs[i] = new Pair(origin[index][i], index, i);
        }
        
        return;
    }
    
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

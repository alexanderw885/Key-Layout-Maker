/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Class that stores all keys and pairs, and provides them to the caller either
 * individually or in sorted lists.
 * @author Alex
 */
public class KeyData {
    int[][] raw;
    Key[] keys;
    
    /**
     * Creates KeyData from text file made by KeyReader
     * @param path file path 
     */
    public KeyData(String path){
        raw = new int[30][31];
        keys = new Key[30];
        
        ScanData(path);
        
        for(int i=0; i<30; i++){
            keys[i] = new Key(raw, i);
        }
        
    }
    
    /**
     * Returns the Key representing char c
     * @param c the character to return the key of
     * @return Key object of char c
     */
    public Key getKey(char c){
        return keys[Utils.ChartoInt(c)];
    }
    
    /**
     * Creates an array of Keys sorted by how often they're pressed, most to least
     * @return sorted array of Keys
     */
    public Key[] MostPressed(){
        Key[] out = new Key[30];
        
        for(int i=0; i<keys.length; i++){
            out[i] =keys[i];
        }
        
        Arrays.sort(out);
        Utils.Reverse(out);
        
        return out;
    }
    
    /**
     * Creates an array of Pairs sorted by how often they occur, most to least
     * @return sorted array of Pairs
     */
    public Pair[] MostPairs(){
        Pair[] out = new Pair[30*30];
        
        for(int i=0; i<30; i++){
            Pair[] oneLett =  keys[i].getFPairs();
            
            for(int j=0; j<30; j++){
                out[(30*i)+j] = oneLett[j];
            }
        }
        
        Arrays.sort(out);
        Utils.Reverse(out);
        
        return out;
    }
    
    /**
     * Returns Pair object for specified chars in order f-l
     * @param f first char in pair
     * @param l last char in pair
     * @return f-l Pair
     */
    public Pair getPair(char f, char l){
        return keys[Utils.ChartoInt(f)].getFPair(l);
    }
    


    /**
     * Handles all the file reading for constructor
     * @param path file path to read
     */
    private void ScanData(String path){
        try{
            File data = new File(path);
            Scanner scan = new Scanner(data);
            int i = 0;
            while(scan.hasNextLine()){
                StringTokenizer tokens = new StringTokenizer(scan.nextLine(), ",");
                int j = 0;
                while(tokens.hasMoreTokens()){
                    raw[i][j] = Integer.valueOf(tokens.nextToken());
                    j++;
                }
                i++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found, ensure "+path+" exists.");
            System.exit(1);
        } 
    }
    
    public String KeyString(){
        String out ="";
        for(int i=0; i<keys.length; i++){
            out +=keys[i].toString();
        }
        out +="\n";
        return out;
    }
    
    @Override
    public String toString(){
        String out = "";
        
        for(int i=0; i<30; i++){
            for(int j=0; j<31; j++){
                out += String.format("%d,",raw[i][j]);
            }
            out +="\n";
        }
        
        return out;
    }
}

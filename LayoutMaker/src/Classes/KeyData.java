/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 *
 * @author Windows
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
        Reverse(out);
        
        return out;
    }
    
    public Pair[] MostPairs(){
        Pair[] out = new Pair[30*30];
        
        for(int i=0; i<30; i++){
            Pair[] oneLett =  keys[i].GetFPairs();
            
            for(int j=0; j<30; j++){
                out[(30*i)+j] = oneLett[j];
            }
        }
        
        Arrays.sort(out);
        Reverse(out);
        
        return out;
    }
    
    /**
     * Reverses order of array
     * @param array array to be reversed
     */
    private void Reverse(Comparable[] array){
        for(int i=0; i<array.length/2; i++){
            Comparable temp = array[i];
            array[i] = array[array.length-i-1];
            array[array.length-i-1] = temp;
        }
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

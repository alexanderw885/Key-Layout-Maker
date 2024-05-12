/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tests;
import DataReader.KeyData;
import DataReader.Key;
import DataReader.Pair;

/**
 *
 * @author Windows
 */
public class ReaderTest {
    public static void run(KeyData data){
        
        /*System.out.println("\nDATA!!!");
        System.out.println(data.toString()+"\n");
        
        System.out.println("\nKEY!!!");        
        Key a = data.getKey('j');
        System.out.println(a.toString());
        
        System.out.println("\nPAIR!!!");
        Pair at = data.getPair('a', 't');   
        System.out.println(at.toString());
        
        System.out.println("\nPRESSES!!!");
        Key[] pressOrder = data.MostPressed();
        for(int i=0; i<30; i++){
            System.out.printf("%c: %d\n", pressOrder[i].getName(), pressOrder[i].getPresses());
        }
        
        System.out.println("\nPAIRS!!!");
        Pair[] pairOrder = data.MostPairs();
        for(int i=0; i<pairOrder.length; i++){
            System.out.printf(pairOrder[i].toString()+"\n");
        }
        
        System.out.println("\nALL KEYS!!!");
        System.out.println(data.KeyString());*/
        
        Key h = data.getKey('h');
        Key t = data.getKey('t');
        System.out.println(h.getLPair('t').toString());
        System.out.println(t.getFPair('h').toString());
        
        Pair[] hlp = h.getLPairs();
        for(int i=0; i<hlp.length; i++){
            System.out.println(hlp[i].toString());
        }

    }
}

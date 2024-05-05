/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tests;
import Classes.*;

/**
 *
 * @author Windows
 */
public class MainTest {
    public static void main(String[] args){
        KeyData data = new KeyData("data.txt");
        
        System.out.println("\nDATA!!!");
        System.out.println(data.toString()+"\n");
        
        System.out.println("\nKEY!!!");        
        Key a = data.getKey('a');

        System.out.println(a.toString());
        
        System.out.println("\nPRESSES!!!");
        Key[] pressOrder = data.MostPressed();
        for(int i=0; i<30; i++){
            System.out.printf("%c: %d\n", pressOrder[i].GetName(), pressOrder[i].GetPresses());
        }
        
        System.out.println("\nPAIRS!!!");
        Pair[] pairOrder = data.MostPairs();
        for(int i=0; i<pairOrder.length; i++){
            System.out.printf(pairOrder[i].toString()+"\n");
        }

    }
}

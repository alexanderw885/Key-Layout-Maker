/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KeyboardMaker;

import DataReader.*;
/**
 * Class that handles the creation of keyboard layouts by calling and connecting to dedicated classes for each step
 * @author Windows
 */
public class Maker {
    KeyData data;
    Keyboard board;
    
    public Maker(KeyData data){
        this.data = data;
        board = new Keyboard(0, data);
    }
    
    
    public Keyboard MakeMain(){
        Homerow.MakeHomerow(data, board);
        return board;
    }
    
}

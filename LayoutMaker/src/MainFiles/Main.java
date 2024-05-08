/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainFiles;

import Tests.*;
import DataReader.*;
import KeyboardMaker.Keyboard;
import KeyboardMaker.Maker;

/**
 *
 * @author Windows
 */
public class Main {
    public static void main(String[] args){
        KeyData data = new KeyData("data.txt");
        ReaderTest.run(data);
        Maker make = new Maker(data);
        Keyboard board = make.MakeMain();
        System.out.println(board.toString());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KeyboardMaker;

import DataReader.*;

/**
 *
 * @author Windows
 */
public class Keyboard {

    Key[][] board;
    String type;
    KeyData data;

    public Keyboard(KeyData data) {
        this(0, data);
    }

    /**
     * Constructs keyboard depending on layout parameter. 0 = blank, for
     * generating new layout 1 = QWERTY 2 = DVORAK if invalid layout is
     * selected, constructor defaults to blank
     *
     * @param layout
     */
    public Keyboard(int layout, KeyData data) {
        this.data = data;

        switch (layout) {
            case 0:
                board = new Key[3][30];
                type = "Blank";
                break;
            case 1:
                Key[][] qwert = { // Note that ' got moved to bottom row to make up for no space on middle row. 
                    {data.getKey('q'),data.getKey('w'),data.getKey('e'),data.getKey('r'),data.getKey('t'),data.getKey('y'),data.getKey('u'),data.getKey('i'),data.getKey('o'),data.getKey('p'),},
                    {data.getKey('a'),data.getKey('s'),data.getKey('d'),data.getKey('f'),data.getKey('g'),data.getKey('h'),data.getKey('j'),data.getKey('k'),data.getKey('l'),data.getKey(';'),},
                    {data.getKey('z'),data.getKey('x'),data.getKey('c'),data.getKey('v'),data.getKey('b'),data.getKey('n'),data.getKey('m'),data.getKey(','),data.getKey('.'),data.getKey('\''),},
                };
                board = qwert;
                type = "QWERTY";
                break;
            case 2:
                Key[][] dvor = {
                    {data.getKey(';'),data.getKey(','),data.getKey('.'),data.getKey('p'),data.getKey('y'),data.getKey('f'),data.getKey('g'),data.getKey('c'),data.getKey('r'),data.getKey('l'),},
                    {data.getKey('a'),data.getKey('o'),data.getKey('e'),data.getKey('u'),data.getKey('i'),data.getKey('d'),data.getKey('h'),data.getKey('t'),data.getKey('n'),data.getKey('s'),},
                    {data.getKey('\''),data.getKey('q'),data.getKey('j'),data.getKey('k'),data.getKey('x'),data.getKey('b'),data.getKey('m'),data.getKey('w'),data.getKey('v'),data.getKey('z'),},
                };
                board = dvor;
                type = "DVORAK";
                break;
        }
    }
}

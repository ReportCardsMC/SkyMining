package me.hapily.plugins.skymining.mines;

import java.util.HashMap;

public class Mines {

    public static HashMap<String, Mine> mines;

    public static Mine getMine(String name){
        return mines.get(name);
    }

}

package com.alexanderbusuttilcazac.utils;

public class Utils {
    // Clear previous line in console
    public static void clearLine(){
        System.out.print("\033[1F\33[K");
    }
    public static void clearLine(String text){
        System.out.print("\033[1F\33[K");
        System.out.println(text);
    }
}
